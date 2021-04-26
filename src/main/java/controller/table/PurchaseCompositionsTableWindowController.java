package controller.table;

import entities.PurchaseComposition;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseCompositionsTableWindowController extends TableWindowController {


    public PurchaseCompositionsTableWindowController() {
        super("PURCHASE_COMPOSITIONS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<PurchaseComposition, String> columnId = new TableColumn<>("id");
        TableColumn<PurchaseComposition, String> columnGood = new TableColumn<>("good");
        TableColumn<PurchaseComposition, String> columnCount = new TableColumn<>("count");
        TableColumn<PurchaseComposition, String> columnResultPrice = new TableColumn<>("result_price");
        TableColumn<PurchaseComposition, String> columnPurchaseDate = new TableColumn<>("purchase_date");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnGood.setCellValueFactory(new PropertyValueFactory<>("good"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        columnResultPrice.setCellValueFactory(new PropertyValueFactory<>("resultPrice"));
        columnPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.getColumns().addAll(columnId, columnGood, columnCount, columnResultPrice, columnPurchaseDate);
        generateTable();
    }

}
