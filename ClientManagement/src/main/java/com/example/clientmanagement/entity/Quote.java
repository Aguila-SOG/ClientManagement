package com.example.clientmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @Column(length = 4)
    private Integer year;

    @Id
    @Column(length = 1)
    private Integer quarterly;

    @Column(nullable = false)
    private Integer facImport;

    @Column
    private Date datePay;

    public Quote(Integer year, Integer quarterly, Integer facImport) {
        this.year = year;
        this.quarterly = quarterly;
        this.facImport = facImport;
    }

    public Quote(){}

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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