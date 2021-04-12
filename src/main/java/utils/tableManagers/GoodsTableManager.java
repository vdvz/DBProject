package utils.tableManagers;

import controller.Entities.Good;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsTableManager extends TableManager {


    private final List<String> columnNames = new ArrayList<String>(){
        {
            add("id");
            add("name");
        }
    };

    public GoodsTableManager(Connection connection) {
        super(connection);
    }

    @Override
    public String loadSelectionQuery() {
        return "SELECT * FROM goods";
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
        return columnNames;
    }

    public ObservableList<Good> getGoods(){
        ObservableList<Good> resultList = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            result = getConnection().executeQuery("select id, name from goods");
            while(result.next()){
                String id = result.getObject("id").toString();
                String displayedName = result.getObject("name").toString();
                resultList.add(new Good(id, displayedName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }




}
