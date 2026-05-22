package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Quote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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

    @PersistenceContext
    private EntityManager entityManager;

    public List<Quote> findAll() {
        return entityManager.createQuery("from Quote", Quote.class).getResultList();
    }

    public List<Quote> findQuote(int year) {
        return entityManager.createQuery("from Quote where year = :yearComparable", Quote.class)
                .setParameter("yearComparable", year)
                .getResultList();
    }

    @Transactional
    public Quote create(Quote quote) {
        entityManager.persist(quote);
        return quote;
    }

    @Transactional
    public Quote editQuote(Quote quote) {
        return entityManager.merge(quote);
    }

    @Transactional
    public void deleteQuote(Long id) {
        Quote quote = entityManager.find(Quote.class, id);
        if (quote != null) {
            entityManager.remove(quote);
        }
    }
}