package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Quote;
import com.example.clientmanagement.repository.QuoteDAO;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void deleteQuote(int year, int quarterly) {
        quoteDAO.deleteQuote(year, quarterly);
    }

    public double totalYearlyAmmount(int year) {
        double ammount = 0;
        List<Quote> quotes = new ArrayList<>();
        try {
            quotes = quoteDAO.findQuote(year);
            for (Quote quote : quotes) {
                ammount += quote.getFacImport();
            }
            return ammount;
        } catch (Exception e) {
            System.out.println("No se han podido encontrar resultados" + e);
            return 0;
        }
    }
}