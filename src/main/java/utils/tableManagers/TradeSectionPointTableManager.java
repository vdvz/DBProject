package utils.tableManagers;

import Entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;

import java.util.List;
import java.util.Map;

public class TradeSectionPointTableManager extends TableManager {

    public TradeSectionPointTableManager(Connection connection) {
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
    public ObservableList<Entity> getTableRows() {
        return null;
    }

}
