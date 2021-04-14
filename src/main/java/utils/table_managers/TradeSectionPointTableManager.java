package utils.table_managers;

import entities.Entity;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TradeSectionPointTableManager extends TableManager {

    public TradeSectionPointTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.TRADE_SECTION_POINT);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("trade_point", Integer.class);
            put("floor", String.class);
            put("manager_name", Integer.class);
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
