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
    @JoinColumn(name = "costumer_id", nullable = false)
    private Customer customer;
}
