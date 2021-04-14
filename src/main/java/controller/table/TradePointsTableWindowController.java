package controller.table;

import entities.TradePoint;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TradePointsTableWindowController extends TableWindowController {


    public TradePointsTableWindowController() {
        super("TRADE_POINTS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<TradePoint, String> columnId = new TableColumn<>("id");
        TableColumn<TradePoint, String> columnType = new TableColumn<>("type");
        TableColumn<TradePoint, String> columnName = new TableColumn<>("name");
        TableColumn<TradePoint, String> columnPointSize = new TableColumn<>("point_size");
        TableColumn<TradePoint, String> columnRentSize = new TableColumn<>("rent_size");
        TableColumn<TradePoint, String> columnCommunalPayments = new TableColumn<>("communal_payments");
        TableColumn<TradePoint, String> columnNumberOfCounters = new TableColumn<>("number_of_counters");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPointSize.setCellValueFactory(new PropertyValueFactory<>("pointSize"));
        columnRentSize.setCellValueFactory(new PropertyValueFactory<>("rentSize"));
        columnCommunalPayments.setCellValueFactory(new PropertyValueFactory<>("communalPayments"));
        columnNumberOfCounters.setCellValueFactory(new PropertyValueFactory<>("numberOfCounters"));

        table.getColumns().addAll(columnId, columnType, columnName, columnPointSize, columnRentSize, columnCommunalPayments, columnNumberOfCounters);
    }
}
