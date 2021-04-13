package controller.insertion;

import Entities.Entity;
import Entities.Good;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import utils.tableManagers.GoodsTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SellersInsertionWindowController extends InsertionWindowController {


    public SellersInsertionWindowController() {
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

        loadAvailableGoods().stream().map(e->new ChoiceUnit(((Good)e).getId(), ((Good)e).getName())).forEach(goodItem::addItemsToSelect);

        hBox.getChildren().addAll(countItem, goodItem, resultPriceItem);

    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(countItem.getColumnName(), countItem.getEnteredText());
        insertionMap.put(goodItem.getColumnName(), goodItem.getSelectedItem().getId());
        insertionMap.put(resultPriceItem.getColumnName(), resultPriceItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.SELLERS).insertRow(insertionMap);

    }

    private ObservableList<Entity> loadAvailableGoods(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

}
