package utils.table_managers;

import entities.Entity;
import entities.Good;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class GoodsTableManager extends TableManager {


    private final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("name", String.class);
        }
    };

    public GoodsTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.GOODS);
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
                resultList.add(new Good(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
