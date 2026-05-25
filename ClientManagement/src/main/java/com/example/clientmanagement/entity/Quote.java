package com.example.clientmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @Column(length = 4)
    private Integer quoteYear;

    @Id
    @Column(length = 1)
    private Integer quarterly;

    @Column(nullable = false)
    private Integer facImport;

    @Column
    private Date datePay;

    public Quote(Integer year, Integer quarterly, Integer facImport) {
        this.quoteYear = year;
        this.quarterly = quarterly;
        this.facImport = facImport;
    }

    public Quote(){}

    public Integer getQuoteYear() {
        return quoteYear;
    }

    public void setQuoteYear(Integer year) {
        this.quoteYear = year;
    }

    public Integer getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(Integer quarterly) {
        this.quarterly = quarterly;
    }

    public Integer getFacImport() {
        return facImport;
    }

    public void setFacImport(Integer facImport) {
        this.facImport = facImport;
    }

    public Date getDatePay() {
        return datePay;
    }

    public void setDatePay(Date datePay) {
        this.datePay = datePay;
    }
}