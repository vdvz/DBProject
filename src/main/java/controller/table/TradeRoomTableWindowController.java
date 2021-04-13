package controller.table;

import Entities.TradeRoom;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TradeRoomTableWindowController extends TableWindowController {

    public TradeRoomTableWindowController() {
        super("TRADE_ROOM");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<TradeRoom, String> columnId = new TableColumn<>("id");
        TableColumn<TradeRoom, String> columnTradePointsId = new TableColumn<>("trade_points_id");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTradePointsId.setCellValueFactory(new PropertyValueFactory<>("tradePointsId"));

        table.getColumns().addAll(columnId, columnTradePointsId);
    }

    @Override
    public void createNewRow() {

    }

}
