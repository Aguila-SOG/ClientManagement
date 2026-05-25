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
    private String facturaType;

    @Column(length = 25, nullable = false)
    private String pricePaypal;

    @Column(length = 25, nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean isMade;

    @Column(nullable = false)
    private LocalDate billDate;

    @Column(nullable = false)
    private double priceEu;

    @Column(nullable = false)
    private double priceUs;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Bill(Long idNumber, String facturaType, String pricePaypal, String title, boolean isMade, LocalDate billDate, double priceEu, double priceUs, Customer customer) {
        this.idNumber = idNumber;
        this.facturaType = facturaType;
        this.pricePaypal = pricePaypal;
        this.title = title;
        this.isMade = isMade;
        this.billDate = billDate;
        this.priceEu = priceEu;
        this.priceUs = priceUs;
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

    public String getFacturaType() {
        return facturaType;
    }

    public void setFacturaType(String factura_type) {
        this.facturaType = factura_type;
    }

    public String getPricePaypal() {
        return pricePaypal;
    }

    public void setPricePaypal(String price_Paypal) {
        this.pricePaypal = price_Paypal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIs_made() {
        return isMade;
    }

    public void setIs_made(boolean isMade) {
        this.isMade = isMade;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBill_date(LocalDate billDate) {
        this.billDate = billDate;
    }

    public double getPriceEu() {
        return priceEu;
    }

    public void setPriceEu(double priceEu) {
        this.priceEu = priceEu;
    }

    public double getPriceUs() {
        return priceUs;
    }

    public void setPriceUs(double priceUs) {
        this.priceUs = priceUs;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
