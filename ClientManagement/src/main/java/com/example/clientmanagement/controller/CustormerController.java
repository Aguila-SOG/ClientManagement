package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@Tag(name="Clientes")
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

    @GetMapping("/search/{nick}")
    public List<Customer> findCustomer(@PathVariable String nick){
        try {
            return customerService.findCustomer(nick);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {
        if (customer.getNick() == null || customer.getNick().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nick is required");
        }
        if (customer.getPlatform() == null || customer.getPlatform().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "platform is required");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is required");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.editCustomer(customer));
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@RequestParam Long id){
        try {
            customerService.deleteCustomer(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
