package utils;

import java.sql.*;
import java.util.*;

public class Connection {

    private java.sql.Connection connection;

    private static final String nsuIp = "84.237.50.81";
    private static final String localhost = "127.0.0.1";
    private static final String defaultPort = "1521";
    private static final String login = "18208_VIZIR";
    private static final String password = "Vadim090900";
    private static final String driver = "oracle.jdbc.driver.OracleDriver";

    public Connection() throws SQLException, ClassNotFoundException {
        registerConnection(nsuIp, login, password);
    }

    public Connection(String login, String password) throws SQLException, ClassNotFoundException {
        registerConnection(localhost, login, password);
    }

    private void registerConnection(String ip, String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driver);

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
        TimeZone.setDefault(timeZone);
        Locale.setDefault(Locale.ENGLISH);

        System.out.println("register connection to " + ip + "...");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@" + ip + ":" + Connection.defaultPort + ":", props);

        if (connection.isValid(1)) {
            System.out.println("success connection to " + ip);
        } else {
            System.out.println("bad connection to " + ip);
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("connection was closed");
        } else {
            System.out.println("connection is not registered");
        }
    }

    public CallableStatement createCallableStatement(String query) throws SQLException {
        return connection.prepareCall(query);
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public void execute(String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public int executeUpdate(String sql) throws SQLException {
        PreparedStatement preStatement = connection.prepareStatement(sql);
        return preStatement.executeUpdate(sql);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public PreparedStatement prepareStatement(String query, String[] columnNames) throws SQLException {
        return connection.prepareStatement(query, columnNames);
    }

    public void setAutoCommit(boolean b){
        try {
            connection.setAutoCommit(b);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}