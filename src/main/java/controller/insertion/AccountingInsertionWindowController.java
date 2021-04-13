package controller.insertion;

import Entities.Account;
import Entities.Entity;
import Entities.Good;
import Entities.TradePoint;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import utils.table_managers.AccountingTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AccountingInsertionWindowController extends InsertionWindowController {

    private final AccountingTableManager tableManager = (AccountingTableManager) Main.getDatabaseManager().getTableManager(TableNames.ACCOUNTING);
    private SelectItem tradePointItem;
    private SelectItem goodItem;
    private EnterItem countItem;
    private EnterItem priceItem;

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
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(countItem.getColumnName(), countItem.getEnteredText());
        valuesMap.put(tradePointItem.getColumnName(), tradePointItem.getSelectedItem().getId());
        valuesMap.put(goodItem.getColumnName(), goodItem.getSelectedItem().getId());
        valuesMap.put(priceItem.getColumnName(), priceItem.getEnteredText());
        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        Account value = (Account) entity;
        getIdItem().setText(value.getId());
        countItem.setText(value.getCount());
        tradePointItem.setSelectItem(value.getTradePoint());
        goodItem.setSelectItem(value.getGood());
        priceItem.setText(value.getPrice());
    }

    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

    private ObservableList<Entity> loadAvailableGoods(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

}
