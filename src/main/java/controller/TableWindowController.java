package controller;

import init.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DatabaseManager;

import java.net.URL;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TableWindowController extends BaseController implements Initializable {

    public static final String MAIN_WINDOW_FXML = "/table_window.fxml";
    private final DatabaseManager manager;

    @FXML
    public TableView table;
    private final String tableName;
    TableWindowController(String tableName){
        manager = new DatabaseManager(Main.getConnection());
        this.tableName = tableName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Map.Entry<String, List<String>>> list = manager.getTable(tableName);

        for (Map.Entry<String, List<String>> entry : list) {
            TableColumn column = new TableColumn(entry.getKey());

            column.setCellValueFactory(new PropertyValueFactory<String, String>(entry.getKey()));
            table.getColumns().add(column);
        }

    }


    public void generateTable(){
        exe
        ResultSetMetaData rsmd = rs.getMetaData();
        String name = rsmd.getColumnName(1);

    }

    public void back(){

    }

    public void createNewRow(){

    }

    public void update(ActionEvent actionEvent) {

    }
}
