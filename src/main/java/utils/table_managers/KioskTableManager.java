package utils.table_managers;

import entities.Entity;
import entities.Kiosk;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class KioskTableManager extends TableManager {


    private final static Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("name", String.class);
            put("trade_point", Integer.class);
        }
    };

    public KioskTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.KIOSK);
    }

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
                String name = result.getObject("name").toString();
                String tradePoint = result.getObject("trade_point").toString();
                resultList.add(new Kiosk(id, name, tradePoint));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
