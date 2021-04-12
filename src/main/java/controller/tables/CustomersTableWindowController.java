package controller.tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomersTableWindowController extends TableWindowController {

    public CustomersTableWindowController() {
        super("CUSTOMERS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn columnId = new TableColumn("id");
        TableColumn columnName = new TableColumn("name");
        TableColumn columnAge = new TableColumn("age");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnName.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        columnAge.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3)));

        table.getColumns().addAll(columnId, columnName, columnAge);
    }

    @Override
    public void createNewRow() {

    }

}
