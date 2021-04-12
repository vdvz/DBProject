package controller.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class SellersTableWindowController extends TableWindowController {


    public SellersTableWindowController() {
        super("SELLERS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn columnId = new TableColumn("id");
        TableColumn columnName = new TableColumn("name");
        TableColumn columnSalary = new TableColumn("salary");
        TableColumn columnTradePoint = new TableColumn("trade_point");
        TableColumn columnTradeRoom = new TableColumn("trade_room");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnName.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        columnSalary.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2)));

        columnTradePoint.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3)));

        columnTradeRoom.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(4)));


        table.getColumns().addAll(columnId, columnName, columnSalary, columnTradePoint, columnTradeRoom);
    }

    @Override
    public void createNewRow() {

    }

}
