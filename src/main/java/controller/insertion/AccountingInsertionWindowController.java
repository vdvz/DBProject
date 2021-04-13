package controller.insertion;

import Entities.Entity;
import Entities.Good;
import Entities.TradePoint;
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

public class AccountingInsertionWindowController extends InsertionWindowController {


    public AccountingInsertionWindowController() {
    }

    SelectItem tradePointItem;
    SelectItem goodItem;
    EnterItem countItem;
    EnterItem priceItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        countItem = new EnterItem("count");
        tradePointItem = new SelectItem("trade_point");
        goodItem = new SelectItem("good");
        priceItem = new EnterItem("price");

        loadAvailableGoods().stream().map(e->new ChoiceUnit(((Good)e).getId(), ((Good)e).getName())).forEach(goodItem::addItemsToSelect);
        loadAvailableTradePoints().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(tradePointItem::addItemsToSelect);

        hBox.getChildren().addAll(countItem, tradePointItem, goodItem, priceItem);

    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(countItem.getColumnName(), countItem.getEnteredText());
        insertionMap.put(tradePointItem.getColumnName(), tradePointItem.getSelectedItem().getId());
        insertionMap.put(goodItem.getColumnName(), goodItem.getSelectedItem().getId());
        insertionMap.put(priceItem.getColumnName(), priceItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.ACCOUNTING).insertRow(insertionMap);
    }

    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

    private ObservableList<Entity> loadAvailableGoods(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

}
