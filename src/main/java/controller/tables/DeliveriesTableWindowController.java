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

public class DeliveriesTableWindowController extends TableWindowController {


    public DeliveriesTableWindowController() {
        super("DELIVERIES");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn columnId = new TableColumn("id");
        TableColumn columnProviderId = new TableColumn("provider_id");
        TableColumn columnTradePointId = new TableColumn("trade_point_id");
        TableColumn columnCount = new TableColumn("count");
        TableColumn columnDeliverDate = new TableColumn("deliver_date");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0).toString()));
        columnProviderId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1).toString()));
        columnTradePointId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2).toString()));
        columnCount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3).toString()));
        columnDeliverDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(4).toString()));

        table.getColumns().addAll(columnId, columnProviderId, columnTradePointId, columnCount, columnDeliverDate);
    }

    @Override
    public void createNewRow() {

    }

}
