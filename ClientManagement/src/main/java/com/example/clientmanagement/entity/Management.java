package com.example.clientmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "management")
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    private int fac_year;

    @Column(nullable = false, length = 1)
    private int quarterly;

    @Column(nullable = false, length = 50)
    private double tax_payment;

    @Column(nullable = false, length = 50)
    private double performance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFac_year() {
        return fac_year;
    }

    public void setFac_year(int fac_year) {
        this.fac_year = fac_year;
    }

    public int getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(int quarterly) {
        this.quarterly = quarterly;
    }

    public double getTax_payment() {
        return tax_payment;
    }

    public void setTax_payment(double tax_payment) {
        this.tax_payment = tax_payment;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(double performance) {
        this.performance = performance;
    }
}
