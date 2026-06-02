package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.entity.Commission;
import com.example.clientmanagement.entity.Customer;
import com.example.clientmanagement.repository.BillDAO;
import com.example.clientmanagement.repository.CommissionDAO;
import com.example.clientmanagement.repository.CustomerDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final BillDAO billDAO;
    private final CommissionDAO commissionDAO;

    public CustomerService(CustomerDAO customerDAO, BillDAO billDAO, CommissionDAO commissionDAO){
        this.customerDAO = customerDAO;
        this.billDAO = billDAO;
        this.commissionDAO = commissionDAO;
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

    public List<Customer> findCustomerByNick(String nick){
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

    public List<Customer> findCustomerByName(String name){
        try {
            if (name == null) {
                return new ArrayList<>();
            }
            return customerDAO.findByNameContaining(name);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
            return new ArrayList<>();
        }
    }

    public List<Customer> findCustomerByEmail(String email){
        try {
            if (email == null) {
                return new ArrayList<>();
            }
            return customerDAO.findByEmailContaining(email);
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
            List<Bill> bills = billDAO.findByCustomerId(id);
            for (Bill bill : bills) {
                bill.setCustomer(null);
                billDAO.save(bill);
            }
            List<Commission> commissions = commissionDAO.findByCustomerId(id);
            for (Commission commission : commissions) {
                commission.setCustomer(null);
                commissionDAO.save(commission);
            }
            customerDAO.deleteById(id);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
        }
    }
}
