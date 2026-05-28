package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.repository.CustomerDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public List<Customer> findAll(){
        try {
            return customerDAO.findAll();
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
            return new ArrayList<>();
        }
    }

    public Customer findById(Long id) {
        try {
            return customerDAO.findById(id).orElse(null);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
            return null;
        }
    }

    public Customer create(Customer customer) {
        return customerDAO.save(customer);
    }

    public List<Customer> findCustomer(String nick){
        try {
            if (nick == null) {
                return new ArrayList<>();
            }
            return customerDAO.findByNickContaining(nick);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
            return new ArrayList<>();
        }
    }

    public Customer editCustomer(Customer customer){
        try {
            Customer existing = findById(customer.getId());

            existing.setNick(customer.getNick());
            existing.setPlatform(customer.getPlatform());
            existing.setName(customer.getName());
            existing.setEmail(customer.getEmail());

            return customerDAO.save(existing);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
            return null;
        }
    }

    public void deleteCustomer(Long id){
        try {
            customerDAO.deleteById(id);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
        }
    }
}
