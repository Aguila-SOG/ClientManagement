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
        @Column(length = 4, nullable = false)
        private Integer quoteYear;

        @Column(length = 1, nullable = false)
        private Integer quarterly;

        @Column(length = 2, nullable = false)
        private Integer month;

        public Id() {

        }
        public Id(Integer quoteYear, Integer quarterly, Integer month) {
            this.quoteYear = quoteYear;
            this.quarterly = quarterly;
            this.month = month;
        }

        public Integer getQuoteYear() {
            return quoteYear;
        }

        public void setQuoteYear(Integer quoteYear) {
            this.quoteYear = quoteYear;
        }

        public Integer getQuarterly() {
            return quarterly;
        }

        public void setQuarterly(Integer quarterly) {
            this.quarterly = quarterly;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }
    }

    public Quote(double facImport, Integer quoteYear, Integer quarterly, Integer month) {
        this.facImport = facImport;
        this.id = new Id(quoteYear, quarterly, month);
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