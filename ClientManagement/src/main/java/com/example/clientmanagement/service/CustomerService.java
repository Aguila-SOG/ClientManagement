package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.repository.CustomerDAO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public List<Customer> findAll(){
        return customerDAO.findAll();
    }

    public Customer create(Customer customer) {
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
        return customerDAO.create(customer);
    }

    public List<Customer> findCustomer(String nick){
        return customerDAO.findCustomer(nick);
    }

    public Customer editCustomer(Customer customer){
            if (customer.getNick() == null || customer.getNick().isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nick is required");
    }
        if (customer.getPlatform() == null || customer.getPlatform().isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "platform is required");
    }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is required");
    }
        return customerDAO.editCustomer(customer);
    }
    public void deleteCustomer(Long id){
        customerDAO.deleteCustomer(id);
    }
}
