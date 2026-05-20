package com.example.clientmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_number")
    private Long idNumber;

    @Column(length = 25, nullable = false)
    private String factura_type;

    @Column(length = 25, nullable = false)
    private String price_Paypal;

    @Column(length = 25, nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean is_made;

    @Column(nullable = false)
    private LocalDate bill_date;

    @Column(nullable = false)
    private double price_eu;

    @Column(nullable = false)
    private double price_us;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Bill(Long idNumber, String factura_type, String price_Paypal, String title, boolean is_made, LocalDate bill_date, double price_eu, double price_us, Customer customer) {
        this.idNumber = idNumber;
        this.factura_type = factura_type;
        this.price_Paypal = price_Paypal;
        this.title = title;
        this.is_made = is_made;
        this.bill_date = bill_date;
        this.price_eu = price_eu;
        this.price_us = price_us;
        this.customer = customer;
    }

    public Bill() {

    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public String getFactura_type() {
        return factura_type;
    }

    public void setFactura_type(String factura_type) {
        this.factura_type = factura_type;
    }

    public String getPrice_Paypal() {
        return price_Paypal;
    }

    public void setPrice_Paypal(String price_Paypal) {
        this.price_Paypal = price_Paypal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIs_made() {
        return is_made;
    }

    public void setIs_made(boolean is_made) {
        this.is_made = is_made;
    }

    public LocalDate getBill_date() {
        return bill_date;
    }

    public void setBill_date(LocalDate bill_date) {
        this.bill_date = bill_date;
    }

    public double getPrice_eu() {
        return price_eu;
    }

    public void setPrice_eu(double price_eu) {
        this.price_eu = price_eu;
    }

    public double getPrice_us() {
        return price_us;
    }

    public void setPrice_us(double price_us) {
        this.price_us = price_us;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
