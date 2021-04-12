package controller.table;

import controller.Controller;
import init.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
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

        TableColumn columnId = new TableColumn("good_id");
        TableColumn columnGood = new TableColumn("good");
        TableColumn columnCount = new TableColumn("count");
        TableColumn columnResultPrice = new TableColumn("result_price");

        columnId.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(0)));

        columnGood.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(1)));

        columnCount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(2)));

        columnResultPrice.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>)
                param -> new SimpleStringProperty(param.getValue().get(3)));

        table.getColumns().addAll(columnId, columnGood, columnCount, columnResultPrice);
    }

    @Override
    public void createNewRow() {
        try {
            Controller controller = Main.getNavigation().loadTable("/insertion_window", "controller.insertions.PurchaseCompositionsInsertionWindowController");
            Main.getNavigation().show(controller, Main.getNavigation().createNewStage());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }


}
