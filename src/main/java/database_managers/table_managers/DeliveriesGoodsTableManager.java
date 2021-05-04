package database_managers.table_managers;

import entities.DeliveriesGood;
import entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeliveriesGoodsTableManager extends TableManager {

    public DeliveriesGoodsTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.DELIVERIES_GOODS);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            //put("provider_id", Integer.class);
            put("good_id", Integer.class);
            put("delivery_id", Integer.class);
            put("count", Integer.class);
            put("price", Integer.class);
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
                //String providerId = result.getObject("provider_id").toString();
                String goodId = result.getObject("good_id").toString();
                String deliveryId = result.getObject("delivery_id").toString();
                String count = result.getObject("count").toString();
                String price = result.getObject("price").toString();

                resultList.add(new DeliveriesGood(id/*, providerId*/, goodId, deliveryId,count, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;

    }
}
