package utils.table_managers;

import entities.Entity;
import entities.Seller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SellersTableManager extends TableManager {


    public SellersTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.SELLERS);
    }

    private final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("name", String.class);
            put("salary", Integer.class);
            put("trade_point", Integer.class);
            put("trade_room", Integer.class);
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
                String name = result.getObject("name").toString();
                String salary = result.getObject("salary").toString();
                String tradePoint = result.getObject("trade_point").toString();
                String tradeRoom = result.getObject("trade_room").toString();

                resultList.add(new Seller(id, name, salary, tradePoint, tradeRoom));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }
}
