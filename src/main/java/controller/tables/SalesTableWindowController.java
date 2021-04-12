package controller.tables;

import TableRows.ProviderTableRow;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesTableWindowController extends TableWindowController {

    public SalesTableWindowController() {
        super("SALES");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn columnId = new TableColumn("id");
        TableColumn columnSeller = new TableColumn("seller");
        TableColumn columnCustomer = new TableColumn("customer");
        TableColumn columnPurchaseComposition = new TableColumn("purchase_composition");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnSeller.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        columnCustomer.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2)));

        columnPurchaseComposition.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3)));

        table.getColumns().addAll(columnId, columnSeller, columnCustomer, columnPurchaseComposition);
    }

    @Override
    public void createNewRow() {

    }

}
