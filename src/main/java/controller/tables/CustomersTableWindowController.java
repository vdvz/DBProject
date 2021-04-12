package controller.tables;

import controller.Entities.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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
        TableColumn<Customer, String> columnId = new TableColumn<>("id");
        TableColumn<Customer, String> columnName = new TableColumn<>("name");
        TableColumn<Customer, String> columnAge = new TableColumn<>("age");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(columnId, columnName, columnAge);
    }

    @Override
    public void createNewRow() {

    }

}
