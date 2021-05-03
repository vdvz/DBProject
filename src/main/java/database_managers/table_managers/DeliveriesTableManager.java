package database_managers.table_managers;

import entities.Delivery;
import entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeliveriesTableManager extends TableManager {


    public DeliveriesTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.DELIVERIES);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("provider_id", Integer.class);
            put("trade_point_id", Integer.class);
            put("count", Integer.class);
            put("deliver_date", Date.class);
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
                String providerId = result.getObject("provider_id").toString();
                String tradePointId = result.getObject("trade_point_id").toString();
                String deliveryDate = result.getDate("delivery_date").toString();

                resultList.add(new Delivery(id, providerId, tradePointId, deliveryDate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
