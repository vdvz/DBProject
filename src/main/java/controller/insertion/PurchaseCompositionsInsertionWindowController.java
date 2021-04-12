package controller.insertion;

import Entities.Good;
import init.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.tableManagers.GoodsTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PurchaseCompositionsInsertionWindowController extends InsertionWindowController {


    public PurchaseCompositionsInsertionWindowController() {
    }


    EnterItem countItem;
    SelectItem goodItem;
    EnterItem resultPriceItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        countItem = new EnterItem("count");
        goodItem = new SelectItem("good");
        resultPriceItem = new EnterItem("result_price");

        loadAvailableGoods().stream().map(e->new ChoiceUnit(e.getId(), e.getName())).forEach(goodItem::addItemsToSelect);

        hBox.getChildren().addAll(countItem, goodItem, resultPriceItem);

    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(countItem.getColumnName(), countItem.getEnteredText());
        insertionMap.put(goodItem.getColumnName(), goodItem.getSelectedItem().getId());
        insertionMap.put(resultPriceItem.getColumnName(), resultPriceItem.getEnteredText());

        Main.getDatabaseManager().getTableManager("PURCHASE_COMPOSITIONS").insertRow(insertionMap);


    }

    private ObservableList<Good> loadAvailableGoods(){
        return ((GoodsTableManager)Main.getDatabaseManager().getTableManager("GOODS")).getGoods();
    }



}
