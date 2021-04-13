package utils.table_managers;

import Entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;

import java.util.Map;

public class TradeTypesTableManager extends TableManager {

    public TradeTypesTableManager(Connection connection) {
        super(connection);
    }

    @Override
    public String loadSelectionQuery() {
        return null;
    }

    @Override
    public String loadInsertionQuery() {
        return null;
    }

    @Override
    public String loadDeleteQuery() {
        return null;
    }

    @Override
    public String loadUpdateQuery() {
        return null;
    }

    @Override
    public void insertRow(Map<String, String> row) {

    }

    @Override
    public void updateRow(Map<String, String> row) {

    }

    @Override
    public ObservableList<Entity> getTableRows() {
        return null;
    }

}
