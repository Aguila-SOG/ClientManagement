package com.example.clientmanagement.service;

import com.example.clientmanagement.repository.BillDAO;
import com.example.clientmanagement.entity.Bill;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillDAO billDAO;

    public BillService(BillDAO billDAO) {
        this.billDAO = billDAO;
    }

    public List<Bill> findAll() {
        return billDAO.findAll();
    }

    public List<Bill> findAllByClient(Long id) {
        return billDAO.findAllByClient(id);
    }

    public Bill findBillById(Long id) {
        Bill bill = billDAO.findBill(id);
        if (bill == null) {
            throw new EntityNotFoundException("The bill with the id: '"+id+"' does not exist");
        }
        return bill;
    }

    public Bill create(Bill bill) {
        if (bill.getPriceEu() < 0 || bill.getPriceUs() < 0) {
            throw new IllegalArgumentException("The price can't be a negative number");
        }
        if (bill.getCustomer() == null || bill.getCustomer().getId() == null) {
            throw new IllegalArgumentException("The bill must be associated with a customer");
        }
        return billDAO.create(bill);
    }

    public Bill editBill(Bill bill) {
        return billDAO.editBill(bill);
    }

    public void deleteBill(Long id) {
        billDAO.deleteBill(id);
    }

    public double ammountGainedQuarterly(int startMonth, int endMonth, int year, String currency) {
        try {
            if (currency.equals("priceEu") || currency.equals("priceUs") || currency.equals("pricePaypal")) {
                return billDAO.ammountGainedQuarterly(startMonth, endMonth, year, currency);
            } else {
                return 0.0;
            }
        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0.0;
        }
    }

    public double realAmmountGainedQuarterly(int startMonth, int endMonth, int year, String currency) {
        try {
            if (currency.equals("priceEu") || currency.equals("priceUs") || currency.equals("pricePaypal")) {
                return (billDAO.ammountGainedQuarterly(startMonth, endMonth, year, currency) * 0.80);
            } else {
                return 0.0;
            }
        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0.0;
        }
    }

    public double ammountGainedAnnually(int year, String currency) {
        try {
            if (currency.equals("priceEu") || currency.equals("priceUs") || currency.equals("pricePaypal")) {
                return billDAO.ammountGainedAnnually(year, currency);
            } else {
                return 0.0;
            }
        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0.0;
        }
    }

    public double realAmmountGainedAnnually(int year, String currency) {
        try {
            if (currency.equals("priceEu") || currency.equals("priceUs") || currency.equals("pricePaypal")) {
                return (billDAO.ammountGainedAnnually(year, currency) * 0.80);
            } else {
                return 0.0;
            }
        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0.0;
        }
    }
}
