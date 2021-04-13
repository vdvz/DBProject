package controller.table;

import Entities.TradeSectionPoint;
import controller.Controller;
import init.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TradeSectionPointTableWindowController extends TableWindowController {

    public TradeSectionPointTableWindowController() {
        super("TRADE_SECTION_POINT");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<TradeSectionPoint, String> columnId = new TableColumn<>("id");
        TableColumn<TradeSectionPoint, String> columnTradePoint = new TableColumn<>("trade_point");
        TableColumn<TradeSectionPoint, String> columnFloor = new TableColumn<>("floor");
        TableColumn<TradeSectionPoint, String> columnManagerName = new TableColumn<>("managers_name");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTradePoint.setCellValueFactory(new PropertyValueFactory<>("tradePoint"));
        columnFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        columnManagerName.setCellValueFactory(new PropertyValueFactory<>("managersName"));

        table.getColumns().addAll(columnId, columnTradePoint, columnFloor, columnManagerName);
    }

}
