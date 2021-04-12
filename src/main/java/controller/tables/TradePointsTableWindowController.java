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

public class TradePointsTableWindowController extends TableWindowController {


    public TradePointsTableWindowController() {
        super("TRADE_POINTS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn columnId = new TableColumn("id");
        TableColumn columnType = new TableColumn("type");
        TableColumn columnName = new TableColumn("name");
        TableColumn columnPointSize = new TableColumn("point_size");
        TableColumn columnRentSize = new TableColumn("rent_size");
        TableColumn columnCommunalPayments = new TableColumn("communal_payments");
        TableColumn columnNumberOfCounters = new TableColumn("number_of_counters");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnType.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        columnName.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2)));

        columnPointSize.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3)));

        columnRentSize.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(4)));

        columnCommunalPayments.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(5)));

        columnNumberOfCounters.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(6)));

        table.getColumns().addAll(columnId, columnType, columnName, columnPointSize, columnRentSize, columnCommunalPayments, columnNumberOfCounters);
    }

    @Override
    public void createNewRow() {

    }

}
