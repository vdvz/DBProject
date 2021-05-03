package database_managers.request_managers;

import init.Main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import utils.Connection;

public class PurchaseManager {

    private final Connection connection;

    public PurchaseManager() {
        this.connection = Main.getDatabaseManager().getConnection();
    }

    public int addCustomer(Map<String, String> value) throws SQLException {
        String query = "insert into CUSTOMERS(name, age) values (?, ?)";

        PreparedStatement statement = connection.getConnection().prepareStatement(query,
                new String[]{"id"});

        statement.setString(1, value.get("name"));
        statement.setInt(2, Integer.parseInt(value.get("age")));

        statement.execute();

        ResultSet result = statement.getGeneratedKeys();
        result.next();
        return result.getInt(1);
    }

    public List<Integer> addPurchaseCompositions(List<Map<String, String>> values)
            throws SQLException {
        List<Integer> resultIds = new ArrayList<>();
        String query ="insert into PURCHASE_COMPOSITIONS(good, count, result_price, purchase_date) " +
                "values (?, ?, ?, ?)";

        PreparedStatement statement = connection.getConnection().prepareStatement(query,
                new String[]{"id"});

        for (Map<String, String> value : values) {
            statement.setInt(1, Integer.parseInt(value.get("good")));
            statement.setInt(2, Integer.parseInt(value.get("count")));
            statement.setInt(3, Integer.parseInt(value.get("result_price")));
            statement.setDate(4, Date.valueOf(value.get("purchase_date")));

            statement.execute();

            ResultSet result = statement.getGeneratedKeys();
            result.next();
            resultIds.add(result.getInt(1));
        }

        return resultIds;
    }

    public void addSales(List<Map<String, String>> values) throws SQLException {
        String query = "INSERT INTO SALES(seller, customer, purchase_composition) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        for (Map<String, String> value : values) {
            statement.setInt(1, Integer.parseInt(value.get("seller")));
            statement.setInt(2, Integer.parseInt(value.get("customer")));
            statement.setInt(3, Integer.parseInt(value.get("purchase_composition")));

            statement.execute();
        }
    }

    public void startTransaction() {
        connection.setAutoCommit(false);
    }

    public void rollbackTransaction() {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public void commitTransaction() {
        connection.commit();
        connection.setAutoCommit(true);
    }
}
