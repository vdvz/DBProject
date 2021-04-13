package utils.table_managers;

import Entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public abstract class TableManager {

    private final Connection connection;

    public final String selectionQuery;
    public final String insertionQuery;
    public final String deleteQuery;
    public final String updateQuery;

    public final PreparedStatement preparedInsertionStatement;
    public final PreparedStatement preparedDeleteStatement;
    public final PreparedStatement preparedUpdateStatement;

    public TableManager(Connection connection) throws SQLException {
        this.connection = connection;
        selectionQuery = loadSelectionQuery();
        insertionQuery = loadInsertionQuery();
        deleteQuery = loadDeleteQuery();
        updateQuery = loadUpdateQuery();

        preparedInsertionStatement = getConnection().prepareStatement(loadInsertionQuery());
        preparedDeleteStatement = getConnection().prepareStatement(loadDeleteQuery());
        preparedUpdateStatement = getConnection().prepareStatement(loadUpdateQuery());

    }

    public abstract String loadSelectionQuery();
    public abstract String loadInsertionQuery();
    public abstract String loadDeleteQuery();
    public abstract String loadUpdateQuery();

    public void insertRow(Map<String, String> row) throws SQLException{
        int index = 1;
        Map<String, Class> columns = getColumns();
        for(Map.Entry<String, Class> entry: columns.entrySet()){
            if(entry.getValue() == Integer.class){
                preparedInsertionStatement.setInt(index, Integer.parseInt(row.get(entry.getKey())));
            }
            if(entry.getValue() == String.class){
                preparedInsertionStatement.setString(index, row.get(entry.getKey()));
            }
            if(entry.getValue() == Date.class){
                preparedInsertionStatement.setDate(index, Date.valueOf(row.get(entry.getKey())));
            }
            index++;
        }
        preparedInsertionStatement.execute();
    }

    public abstract Map<String, Class> getColumns();

    public void updateRow(Map<String, String> row) throws SQLException {
        int index = 1;
        Map<String, Class> columns = getColumns();
        for(Map.Entry<String, Class> entry: columns.entrySet()){
            if(entry.getValue() == Integer.class){
                preparedUpdateStatement.setInt(index, Integer.parseInt(row.get(entry.getKey())));
            }
            if(entry.getValue() == String.class){
                preparedUpdateStatement.setString(index, row.get(entry.getKey()));
            }
            if(entry.getValue() == Date.class){
                preparedUpdateStatement.setDate(index, Date.valueOf(row.get(entry.getKey())));
            }
            index++;
        }
        preparedUpdateStatement.setInt(index, Integer.parseInt(row.get("id")));
        preparedUpdateStatement.execute();
    }

    public void deleteRow(String rowId) throws SQLException {
        preparedDeleteStatement.setInt(1, Integer.parseInt(rowId));
        preparedDeleteStatement.execute();
    }

    public abstract ObservableList<Entity> getTableRows();

    public Connection getConnection() {
        return connection;
    }
}
