package utils.table_managers;

import Entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;

import java.util.Map;

public abstract class TableManager {

    private final Connection connection;

    public final String selectionQuery;
    public final String insertionQuery;
    public final String deleteQuery;
    public final String updateQuery;

    public TableManager(Connection connection) {
        this.connection = connection;
        selectionQuery = loadSelectionQuery();
        insertionQuery = loadInsertionQuery();
        deleteQuery = loadDeleteQuery();
        updateQuery = loadUpdateQuery();
    }

    public abstract String loadSelectionQuery();
    public abstract String loadInsertionQuery();
    public abstract String loadDeleteQuery();
    public abstract String loadUpdateQuery();

    public abstract void insertRow(Map<String, String> row);

    public abstract void updateRow(Map<String, String> row);

    public void deleteRow(String rowId){
        System.out.println(rowId);
    }

    public abstract ObservableList<Entity> getTableRows();

    public Connection getConnection() {
        return connection;
    }
}
