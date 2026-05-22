package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Quote;
import com.example.clientmanagement.service.QuoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/all")
    public List<Quote> findAll() {
        return quoteService.findAll();
    }

    @PostMapping("/create")
    public Quote create(@RequestBody Quote quote) {
        return quoteService.create(quote);
    }

    @GetMapping("/{year}")
    public List<Quote> findQuote(@PathVariable int year) {
        return quoteService.findQuote(year);
    }

    @PutMapping("/{id}")
    public Quote editQuote(@PathVariable Long id, @RequestBody Quote quote) {
        return quoteService.editQuote(id, quote.getYearOfQuote(), quote.getQuarterly(), quote.getFac_import(), quote.getPerformance());
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
    }
}