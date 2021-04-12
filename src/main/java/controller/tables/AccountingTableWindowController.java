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

public class AccountingTableWindowController extends TableWindowController {


    public AccountingTableWindowController() {
        super("ACCOUNTING");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn columnId = new TableColumn("id");
        TableColumn columnTradePoint = new TableColumn("trade_point");
        TableColumn columnGood = new TableColumn("good");
        TableColumn columnCount = new TableColumn("count");
        TableColumn columnPrice = new TableColumn("price");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnTradePoint.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        columnGood.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2)));

        columnCount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3)));

        columnPrice.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(4)));


        table.getColumns().addAll(columnId, columnTradePoint, columnGood, columnCount, columnPrice);

    }

    @Override
    public void createNewRow() {

    }

}
