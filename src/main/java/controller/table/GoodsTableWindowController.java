package controller.table;

import Entities.Good;
import init.Main;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.tableManagers.GoodsTableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class GoodsTableWindowController extends TableWindowController {

    private final GoodsTableManager tableManager = ((GoodsTableManager)Main.getDatabaseManager().getTableManager("GOODS"));

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

    @Override
    public void deleteRow(String id) {
    }

    @Override
    public void generateTable() {
        table.getItems().addAll(tableManager.getGoods());
    }

    @Override
    public void createNewRow() {

    }
}
