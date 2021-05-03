package database_managers.table_managers;

import entities.Account;
import entities.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountingTableManager extends TableManager {

    public AccountingTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.ACCOUNTING);
    }

    @Override
    public Map<String, Class> getColumns() {
        return columns;
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("trade_point", Integer.class);
            put("good", Integer.class);
            put("count", Integer.class);
            put("price", Integer.class);
        }
    };

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
