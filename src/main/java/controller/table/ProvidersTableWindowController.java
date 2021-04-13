package controller.table;

import Entities.Provider;
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

public class ProvidersTableWindowController extends TableWindowController {


    public ProvidersTableWindowController() {
        super("PROVIDERS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn<Provider, String> columnId = new TableColumn<>("id");
        TableColumn<Provider, String> columnName = new TableColumn<>("name");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.getColumns().addAll(columnId, columnName);
    }

}
