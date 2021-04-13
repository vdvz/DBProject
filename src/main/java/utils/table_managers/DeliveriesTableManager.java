package utils.table_managers;

import Entities.Delivery;
import Entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DeliveriesTableManager extends TableManager {


    public DeliveriesTableManager(Connection connection) {
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
                String providerId = result.getObject("provider_id").toString();
                String tradePointId = result.getObject("trade_point_id").toString();
                String count = result.getObject("count").toString();
                String deliveryDate = result.getObject("delivery_date").toString();

                resultList.add(new Delivery(id, providerId, tradePointId, count, deliveryDate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;

    }

}
