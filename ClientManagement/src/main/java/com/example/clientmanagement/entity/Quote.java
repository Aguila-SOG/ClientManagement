package com.example.clientmanagement.entity;

import jakarta.persistence.*;
import java.sql.Date;


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
    private Integer fac_import;

    @Column
    private Date date_pay;

    public Quote(Integer year, Integer quarterly, Integer fac_import) {
        this.year = year;
        this.quarterly = quarterly;
        this.fac_import = fac_import;
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

    public Integer getFac_import() {
        return fac_import;
    }

    public void setFac_import(Integer fac_import) {
        this.fac_import = fac_import;
    }

    public Date getDate_pay() {
        return date_pay;
    }

    public void setDate_pay(Date date_pay) {
        this.date_pay = date_pay;
    }
}