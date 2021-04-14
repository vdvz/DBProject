package utils.table_managers;

import entities.Entity;
import entities.TradeRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TradeRoomTableManager extends TableManager {

    public TradeRoomTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.TRADE_ROOM);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("trade_points_id", Integer.class);
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
                String tradePointsId = result.getObject("trade_points_id").toString();
                resultList.add(new TradeRoom(id, tradePointsId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
