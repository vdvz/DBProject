package utils.table_managers;

import Entities.DeliveriesGood;
import Entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DeliveriesGoodsTableManager extends TableManager {


    public DeliveriesGoodsTableManager(Connection connection) {
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
                String goodId = result.getObject("good_id").toString();
                String deliveryId = result.getObject("delivery_id").toString();
                String price = result.getObject("price").toString();

                resultList.add(new DeliveriesGood(id, providerId, goodId, deliveryId, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;

    }
}
