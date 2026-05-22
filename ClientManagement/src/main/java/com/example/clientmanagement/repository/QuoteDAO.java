package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.entity.Quote;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuoteDAO {

    private final DataSource dataSource;

    private final SessionFactory sessionFactory;

    public QuoteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(com.example.clientmanagement.entity.Quote.class)
                .addAnnotatedClass(com.example.clientmanagement.entity.Customer.class)
                .configure()
                .buildSessionFactory();
    }

    public List<Quote> findAll() {
        Session session = sessionFactory.openSession();
        List<Quote> quotes = session.createQuery("from Quote", Quote.class).getResultList();
        session.close();
        return quotes;
    }

    public Quote create(Quote quote) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(quote);
        transaction.commit();
        session.close();
        return quote;
    }

    public List<Quote> findQuote(int year) {
        Session session = sessionFactory.openSession();
        List<Quote> quote = session.createQuery("from Quote where year = :yearComparable", Quote.class)
                .setParameter("yearComparable", year)
                .getResultList();
        return quote;
    }

    public Quote editQuote(Quote quote) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Quote newQuote = session.merge(quote);
        transaction.commit();
        session.close();
        return newQuote;
    }

    public void deleteQuote(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Quote quote = session.find(Quote.class, id);
        session.remove(quote);
        transaction.commit();
    }
}