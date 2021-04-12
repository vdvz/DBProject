package controller.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
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

        TableColumn columnId = new TableColumn("id");
        TableColumn columnProviderId = new TableColumn("provider_id");
        TableColumn columnGoodId = new TableColumn("good_id");
        TableColumn columnDeliveryName = new TableColumn("delivery_id");
        TableColumn columnPrice = new TableColumn("price");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0).toString()));
        columnProviderId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1).toString()));
        columnGoodId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2).toString()));
        columnDeliveryName.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3).toString()));
        columnPrice.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(4).toString()));

        table.getColumns().addAll(columnId, columnProviderId, columnGoodId, columnDeliveryName, columnPrice);
    }

    @Override
    public void createNewRow() {
    }

}
