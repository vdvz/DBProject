package utils.table_managers;

import entities.Entity;
import entities.TradeRoom;
import entities.TradeSectionPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
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
        ObservableList<Entity> resultList = FXCollections.observableArrayList();
        ResultSet result;
        try {
            result = getConnection().executeQuery(selectionQuery);
            while(result.next()){
                String id = result.getObject("id").toString();
                String trade_point = result.getObject("trade_point").toString();
                String floor = result.getObject("floor").toString();
                String manager_name = result.getObject("floor").toString();
                resultList.add(new TradeSectionPoint(id, trade_point, floor, manager_name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
