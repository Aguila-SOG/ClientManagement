package com.example.clientmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNumber")
    private Long idNumber;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private double quarterly;

    @Column(nullable = false)
    private double facImport;

    @Column(nullable = false)
    private double performance;

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public double getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(Double quarterly) {
        this.quarterly = quarterly;
    }

    public double getFacImport() {
        return facImport;
    }

    public void setFacImport(Double facImport) {
        this.facImport = facImport;
    }

    public double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }
}