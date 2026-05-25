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
        String query = "SELECT fac_year, quarterly, tax_payment, performance FROM management";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                Management management = new Management(
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
        String query = "INSERT INTO management (fac_year, quarterly, tax_payment, performance) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(2, management.getFac_year());
            preparedStatement.setInt(3, management.getQuarterly());
            preparedStatement.setDouble(4, management.getTax_payment());
            preparedStatement.setDouble(5, management.getPerformance());
            Integer updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }
            return management;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating management", e);
        }
    }

    public Management findManagement(int fac_year, int quarterly){
        Management management = null;
        String query = "SELECT fac_year, quarterly, tax_payment, performance FROM management WHERE fac_year = ? and quarterly = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, fac_year);
            preparedStatement.setInt(2, quarterly);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while(resultSet.next()){
                management = new Management(
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

    public Management editManagement(Management management){
        String query = "UPDATE management SET fac_year = ?, quarterly = ?, tax_payment = ?, performance = ? WHERE fac_year = ? and quarterly = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, management.getFac_year());
            preparedStatement.setInt(2, management.getQuarterly());
            preparedStatement.setDouble(3, management.getTax_payment());
            preparedStatement.setDouble(4, management.getPerformance());
            preparedStatement.setInt(5, management.getFac_year());
            preparedStatement.setInt(6, management.getQuarterly());
            Integer updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row updated, got " + updated);
            }
            return management;
        } catch (SQLException e) {
            throw new RuntimeException("Error edit management", e);
        }
    }

    public void deleteManagement(int fac_year, int quarterly){
        String query = "DELETE FROM management  WHERE fac_year = ? and quarterly = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, fac_year);
            preparedStatement.setLong(2, quarterly);
            Integer updated = preparedStatement.executeUpdate();
            if (updated != 1) {
                throw new SQLException("Expected 1 row deleted, got " + updated);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error delete management", e);
        }
    }
}
