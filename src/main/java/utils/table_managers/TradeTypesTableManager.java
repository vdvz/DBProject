package utils.table_managers;

import Entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TradeTypesTableManager extends TableManager {

    public TradeTypesTableManager(Connection connection) throws SQLException {
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

    private final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("name", String.class);
        }
    };
    @Override
    public Map<String, Class> getColumns() {
        return columns;
    }

    @Override
    public ObservableList<Entity> getTableRows() {
        return null;
    }

}
