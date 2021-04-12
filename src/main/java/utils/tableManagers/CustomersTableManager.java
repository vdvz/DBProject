package utils.tableManagers;

import utils.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomersTableManager extends TableManager {

    public List<String> columnNames = new ArrayList<String>(){{
        add("id");
        add("name");
    }};

    public CustomersTableManager(Connection connection) {
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
    List<String> getColumnNames() {
        return null;
    }

    @Override
    public void insertRow(Map<String, String> row) {

    }

}
