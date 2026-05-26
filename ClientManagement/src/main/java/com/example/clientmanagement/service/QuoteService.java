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

    public void deleteQuote(int year, int quarterly) {
        quoteDAO.deleteQuote(year, quarterly);
    }

    public double calcTotalFactured(int year, int month) {
        List<Quote> yearlyQuotes = quoteDAO.findQuote(year);
        double total = 0.0;

        for (Quote quote : yearlyQuotes) {
            if (quote.getDatePay() != null) {
                int quoteMonth = quote.getDatePay().getMonth() + 1;
                if (quoteMonth == month) {
                    total += quote.getFacImport();
                }
            }
        }
        return total;
    }

    public double calcIrpfRetained(int year, int month) {
        double totalFactured = calcTotalFactured(year, month);
        return totalFactured * 0.20;
    }

    public double calcRealEarnings(int year, int month) {
        double totalFactured = calcTotalFactured(year, month);
        double irpfRetained = calcIrpfRetained(year, month);
        return totalFactured - irpfRetained;
    }

}