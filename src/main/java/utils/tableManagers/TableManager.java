package utils.tableManagers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    abstract List<String> getColumnNames();

    /*
    public ObservableList<ObservableList<String>> getTableRows() throws SQLException {
        ResultSet result = connection.executeQuery(selectionQuery);
        ObservableList<ObservableList<String>> rows = FXCollections.observableArrayList();
        List<String> columnNames = getColumnNames();
        while(result.next()){
            ObservableList<String> row = FXCollections.observableArrayList();
            columnNames.forEach(e -> {
                try {
                    String res = result.getObject(e).toString();
                    row.addAll(res);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            rows.add(row);
        }
        return rows;
    }
    */
    public Connection getConnection() {
        return connection;
    }
}
