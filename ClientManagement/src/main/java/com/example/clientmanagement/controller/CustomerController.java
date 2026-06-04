package com.example.clientmanagement.controller;

import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        Customer created = customerService.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/search/nick/{nick}")
    public List<Customer> findCustomerByNick(@PathVariable String nick){
        return customerService.findCustomerByNick(nick);
    }

    @GetMapping("/search/name/{name}")
    public List<Customer> findCustomerByName(@PathVariable String name){
        return customerService.findCustomerByName(name);
    }

    @GetMapping("/search/email/{email}")
    public List<Customer> findCustomerByEmail(@PathVariable String email){
        return customerService.findCustomerByEmail(email);
    }

    @PutMapping("/edit")
    public ResponseEntity<Customer> editCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.editCustomer(customer));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}