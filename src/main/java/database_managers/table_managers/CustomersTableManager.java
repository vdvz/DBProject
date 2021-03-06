package database_managers.table_managers;

import entities.Customer;
import entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomersTableManager extends TableManager {

    public List<String> columnNames = new ArrayList<String>(){{
        add("id");
        add("name");
    }};

    public CustomersTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.CUSTOMERS);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
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
                String age;
                try{
                    age = Objects.requireNonNull(result.getObject("age")).toString();
                } catch (NullPointerException e){
                    age = "NULL";
                }
                resultList.add(new Customer(id, name,age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
