package com.example.clientmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "management")
@IdClass(ManagementId.class)
public class Management {

    @Id
    @Column(length = 4)
    private Integer facYear;

    @Id
    @Column(length = 1)
    private Integer quarterly;

    @Column(nullable = false)
    private Double taxPayment;

    @Column(nullable = false)
    private Double performance;

    public Management() {}

    public Management(Integer facYear, Integer quarterly, Double taxPayment, Double performance) {
        this.facYear = facYear;
        this.quarterly = quarterly;
        this.taxPayment = taxPayment;
        this.performance = performance;
    }

    public Integer getFacYear() { return facYear; }
    public void setFacYear(Integer facYear) { this.facYear = facYear; }

    public Integer getQuarterly() { return quarterly; }
    public void setQuarterly(Integer quarterly) { this.quarterly = quarterly; }

    public Double getTaxPayment() { return taxPayment; }
    public void setTaxPayment(Double taxPayment) { this.taxPayment = taxPayment; }

    public Double getPerformance() { return performance; }
    public void setPerformance(Double performance) { this.performance = performance; }
}