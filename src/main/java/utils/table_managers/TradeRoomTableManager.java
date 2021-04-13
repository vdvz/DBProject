package utils.table_managers;

import Entities.Entity;
import Entities.TradeRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TradeRoomTableManager extends TableManager {

    public TradeRoomTableManager(Connection connection) {
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
