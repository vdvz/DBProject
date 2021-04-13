package controller.table;

import Entities.Delivery;
import controller.Controller;
import init.Main;
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

        TableColumn<Delivery, String> columnId = new TableColumn<>("id");
        TableColumn<Delivery, String> columnProviderId = new TableColumn<>("provider_id");
        TableColumn<Delivery, String> columnTradePointId = new TableColumn<>("trade_point_id");
        TableColumn<Delivery, String> columnCount = new TableColumn<>("count");
        TableColumn<Delivery, String> columnDeliverDate = new TableColumn<>("deliver_date");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProviderId.setCellValueFactory(new PropertyValueFactory<>("providerId"));
        columnTradePointId.setCellValueFactory(new PropertyValueFactory<>("tradePointId"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        columnDeliverDate.setCellValueFactory(new PropertyValueFactory<>("deliverDate"));

        table.getColumns().addAll(columnId, columnProviderId, columnTradePointId, columnCount, columnDeliverDate);
    }




    @Override
    public void createNewRow() {
        Controller controller = Main.getNavigation().loadTable("/insertion_window.fxml", "controller.insertion.DeliveriesInsertionWindowController");
        Main.getNavigation().show(controller, Main.getNavigation().createNewStage());
    }

}
