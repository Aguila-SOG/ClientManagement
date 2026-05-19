package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAO {
    private final DataSource dataSource;

    public CustomerDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Customer> findAll(){
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT id, nick, platform, name, email FROM customer";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getLong("id"),
                        resultSet.getString("nick"),
                        resultSet.getString("platform"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                customers.add(customer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    public Customer create(Customer customer) {
        String query = "INSERT INTO customer (nick, platform, name, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getNick());
            preparedStatement.setString(2, customer.getPlatform());
            preparedStatement.setString(3, customer.getName());
            preparedStatement.setString(4, customer.getEmail());
            int updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    customer.setId(resultSet.getLong(1));
                }
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating customer", e);
        }
    }

    public Customer findCustomer(Long id){
        Customer customer = null;
        String query = "SELECT id, nick, platform, name, email FROM customer WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next()){
                 customer = new Customer(
                        resultSet.getLong("id"),
                        resultSet.getString("nick"),
                        resultSet.getString("platform"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public Customer editCustomer(Long id, String nick, String platform, String name, String email){
        Customer customer = null;
        String query = "UPDATE customer SET nick = ?, platform = ?, name = ?, email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, nick);
            preparedStatement.setString(2, platform);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, email);
            preparedStatement.setLong(5, id);
            int updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    customer.setId(resultSet.getLong(1));
                }
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException("Error edit customer", e);
        }
    }

    public void deleteCustomer(Long id){
        String query = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            int updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error delete customer", e);
        }
    }
}
