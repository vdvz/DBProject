package controller.table;

import Entities.PurchaseComposition;
import controller.Controller;
import init.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseCompositionsTableWindowController extends TableWindowController {


    public PurchaseCompositionsTableWindowController() {
        super("PURCHASE_COMPOSITIONS");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        TableColumn<PurchaseComposition, String> columnId = new TableColumn<>("good_id");
        TableColumn<PurchaseComposition, String> columnGood = new TableColumn<>("good");
        TableColumn<PurchaseComposition, String> columnCount = new TableColumn<>("count");
        TableColumn<PurchaseComposition, String> columnResultPrice = new TableColumn<>("result_price");

        columnId.setCellValueFactory(new PropertyValueFactory<>("goodId"));
        columnGood.setCellValueFactory(new PropertyValueFactory<>("good"));
        columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        columnResultPrice.setCellValueFactory(new PropertyValueFactory<>("resultPrice"));

        table.getColumns().addAll(columnId, columnGood, columnCount, columnResultPrice);
    }

}
