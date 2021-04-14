package controller.table;

import entities.Sale;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesTableWindowController extends TableWindowController {

    public SalesTableWindowController() {
        super("SALES");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<Sale, String> columnId = new TableColumn<>("id");
        TableColumn<Sale, String> columnSeller = new TableColumn<>("seller");
        TableColumn<Sale, String> columnCustomer = new TableColumn<>("customer");
        TableColumn<Sale, String> columnPurchaseComposition = new TableColumn<>("purchase_composition");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnSeller.setCellValueFactory(new PropertyValueFactory<>("seller"));
        columnCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        columnPurchaseComposition.setCellValueFactory(new PropertyValueFactory<>("purchaseComposition"));

        table.getColumns().addAll(columnId, columnSeller, columnCustomer, columnPurchaseComposition);
    }

}
