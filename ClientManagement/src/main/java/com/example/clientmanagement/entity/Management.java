package com.example.clientmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "management")
@IdClass(Management.class)
public class Management {
    @Id
    @Column(length = 4)
    private Integer facYear;

    @Id
    @Column(length = 1)
    private Integer quarterly;

    @Column(nullable = false, length = 50)
    private Double taxPayment;

    @Column(nullable = false, length = 50)
    private Double performance;

    public Management(int facYear, int quarterly, Double taxPayment, Double performance) {
        this.facYear = facYear;
        this.quarterly = quarterly;
        this.taxPayment = taxPayment;
        this.performance = performance;
    }

    public Management() {
        
    }

    public Integer getFacYear() {
        return facYear;
    }

    public void setFacYear(Integer facYear) {
        this.facYear = facYear;
    }

    public Integer getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(Integer quarterly) {
        this.quarterly = quarterly;
    }

    public Double getTaxPayment() {
        return taxPayment;
    }

    public void setTaxPayment(Double tax_payment) {
        this.taxPayment = tax_payment;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }
}
