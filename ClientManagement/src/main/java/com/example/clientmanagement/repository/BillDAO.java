package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Bill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class BillDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Bill> findAll() {
        return entityManager.createQuery("from Bill", Bill.class).getResultList();
    }

    public List<Bill> findAllByClient(Long customerId) {
        return entityManager.createQuery("from Bill where customer.id = :id", Bill.class)
                .setParameter("id", customerId)
                .getResultList();
    }

    public Bill findBill(Long id) {
        return entityManager.find(Bill.class, id);
    }

    @Transactional
    public Bill create(Bill bill) {
        entityManager.persist(bill);
        return bill;
    }

    @Transactional
    public Bill editBill(Bill bill) {
        return entityManager.merge(bill);
    }

    @Transactional
    public void deleteBill(Long id) {
        Bill bill = entityManager.find(Bill.class, id);
        if (bill != null) {
            entityManager.remove(bill);
        }
    }

    public double ammountGainedQuarterly(int startMonth, int endMonth, int year, String currency) {
        Double ammount = entityManager.createQuery("SELECT SUM("+currency+") FROM Bill WHERE YEAR(billDate) = :selectedYear AND MONTH(billDate) BETWEEN :startMonth AND :endMonth", Double.class)
                .setParameter("selectedYear", year)
                .setParameter("startMonth", startMonth)
                .setParameter("endMonth", endMonth)
                .getSingleResult();
        if (ammount ==  null) {
            ammount = 0.0;
        }
        return ammount;
    }

    public double ammountGainedAnnually(int year, String currency) {
        Double ammount = entityManager.createQuery("SELECT SUM("+currency+") FROM Bill WHERE YEAR(billDate) = :selectedYear", Double.class)
                .setParameter("selectedYear", year)
                .getSingleResult();
        if (ammount ==  null) {
            ammount = 0.0;
        }
        return ammount;
    }
}
