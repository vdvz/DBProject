package controller.table;

import Entities.TradeType;
import controller.Controller;
import init.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TradeTypesTableWindowController extends TableWindowController {


    public TradeTypesTableWindowController() {
        super("TRADE_TYPES");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<TradeType, String> columnId = new TableColumn<>("id");
        TableColumn<TradeType, String> columnName = new TableColumn<>("name");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.getColumns().addAll(columnId, columnName);

    }

}
