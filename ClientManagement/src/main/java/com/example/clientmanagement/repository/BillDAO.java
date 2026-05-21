package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Bill;
import com.example.clientmanagement.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BillDAO {
    private final DataSource dataSource;

    public BillDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT id_number, factura_type, price_Paypal, title, is_made, bill_date, price_eu, price_us, customer_id FROM bill";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                Bill bill = new Bill(
                        resultSet.getLong("id_number"),
                        resultSet.getString("factura_type"),
                        resultSet.getString("price_Paypal"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("is_made"),
                        resultSet.getObject("bill_date", LocalDate.class),
                        resultSet.getDouble("price_eu"),
                        resultSet.getDouble("price_us"),
                        customer
                );
                bills.add(bill);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public List<Bill> findAllByClient(Long customerId) {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT id_number, factura_type, price_Paypal, title, is_made, bill_date, price_eu, price_us, customer_id FROM bill WHERE customer_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                bills.add(new Bill(
                        resultSet.getLong("id_number"),
                        resultSet.getString("factura_type"),
                        resultSet.getString("price_Paypal"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("is_made"),
                        resultSet.getObject("bill_date", LocalDate.class),
                        resultSet.getDouble("price_eu"),
                        resultSet.getDouble("price_us"),
                        customer
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }

    public Bill findBill(Long id) {
        Bill bill = null;
        String query = "select id_number, factura_type, price_Paypal, title, is_made, bill_date, price_eu, price_us, customer_id FROM bill WHERE id_number = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("customer_id"));
                bill = new Bill(
                        resultSet.getLong("id_number"),
                        resultSet.getString("factura_type"),
                        resultSet.getString("price_Paypal"),
                        resultSet.getString("title"),
                        resultSet.getBoolean("is_made"),
                        resultSet.getObject("bill_date", LocalDate.class),
                        resultSet.getDouble("price_eu"),
                        resultSet.getDouble("price_us"),
                        customer
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bill;
    }

    public Bill create(Bill bill) {
        String query = "INSERT INTO bill (factura_type, price_Paypal, title, is_made, bill_date, price_eu, price_us, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, bill.getFactura_type());
            preparedStatement.setString(2, bill.getPrice_Paypal());
            preparedStatement.setString(3, bill.getTitle());
            preparedStatement.setBoolean(4, bill.isIs_made());
            preparedStatement.setDate(5, Date.valueOf(bill.getBill_date()));
            preparedStatement.setDouble(6, bill.getPrice_eu());
            preparedStatement.setDouble(7, bill.getPrice_us());
            preparedStatement.setLong(8, bill.getCustomer().getId());
            int updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got "+updated);
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    bill.setIdNumber(resultSet.getLong(1));
                }
            }
            return bill;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating bill ", e);
        }
    }

    public Bill editBill(Bill bill) {
        String query = "UPDATE bill SET factura_type = ?, price_Paypal = ?, title = ?, is_made = ?, bill_date = ?, price_eu = ?, price_us = ?, customer_id = ? WHERE id_number = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, bill.getFactura_type());
            preparedStatement.setString(2, bill.getPrice_Paypal());
            preparedStatement.setString(3, bill.getTitle());
            preparedStatement.setBoolean(4, bill.isIs_made());
            preparedStatement.setDate(5, Date.valueOf(bill.getBill_date()));
            preparedStatement.setDouble(6, bill.getPrice_eu());
            preparedStatement.setDouble(7, bill.getPrice_us());
            preparedStatement.setLong(8, bill.getCustomer().getId());
            preparedStatement.setLong(9, bill.getIdNumber());
            int updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got "+updated);
            }

            return bill;
        } catch (SQLException e) {
            throw new RuntimeException("Error edit bill", e);
        }
    }

    public void deleteBill(Long id) {
        String query = "DELETE FROM bill WHERE id_number = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            int updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got "+updated);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error delete bill", e);
        }
    }
}
