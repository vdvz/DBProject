package controller.tables;

import init.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class GoodsTableWindowController extends TableWindowController {


    public GoodsTableWindowController() {
        super("GOODS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn columnId = new TableColumn("id");
        TableColumn columnName = new TableColumn("name");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnName.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        table.getColumns().addAll(columnId, columnName);

        generateTable();
    }

    @Override
    public void createNewRow() {

    }
}
