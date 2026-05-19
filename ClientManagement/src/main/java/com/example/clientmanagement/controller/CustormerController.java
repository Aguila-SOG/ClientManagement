package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustormerController {
    private final CustomerService customerService;

    public CustormerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body is required");
        }
        if (customer.getNick() == null || customer.getNick().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nick is required");
        }
        if (customer.getPlatform() == null || customer.getPlatform().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "platform is required");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is required");
        }

        Customer created = customerService.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public Customer findCustomer(@PathVariable Long id){
        try {
            return customerService.findCustomer(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Customer> editCustomer(@PathVariable Long id,@PathVariable String nick,@PathVariable String platform,@PathVariable String name,@PathVariable String email) {
        if (nick == null || nick.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nick is required");
        }
        if (platform == null || platform.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "platform is required");
        }
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is required");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.editCustomer(id, nick, platform, name, email));
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@PathVariable Long id){
        try {
            customerService.deleteCustomer(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
