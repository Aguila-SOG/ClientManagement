package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.repository.CustomerDAO;
import org.springframework.stereotype.Service;

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
        return customerDAO.create(customer);
    }

    public List<Customer> findCustomer(String nick){
        return customerDAO.findCustomer(nick);
    }

    public Customer editCustomer(Customer customer){
        return customerDAO.editCustomer(customer);
    }

    public void deleteCustomer(Long id){
        customerDAO.deleteCustomer(id);
    }
}
