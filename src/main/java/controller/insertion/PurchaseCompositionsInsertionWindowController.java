package controller.insertion;

import Entities.Good;
import init.Main;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.tableManagers.GoodsTableManager;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseCompositionsInsertionWindowController extends InsertionWindowController {


    public PurchaseCompositionsInsertionWindowController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView.setEditable(true);

        TableColumn columnId = new TableColumn("id");
        columnId.setEditable(false);

        TableColumn<Good, Good> columnGood = new TableColumn<>("good");
        columnGood.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnGood.setCellFactory(ComboBoxTableCell.forTableColumn(loadAvailableGoods()));

        TableColumn columnCount = new TableColumn("count");
        TableColumn columnResultPrice = new TableColumn("result_price");

        tableView.getColumns().addAll(columnId, columnGood, columnCount, columnResultPrice);

        columnGood.getColumns().addAll(columnId, columnGood, columnCount);

    }

    private ObservableList<Good> loadAvailableGoods(){
        return ((GoodsTableManager)Main.getDatabaseManager().getTableManager("GOODS")).getGoods();
    }


}
