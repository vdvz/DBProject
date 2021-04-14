package utils.table_managers;

import entities.Entity;
import entities.TradePoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TradePointsTableManager extends TableManager {

    public TradePointsTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.TRADE_POINTS);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("type", Integer.class);
            put("name", String.class);
            put("point_size", Integer.class);
            put("rent_price", Integer.class);
            put("communal_payments", Integer.class);
            put("number_of_counters", Integer.class);
        }
    };
    @Override
    public Map<String, Class> getColumns() {
        return columns;
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
