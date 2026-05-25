package com.example.clientmanagement.repository;

import com.example.clientmanagement.entity.Quote;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuoteDAO {

    private final DataSource dataSource;

    public QuoteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Quote> findAll() {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT idNumber, year, quarterly, facImport, performance FROM quote";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Quote quote = new Quote();
                quote.setIdNumber(resultSet.getLong("idNumber"));
                quote.setYear(resultSet.getInt("year"));
                quote.setQuarterly(resultSet.getDouble("quarterly"));
                quote.setFacImport(resultSet.getDouble("facImport"));
                quote.setPerformance(resultSet.getDouble("performance"));
                quotes.add(quote);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting quotes", e);
        }

        return quotes;
    }

    public Quote create(Quote quote) {
        String query = "INSERT INTO quote (year, quarterly, facImport, performance) VALUES (?, ?, ?, ?) ";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, quote.getYear());
            preparedStatement.setDouble(2, quote.getQuarterly());
            preparedStatement.setDouble(3, quote.getFacImport());
            preparedStatement.setDouble(4, quote.getPerformance());

            int updated = preparedStatement.executeUpdate();

            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    quote.setIdNumber(generatedKeys.getLong(1));
                }
            }

            return quote;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating quote", e);
        }
    }

    public List<Quote> findQuote(int year) {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT idNumber, year, quarterly, facImport, performance FROM quote WHERE year = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, year);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Quote quote = new Quote();
                    quote.setIdNumber(resultSet.getLong("idNumber"));
                    quote.setYear(resultSet.getInt("year"));
                    quote.setQuarterly(resultSet.getDouble("quarterly"));
                    quote.setFacImport(resultSet.getDouble("facImport"));
                    quote.setPerformance(resultSet.getDouble("performance"));
                    quotes.add(quote);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding quote", e);
        }
        return quotes;
    }

    public Quote editQuote(Long id, Quote quote) {
        String query = "UPDATE quote SET year = ?, quarterly = ?, facImport = ?, performance = ? WHERE idNumber = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quote.getYear());
            preparedStatement.setDouble(2, quote.getQuarterly());
            preparedStatement.setDouble(3, quote.getFacImport());
            preparedStatement.setDouble(4, quote.getPerformance());
            preparedStatement.setLong(5, id);

            int updated = preparedStatement.executeUpdate();

            if (updated != 1) {
                throw new SQLException("Expected 1 row updated, got " + updated);
            }

            Quote newQuote = new Quote();
            newQuote.setIdNumber(id);
            newQuote.setYear(quote.getYear());
            newQuote.setQuarterly(quote.getQuarterly());
            newQuote.setFacImport(quote.getFacImport());
            newQuote.setPerformance(quote.getPerformance());

            return quote;

        } catch (SQLException e) {
            throw new RuntimeException("Error editing quote", e);
        }
    }

    public void deleteQuote(Long id) {
        String query = "DELETE FROM quote WHERE idNumber = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            int deleted = preparedStatement.executeUpdate();

            if (deleted != 1) {
                throw new SQLException("Expected 1 row deleted, got " + deleted);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting quote", e);
        }
    }
}