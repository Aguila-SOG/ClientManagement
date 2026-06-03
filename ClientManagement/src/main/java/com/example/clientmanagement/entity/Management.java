package com.example.clientmanagement.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "management")
public class Management {

    @EmbeddedId
    private Id id;

    @Column(nullable = false)
    private Double taxPayment;

    @Column(nullable = false)
    private Double performance;

    @Embeddable
    public static class Id implements Serializable {
        @Column(length = 4, nullable = false)
        private Integer facYear;

        @Column(length = 1, nullable = false)
        private Integer quarterly;

        public Id() {

        }

        public Id(Integer facYear, Integer quarterly) {
            this.facYear = facYear;
            this.quarterly = quarterly;
        }
    }

    public Management() {}

    public Management(Integer facYear, Integer quarterly, Double taxPayment, Double performance) {
        this.id = new Id(facYear, quarterly);
        this.taxPayment = taxPayment;
        this.performance = performance;
    }

    public Integer getFacYear() { return id.facYear; }
    public void setFacYear(Integer facYear) { id.facYear = facYear; }

    public Integer getQuarterly() { return id.quarterly; }
    public void setQuarterly(Integer quarterly) { id.quarterly = quarterly; }

    public Double getTaxPayment() { return taxPayment; }
    public void setTaxPayment(Double taxPayment) { this.taxPayment = taxPayment; }

    public Double getPerformance() { return performance; }
    public void setPerformance(Double performance) { this.performance = performance; }
}