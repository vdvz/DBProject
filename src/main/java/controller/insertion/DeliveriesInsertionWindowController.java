package controller.insertion;

import Entities.*;
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

public class DeliveriesInsertionWindowController extends InsertionWindowController {


    public DeliveriesInsertionWindowController() {
    }

    SelectItem providerIdItem;
    SelectItem tradePointIdItem;
    EnterItem countItem;
    EnterItem deliverDateItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        providerIdItem = new SelectItem("provider_id");
        tradePointIdItem = new SelectItem("trade_point_id");
        countItem = new EnterItem("count");
        deliverDateItem = new EnterItem("deliver_date");

        loadAvailableProviders().stream().map(e->new ChoiceUnit(((Provider)e).getId(), ((Provider)e).getName())).forEach(providerIdItem::addItemsToSelect);
        loadAvailableTradePoints().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(providerIdItem::addItemsToSelect);

        hBox.getChildren().addAll(providerIdItem, tradePointIdItem, countItem, deliverDateItem);

    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();

        insertionMap.put(providerIdItem.getColumnName(), providerIdItem.getSelectedItem().getId());
        insertionMap.put(tradePointIdItem.getColumnName(), tradePointIdItem.getSelectedItem().getId());
        insertionMap.put(countItem.getColumnName(), countItem.getEnteredText());
        insertionMap.put(deliverDateItem.getColumnName(), deliverDateItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.DELIVERIES).insertRow(insertionMap);
    }

    private ObservableList<Entity> loadAvailableProviders(){
        return Main.getDatabaseManager().getTableManager(TableNames.PROVIDERS).getTableRows();
    }
    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

}
