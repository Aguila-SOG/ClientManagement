package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Quote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class QuoteDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Quote> findAll() {
        return entityManager.createQuery("from Quote", Quote.class).getResultList();
    }

    public List<Quote> findQuote(int year) {
        return entityManager.createQuery("from Quote where quoteYear = :yearComparable", Quote.class)
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
    public void deleteQuote(int year, int quarterly) {
        entityManager.createQuery("DELETE FROM Quote WHERE quoteYear = :deleteYear AND quarterly = :deleteQuarterly")
                .setParameter("deleteYear", year)
                .setParameter("deleteQuarterly", quarterly)
                .executeUpdate();
    }
}