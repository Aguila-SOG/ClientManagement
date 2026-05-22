package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Quote;
import com.example.clientmanagement.repository.QuoteDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteDAO quoteDAO;

    public QuoteService(QuoteDAO quoteDAO) {
        this.quoteDAO = quoteDAO;
    }

    public List<Quote> findAll() {
        return quoteDAO.findAll();
    }

    public Quote create(Quote quote) {
        return quoteDAO.create(quote);
    }

    public List<Quote> findQuote(int year) {
        return quoteDAO.findQuote(year);
    }

    public Quote editQuote(Quote quote) {
        return quoteDAO.editQuote(quote);
    }

    public void deleteQuote(Long id) {
        quoteDAO.deleteQuote(id);
    }
}