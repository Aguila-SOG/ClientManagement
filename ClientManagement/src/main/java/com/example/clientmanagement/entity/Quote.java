package com.example.clientmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_number")
    private Long idNumber;

    @Column(nullable = false)
    private Integer yearOfQuote;

    @Column(nullable = false)
    private Integer quarterly;

    @Column(nullable = false)
    private Integer fac_import;

    @Column(nullable = false)
    private Integer performance;

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getYearOfQuote() {
        return yearOfQuote;
    }

    public void setYearOfQuote(Integer yearOfQuote) {
        this.yearOfQuote = yearOfQuote;
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

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }
}