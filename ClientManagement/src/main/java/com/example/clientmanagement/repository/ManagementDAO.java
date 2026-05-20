package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Management;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ManagementDAO {
    private final DataSource dataSource;

    public ManagementDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Management> findAll(){
        List<Management> managements = new ArrayList<>();
        String query = "SELECT id, fac_year, quarterly, tax_payment, performance FROM management";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Management management = new Management(
                        resultSet.getLong("id"),
                        resultSet.getInt("fac_year"),
                        resultSet.getInt("quarterly"),
                        resultSet.getDouble("tax_payment"),
                        resultSet.getDouble("performance")
                );
                managements.add(management);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return managements;
    }

    public Management createManagement(Management management) {
        String query = "INSERT INTO management (id, fac_year, quarterly, tax_payment, performance) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, management.getId());
            preparedStatement.setInt(2, management.getFac_year());
            preparedStatement.setInt(3, management.getQuarterly());
            preparedStatement.setDouble(4, management.getTax_payment());
            preparedStatement.setDouble(5, management.getPerformance());
            Integer updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    management.setId(resultSet.getLong(1));
                }
            }
            return management;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating management", e);
        }
    }

    public Management findManagement(Long id){
        Management management = null;
        String query = "SELECT id, fac_year, quarterly, tax_payment, performance FROM management WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next()){
                management = new Management(
                        resultSet.getLong("id"),
                        resultSet.getInt("fac_year"),
                        resultSet.getInt("quarterly"),
                        resultSet.getDouble("tax_payment"),
                        resultSet.getDouble("performance")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return management;
    }

    public Management editManagement(Long id, Integer fac_year, Integer quarterly, double tax_payment, double performance){
        Management management = null;
        String query = "UPDATE management SET fac_year = ?, quarterly = ?, tax_payment = ?, performance = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, fac_year);
            preparedStatement.setInt(2, quarterly);
            preparedStatement.setDouble(3, tax_payment);
            preparedStatement.setDouble(4, performance);
            preparedStatement.setLong(5, id);
            Integer updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row updated, got " + updated);
            }
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    management.setId(resultSet.getLong(1));
                }
            }
            return management;
        } catch (SQLException e) {
            throw new RuntimeException("Error edit management", e);
        }
    }

    public void deleteManagement(Long id){
        String query = "DELETE FROM management WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, id);
            Integer updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row deleted, got " + updated);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error delete management", e);
        }
    }
}
