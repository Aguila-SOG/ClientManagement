package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BillDAO {
    private final DataSource dataSource;

    private final SessionFactory sessionFactory;

    public BillDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(com.example.clientmanagement.entity.Bill.class)
                .addAnnotatedClass(com.example.clientmanagement.entity.Customer.class)
                .configure()
                .buildSessionFactory();
    }

    public List<Bill> findAll() {
        Session session = sessionFactory.openSession();
        List<Bill> bills = session.createQuery("from Bill", Bill.class).getResultList();
        session.close();
        return bills;
    }

    public List<Bill> findAllByClient(Long customerId) {
        Session session = sessionFactory.openSession();
        List<Bill> bills = session.createQuery("from Bill where customer.id = :id", Bill.class)
                .setParameter("id", customerId)
                .getResultList();
        return bills;
    }

    public Bill findBill(Long id) {
        Session session = sessionFactory.openSession();
        Bill bill = session.find(Bill.class, id);
        return bill;
    }

    public Bill create(Bill bill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(bill);
        transaction.commit();
        session.close();
        return bill;
    }

    public Bill editBill(Bill bill) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Bill newBill = session.merge(bill);
        transaction.commit();
        session.close();
        return newBill;
    }

    public void deleteBill(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Bill bill = session.find(Bill.class, id);
        session.remove(bill);
        transaction.commit();
    }
}
