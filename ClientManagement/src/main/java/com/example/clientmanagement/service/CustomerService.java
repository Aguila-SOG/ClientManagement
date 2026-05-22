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

    public Customer findById(Long id) {
        return customerDAO.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer create(Customer customer) {
        return customerDAO.save(customer);
    }

    public List<Customer> findCustomer(String nick){
        return customerDAO.findByNickContaining(nick);
    }

    public Customer editCustomer(Customer customer){
        Customer existing = findById(customer.getId());

        existing.setNick(customer.getNick());
        existing.setPlatform(customer.getPlatform());
        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());

        return customerDAO.save(existing);
    }

    public void deleteCustomer(Long id){
        if (!customerDAO.existsById(id)) {
            throw new RuntimeException("Customer not found.");
        }
        customerDAO.deleteById(id);
    }
}