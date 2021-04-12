package controller.insertions;

import controller.tables.TableWindowController;
import init.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PurchaseCompositionsInsertionWindowController extends InsertionWindowController {


    public PurchaseCompositionsInsertionWindowController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView.setEditable(true);

        TableColumn columnId = new TableColumn("good_id");
        columnId.setEditable(false);
        TableColumn columnGood = new TableColumn("good");
        columnGood.setCellFactory(e->CheckBoxTableCell.<ObservableList<String>>forTableColumn(columnGood));
        TableColumn columnCount = new TableColumn("count");
        TableColumn columnResultPrice = new TableColumn("result_price");

        tableView.getColumns().addAll(columnId, columnGood, columnCount, columnResultPrice);

        columnGood.getColumns().addAll(loadAvailableGoods());
    }

    private ObservableList<String> loadAvailableGoods(){
        try {
            return Main.getDatabaseManager().getTableManager("GOODS").getTableRows().get(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
