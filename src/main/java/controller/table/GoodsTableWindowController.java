package controller.table;

import Entities.Entity;
import Entities.Good;
import controller.Controller;
import controller.insertion.InsertionWindowController;
import init.Main;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.table_managers.GoodsTableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GoodsTableWindowController extends TableWindowController {

    public GoodsTableWindowController() {
        super("GOODS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn<Good, String> columnId = new TableColumn<>("id");
        TableColumn<Good, String> columnName = new TableColumn<>("name");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.getColumns().addAll(columnId, columnName);

        generateTable();
    }
}
