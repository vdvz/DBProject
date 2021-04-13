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
import utils.table_managers.TradePointsTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradePointsInsertionWindowController extends InsertionWindowController {


    private TradePointsTableManager tableManager = (TradePointsTableManager) Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS);
    private SelectItem typeItem;
    private EnterItem nameItem;
    private EnterItem pointSizeItem;
    private EnterItem rentSizeItem;
    private EnterItem communalPaymentsItem;
    private EnterItem numberOfCountersItem;

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
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(typeItem.getColumnName(), typeItem.getSelectedItem().getId());
        valuesMap.put(nameItem.getColumnName(), nameItem.getEnteredText());
        valuesMap.put(pointSizeItem.getColumnName(), pointSizeItem.getEnteredText());
        valuesMap.put(rentSizeItem.getColumnName(), rentSizeItem.getEnteredText());
        valuesMap.put(communalPaymentsItem.getColumnName(), communalPaymentsItem.getEnteredText());
        valuesMap.put(numberOfCountersItem.getColumnName(), numberOfCountersItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity value) {

    }

    private ObservableList<Entity> loadAvailableTradeTypes(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).getTableRows();
    }

}
