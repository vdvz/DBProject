package controller.table;

import entities.Delivery;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DeliveriesTableWindowController extends TableWindowController {


    public DeliveriesTableWindowController() {
        super("DELIVERIES");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<Delivery, String> columnId = new TableColumn<>("id");
        TableColumn<Delivery, String> columnProviderId = new TableColumn<>("provider_id");
        TableColumn<Delivery, String> columnTradePointId = new TableColumn<>("trade_point_id");
        TableColumn<Delivery, String> columnDeliverDate = new TableColumn<>("deliver_date");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProviderId.setCellValueFactory(new PropertyValueFactory<>("providerId"));
        columnTradePointId.setCellValueFactory(new PropertyValueFactory<>("tradePointId"));
        columnDeliverDate.setCellValueFactory(new PropertyValueFactory<>("deliverDate"));

        table.getColumns().addAll(columnId, columnProviderId, columnTradePointId, columnDeliverDate);
        generateTable();
    }

}
