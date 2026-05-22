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
        String query = "SELECT year, quarterly, fac_import, date_pay FROM quote";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Quote quote = new Quote();
                quote.setYear(resultSet.getInt("year"));
                quote.setQuarterly(resultSet.getInt("quarterly"));
                quote.setFac_import(resultSet.getInt("fac_import"));
                quote.setDate_pay(resultSet.getDate("date_pay"));
                quotes.add(quote);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error getting quotes", e);
        }

        return quotes;
    }

    public Quote create(Quote quote) {
        String query = "INSERT INTO quote (year, quarterly, fac_import, date_pay) VALUES (?, ?, ?, ?) ";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quote.getYear());
            preparedStatement.setInt(2, quote.getQuarterly());
            preparedStatement.setInt(3, quote.getFac_import());
            preparedStatement.setDate(4, quote.getDate_pay());

            int updated = preparedStatement.executeUpdate();

            if (updated != 1) {
                throw new SQLException("Expected 1 row inserted, got " + updated);
            }

            return quote;

        } catch (SQLException e) {
            throw new RuntimeException("Error creating quote", e);
        }
    }

    public List<Quote> findQuote(int year) {
        List<Quote> quotes = new ArrayList<>();
        String query = "SELECT year, quarterly, fac_import, date_pay FROM quote WHERE year = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, year);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Quote quote = new Quote();
                    quote.setYear(resultSet.getInt("year"));
                    quote.setQuarterly(resultSet.getInt("quarterly"));
                    quote.setFac_import(resultSet.getInt("fac_import"));
                    quote.setDate_pay(resultSet.getDate("date_pay"));
                    quotes.add(quote);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding quote", e);
        }
        return quotes;
    }

    public Quote editQuote(Quote quote) {
        String query = "UPDATE quote SET year = ?, quarterly = ?, fac_import = ?, date_pay = ? WHERE year = ? and quarterly = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, quote.getYear());
            preparedStatement.setInt(2, quote.getQuarterly());
            preparedStatement.setInt(3, quote.getFac_import());
            preparedStatement.setDate(4, quote.getDate_pay());
            preparedStatement.setInt(5, quote.getYear());
            preparedStatement.setInt(2, quote.getQuarterly());

            int updated = preparedStatement.executeUpdate();

            if (updated != 1) {
                throw new SQLException("Expected 1 row updated, got " + updated);
            }
            return quote;
        } catch (SQLException e) {
            throw new RuntimeException("Error editing quote", e);
        }
    }

    public void deleteQuote(int year, int quarterly) {
        String query = "DELETE FROM quote WHERE year = ? and quarterly = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, year);
            preparedStatement.setLong(1, quarterly);

            int deleted = preparedStatement.executeUpdate();

            if (deleted != 1) {
                throw new SQLException("Expected 1 row deleted, got " + deleted);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting quote", e);
        }
    }
}