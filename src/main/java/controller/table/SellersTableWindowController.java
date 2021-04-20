package controller.table;

import entities.Seller;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SellersTableWindowController extends TableWindowController {


    public SellersTableWindowController() {
        super("SELLERS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        TableColumn<Seller, String> columnId = new TableColumn<>("id");
        TableColumn<Seller, String> columnName = new TableColumn<>("name");
        TableColumn<Seller, String> columnSalary = new TableColumn<>("salary");
        TableColumn<Seller, String> columnTradePoint = new TableColumn<>("trade_point");
        TableColumn<Seller, String> columnTradeRoom = new TableColumn<>("trade_room");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        columnTradePoint.setCellValueFactory(new PropertyValueFactory<>("tradePoint"));
        columnTradeRoom.setCellValueFactory(new PropertyValueFactory<>("tradeRoom"));

        table.getColumns().addAll(columnId, columnName, columnSalary, columnTradePoint, columnTradeRoom);
        generateTable();
    }

}
