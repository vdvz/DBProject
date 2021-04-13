package utils.table_managers;

import Entities.Customer;
import Entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomersTableManager extends TableManager {

    public List<String> columnNames = new ArrayList<String>(){{
        add("id");
        add("name");
    }};

    public CustomersTableManager(Connection connection) throws SQLException {
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

    private final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("name", String.class);
            put("age", Integer.class);
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
                String name = result.getObject("name").toString();
                String age = result.getObject("age").toString();

                resultList.add(new Customer(id, name,age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
