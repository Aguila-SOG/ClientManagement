package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteDAO extends JpaRepository<Quote, Quote.Id> {

    List<Quote> findByIdQuoteYear(Integer year);
}
