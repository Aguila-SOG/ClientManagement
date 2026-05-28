package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Quote;
import com.example.clientmanagement.repository.QuoteDAO;
import org.springframework.dao.DataAccessException;
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

    private final double EURO_TO_USD = 1.16;
    private final double PAYPAL_VARIABLE_USD = 0.044;
    private final double PAYPAL_FIXED_USD = 0.30;

    public double calcTotalFactured(int year, int month) {
        return calcTotalFactured(year, month, "EUR");
    }

    public double calcTotalFactured(int year, int month, String mode) {
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

        if ("USD".equalsIgnoreCase(mode)) {
            return total * EURO_TO_USD;
        } else if ("PAYPAL_USD".equalsIgnoreCase(mode)) {
            double totalUsd = total * EURO_TO_USD;
            return totalUsd - ((totalUsd * PAYPAL_VARIABLE_USD) + PAYPAL_FIXED_USD);
        }
        return total;
    }


    public double calcIrpfRetained(int year, int month) {
        return calcIrpfRetained(year, month, "EUR");
    }

    public double calcIrpfRetained(int year, int month, String mode) {
        double totalFactured = calcTotalFactured(year, month, "EUR");
        double irpf = totalFactured * 0.20;

        if ("USD".equalsIgnoreCase(mode)) {
            return irpf * EURO_TO_USD;
        } else if ("PAYPAL_USD".equalsIgnoreCase(mode)) {
            double irpfUsd = irpf * EURO_TO_USD;
            return irpfUsd - ((irpfUsd * PAYPAL_VARIABLE_USD) + PAYPAL_FIXED_USD);
        }
        return irpf;
    }


    public double calcRealEarnings(int year, int month) {
        return calcRealEarnings(year, month, "EUR");
    }

    public double calcRealEarnings(int year, int month, String mode) {
        double totalFactured = calcTotalFactured(year, month, "EUR");
        double irpfRetained = calcIrpfRetained(year, month, "EUR");
        double earnings = totalFactured - irpfRetained;

        if ("USD".equalsIgnoreCase(mode)) {
            return earnings * EURO_TO_USD;
        } else if ("PAYPAL_USD".equalsIgnoreCase(mode)) {
            double earningsUsd = earnings * EURO_TO_USD;
            return earningsUsd - ((earningsUsd * PAYPAL_VARIABLE_USD) + PAYPAL_FIXED_USD);
        }
        return earnings;
    }


    public double totalYearlyAmmount(int year) {
        return totalYearlyAmmount(year, "EUR");
    }

    public double totalYearlyAmmount(int year, String mode) {
        double ammount = 0;
        List<Quote> quotes = new ArrayList<>();
        try {
            quotes = quoteDAO.findQuote(year);
            for (Quote quote : quotes) {
                ammount += quote.getFacImport();
            }

            if ("USD".equalsIgnoreCase(mode)) {
                return ammount * EURO_TO_USD;
            } else if ("PAYPAL_USD".equalsIgnoreCase(mode)) {
                double ammountUsd = ammount * EURO_TO_USD;
                return ammountUsd - ((ammountUsd * PAYPAL_VARIABLE_USD) + PAYPAL_FIXED_USD);
            }
            return ammount;

        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0;
        }
    }
}