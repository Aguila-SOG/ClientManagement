package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Commission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CommissionDAO {
    private final DataSource dataSource;

    private final SessionFactory sessionFactory;

    public CommissionDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(com.example.clientmanagement.entity.Bill.class)
                .addAnnotatedClass(com.example.clientmanagement.entity.Customer.class)
                .addAnnotatedClass(com.example.clientmanagement.entity.Commission.class)
                .configure()
                .buildSessionFactory();
    }

    public List<Commission> findAll() {
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
    }
}
