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

    public Customer findCustomer(Long id){
        return customerDAO.findCustomer(id);
    }

    public Customer editCustomer(Long id, String nick, String platform, String name, String email){
        return customerDAO.editCustomer(id, nick, platform, name, email);
    }

    public void deleteCustomer(Long id){
        customerDAO.deleteCustomer(id);
    }
}
