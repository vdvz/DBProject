package utils.tableManagers;

import controller.tables.TableWindowController;
import javafx.scene.control.TableColumn;
import utils.Connection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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


}
