package controller.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
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

        TableColumn columnId = new TableColumn("id");
        TableColumn columnTradePointsId = new TableColumn("trade_points_id");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnTradePointsId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        table.getColumns().addAll(columnId, columnTradePointsId);
    }

    @Override
    public void deleteRow(String id) {

    }

    @Override
    public void createNewRow() {

    }

}
