package utils.table_managers;

import entities.Entity;
import entities.PurchaseComposition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PurchaseCompositionsTableManager extends TableManager {


    public PurchaseCompositionsTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.PURCHASE_COMPOSITIONS);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("good", Integer.class);
            put("count", Integer.class);
            put("result_price", Integer.class);
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
