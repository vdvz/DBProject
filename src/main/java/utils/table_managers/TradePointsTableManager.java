package utils.table_managers;

import Entities.Entity;
import Entities.TradePoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TradePointsTableManager extends TableManager {

    public TradePointsTableManager(Connection connection) {
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
    public ObservableList<Entity> getTableRows(){
        ObservableList<Entity> resultList = FXCollections.observableArrayList();
        ResultSet result;
        try {
            result = getConnection().executeQuery(selectionQuery);
            while(result.next()){
                String id = result.getObject("id").toString();
                String type = result.getObject("type").toString();
                String name = result.getObject("name").toString();
                String pointSize = result.getObject("point_size").toString();
                String rentSize = result.getObject("rent_size").toString();
                String communalPayments = result.getObject("communal_payments").toString();
                String numberOfCounters = result.getObject("number_of_counters").toString();

                resultList.add(new TradePoint(id, type, name, pointSize, rentSize, communalPayments, numberOfCounters));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
