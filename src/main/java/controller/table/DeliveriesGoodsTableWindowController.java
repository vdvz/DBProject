package controller.table;

import Entities.DeliveriesGood;
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

public class DeliveriesGoodsTableWindowController extends TableWindowController {


    public DeliveriesGoodsTableWindowController() {
        super("DELIVERIES_GOODS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<DeliveriesGood, String> columnId = new TableColumn<>("id");
        TableColumn<DeliveriesGood, String> columnProviderId = new TableColumn<>("provider_id");
        TableColumn<DeliveriesGood, String> columnGoodId = new TableColumn<>("good_id");
        TableColumn<DeliveriesGood, String> columnDeliveryName = new TableColumn<>("delivery_id");
        TableColumn<DeliveriesGood, String> columnPrice = new TableColumn<>("price");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProviderId.setCellValueFactory(new PropertyValueFactory<>("providerId"));
        columnGoodId.setCellValueFactory(new PropertyValueFactory<>("goodId"));
        columnDeliveryName.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(columnId, columnProviderId, columnGoodId, columnDeliveryName, columnPrice);
    }

  @Override
    public void createNewRow() {
        Controller controller = Main.getNavigation().loadTable("/insertion_window.fxml", "controller.insertion.DeliveriesGoodsInsertionWindowController");
        Main.getNavigation().show(controller, Main.getNavigation().createNewStage());
    }

}
