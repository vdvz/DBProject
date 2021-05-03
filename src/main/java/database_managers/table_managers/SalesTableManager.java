package database_managers.table_managers;

import entities.Entity;
import entities.PurchaseComposition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;
import utils.TableNames;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalesTableManager extends TableManager {


    public SalesTableManager(Connection connection) throws SQLException {
        super(connection, TableNames.SALES);
    }

    private static final Map<String, Class> columns = new LinkedHashMap<String, Class>(){
        {
            put("seller", Integer.class);
            put("customer", Integer.class);
            put("result_price", Integer.class);
            put("purchase_date", Date.class);
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
                String seller = result.getObject("seller").toString();
                String customer = result.getObject("customer").toString();
                String purchaseComposition = result.getObject("purchase_composition").toString();
                String purchase_date = result.getObject("purchase_date").toString();

                resultList.add(new PurchaseComposition(id, seller, customer, purchaseComposition, purchase_date));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
