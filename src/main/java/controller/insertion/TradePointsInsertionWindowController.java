package controller.insertion;

import Entities.Entity;
import Entities.Good;
import Entities.TradePoint;
import Entities.TradeType;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradePointsInsertionWindowController extends InsertionWindowController {


    public TradePointsInsertionWindowController() {
    }

    SelectItem typeItem;
    EnterItem nameItem;
    EnterItem pointSizeItem;
    EnterItem rentSizeItem;
    EnterItem communalPaymentsItem;
    EnterItem numberOfCountersItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        typeItem = new SelectItem("type");
        nameItem = new EnterItem("name");
        pointSizeItem = new EnterItem("point_size");
        rentSizeItem = new EnterItem("rent_size");
        communalPaymentsItem = new EnterItem("communal_payments");
        numberOfCountersItem = new EnterItem("number_of_counters");

        loadAvailableTradeTypes().stream().map(e->new ChoiceUnit(((TradeType)e).getId(), ((TradeType)e).getName())).forEach(typeItem::addItemsToSelect);

        hBox.getChildren().addAll(typeItem, nameItem, pointSizeItem, rentSizeItem, communalPaymentsItem, numberOfCountersItem);
    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(typeItem.getColumnName(), typeItem.getSelectedItem().getId());
        insertionMap.put(nameItem.getColumnName(), nameItem.getEnteredText());
        insertionMap.put(pointSizeItem.getColumnName(), pointSizeItem.getEnteredText());
        insertionMap.put(rentSizeItem.getColumnName(), rentSizeItem.getEnteredText());
        insertionMap.put(communalPaymentsItem.getColumnName(), communalPaymentsItem.getEnteredText());
        insertionMap.put(numberOfCountersItem.getColumnName(), numberOfCountersItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).insertRow(insertionMap);
    }

    private ObservableList<Entity> loadAvailableTradeTypes(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).getTableRows();
    }

}
