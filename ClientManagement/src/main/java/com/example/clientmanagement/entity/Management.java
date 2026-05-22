package com.example.clientmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "management")
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4)
    private Integer fac_year;

    @Column(nullable = false, length = 1)
    private Integer quarterly;

    @Column(nullable = false, length = 50)
    private Double tax_payment;

    @Column(nullable = false, length = 50)
    private Double performance;

    public Management(Long id, int fac_year, int quarterly, Double tax_payment, Double performance) {
        this.id = id;
        this.fac_year = fac_year;
        this.quarterly = quarterly;
        this.tax_payment = tax_payment;
        this.performance = performance;
    }

    public Management() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFac_year() {
        return fac_year;
    }

    public void setFac_year(Integer fac_year) {
        this.fac_year = fac_year;
    }

    public Integer getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(Integer quarterly) {
        this.quarterly = quarterly;
    }

    public Double getTax_payment() {
        return tax_payment;
    }

    public void setTax_payment(Double tax_payment) {
        this.tax_payment = tax_payment;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public Integer getFacYear() {
        return fac_year;
    }
}
