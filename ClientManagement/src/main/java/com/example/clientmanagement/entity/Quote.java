package com.example.clientmanagement.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "quote")
public class Quote {

    @EmbeddedId
    private Id id = new Id();

    @Column(nullable = false)
    private double facImport;

    @Column
    private Date datePay;

    @Embeddable
    public static class Id implements Serializable {
        @Column(length = 4)
        private Integer quoteYear;

        @Column(length = 1)
        private Integer quarterly;

        public Id() {

        }
        public Id(Integer quoteYear, Integer quarterly) {
            this.quoteYear = quoteYear;
            this.quarterly = quarterly;
        }
    }

    public Quote(double facImport) {
        this.facImport = facImport;
    }

    public Quote(){}

    public Integer getQuoteYear() {
        return id.quoteYear;
    }

    public void setQuoteYear(Integer year) {
        this.id.quoteYear = year;
    }

    public Integer getQuarterly() {
        return id.quarterly;
    }

    public void setQuarterly(Integer quarterly) {
        this.id.quarterly = quarterly;
    }

    public double getFacImport() {
        return facImport;
    }

    public void setFacImport(double facImport) {
        this.facImport = facImport;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }
}