package com.example.clientmanagement.service;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.repository.BillDAO;
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
        return billDAO.findByCustomerId(id);
    }

    public Bill findBillById(Long id) {
        return billDAO.findById(id).orElseThrow(() -> new EntityNotFoundException("The bill with the id: '"+id+"' does not exist"));
    }

    public Bill create(Bill bill) {
        if (bill.getPriceEu() < 0 || bill.getPriceUs() < 0) {
            throw new IllegalArgumentException("The price can't be a negative number");
        }
        if (bill.getCustomer() == null || bill.getCustomer().getId() == null) {
            throw new IllegalArgumentException("The bill must be associated with a customer");
        }
        return billDAO.save(bill);
    }

    public Bill editBill(Bill bill) {
        try {
            if (bill.getIdNumber() == null || !billDAO.existsById(bill.getIdNumber())) {
                throw new EntityNotFoundException("Bill not found");
            }
            return billDAO.save(bill);
        } catch (DataAccessException errorConnecting) {
            System.out.println("Error while trying to connect to the database");
            return null;
        }
    }

    public void deleteBill(Long id) {
        billDAO.deleteById(id);
    }

    public double ammountGainedQuarterly(int startMonth, int endMonth, int year, String currency) {
        Double ammount = 0.0;
        try {
            List<Bill> bills = billDAO.findSpecificMonths(startMonth, endMonth, year);
            return calculateSum(bills, currency);
        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0.0;
        }
    }

    public double realAmmountGainedQuarterly(int startMonth, int endMonth, int year, String currency) {
        return ammountGainedQuarterly(startMonth, endMonth, year, currency) * 0.80;
    }

    public double ammountGainedAnnually(int year, String currency) {
        Double ammount = 0.0;
        try {
            List<Bill> bills = billDAO.findSpecificMonthsYearly(year);
            return calculateSum(bills, currency);
        } catch (DataAccessException exceptionDBCommunication) {
            System.out.println("Error while trying to communicate with the database: " + exceptionDBCommunication);
            return 0.0;
        }
    }

    public double realAmmountGainedAnnually(int year, String currency) {
        return ammountGainedAnnually(year, currency) * 0.80;
    }

    public double calculateSum(List<Bill> bills, String currency) {
        Double ammount = 0.0;
        for (Bill bill : bills) {
            if (bill.isMade() == true) {
                if (currency.equals("priceEu")) {
                    ammount += bill.getPriceEu();
                } else if (currency.equals("priceUs")) {
                    ammount += bill.getPriceUs();
                } else if (currency.equals("pricePaypal")) {
                    ammount += bill.getPricePaypal();
                }
            }
        }
        return ammount;
    }
}
