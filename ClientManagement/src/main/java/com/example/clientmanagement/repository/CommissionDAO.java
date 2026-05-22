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

/*    public List<Commission> findAll() {
        Session session = sessionFactory.openSession();
        List<Commission> commissions = session.createQuery("from Commission", Commission.class).getResultList();
        session.close();
        return commissions;
    }

    public List<Commission> findAllByClient(Long customerId) {
        Session session = sessionFactory.openSession();
        List<Commission> commissions = session.createQuery("from Commission where customer.id = :id", Commission.class)
                .setParameter("id", customerId)
                .getResultList();
        session.close();
        return commissions;
    }

    public Commission findCommissionById(Long id) {
        Session session = sessionFactory.openSession();
        Commission commission = session.find(Commission.class, id);
        session.close();
        return commission;
    }

    public Commission create(Commission commission) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(commission);
        transaction.commit();
        session.close();
        return commission;
    }

    public Commission editCommission(Commission commission) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Commission newCommission = session.merge(commission);
        transaction.commit();
        session.close();
        return newCommission;
    }

    public void deleteCommission(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Commission commission = session.find(Commission.class, id);
        session.remove(commission);
        transaction.commit();
        session.close();
    }*/
}
