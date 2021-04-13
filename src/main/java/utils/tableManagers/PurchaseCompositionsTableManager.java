package utils.tableManagers;

import Entities.Entity;
import Entities.Good;
import Entities.PurchaseComposition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PurchaseCompositionsTableManager extends TableManager {


    public PurchaseCompositionsTableManager(Connection connection) {
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
        System.out.println(row.toString());
    }

    @Override
    public ObservableList<Entity> getTableRows(){
        ObservableList<Entity> resultList = FXCollections.observableArrayList();
        ResultSet result;
        try {
            result = getConnection().executeQuery(selectionQuery);
            while(result.next()){
                String id = result.getObject("id").toString();
                String good = result.getObject("good").toString();
                String count = result.getObject("count").toString();
                String resultPrice = result.getObject("result_price").toString();

                resultList.add(new PurchaseComposition(id, good, count, resultPrice));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }
}
