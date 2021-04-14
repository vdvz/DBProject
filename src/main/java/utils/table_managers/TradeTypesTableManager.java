package utils.table_managers;

import entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TradeTypesTableManager extends TableManager {

    public TradeTypesTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.TRADE_TYPE);
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
