package utils.table_managers;

import Entities.Account;
import Entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AccountingTableManager extends TableManager {

    public AccountingTableManager(Connection connection) {
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
                String tradePoint = result.getObject("trade_point").toString();
                String good = result.getObject("good").toString();
                String count = result.getObject("count").toString();
                String price = result.getObject("price").toString();

                resultList.add(new Account(id, tradePoint, good, count, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
