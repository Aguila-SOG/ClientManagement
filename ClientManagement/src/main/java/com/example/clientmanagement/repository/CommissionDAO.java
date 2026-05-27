package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Commission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommissionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Commission> findAll() {
        return entityManager.createQuery("from Commission", Commission.class).getResultList();
    }

    public List<Commission> findAllByClient(Long customerId) {
        return entityManager.createQuery("from Commission where customer.id = :id", Commission.class)
                .setParameter("id", customerId)
                .getResultList();
    }

    public Commission findCommissionById(Long id) {
        return entityManager.find(Commission.class, id);
    }

    @Transactional
    public Commission create(Commission commission) {
        entityManager.persist(commission);
        return commission;
    }

    @Transactional
    public Commission editCommission(Commission commission) {
        return entityManager.merge(commission);
    }

    @Transactional
    public void deleteCommission(Long id) {
        Commission commission = entityManager.find(Commission.class, id);
        if (commission != null) {
            entityManager.remove(commission);
        }
    }


}
