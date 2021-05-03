package controller.insertion;

import entities.*;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import database_managers.table_managers.TradePointsTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradePointsInsertionWindowController extends InsertionWindowController {


    private TradePointsTableManager tableManager = (TradePointsTableManager) Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS);
    private SelectItem typeItem;
    private EnterItem nameItem;
    private EnterItem pointSizeItem;
    private EnterItem rentPriceItem;
    private EnterItem communalPaymentsItem;
    private EnterItem numberOfCountersItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        typeItem = new SelectItem("type");
        nameItem = new EnterItem("name");
        pointSizeItem = new EnterItem("point_size");
        rentPriceItem = new EnterItem("rent_price");
        communalPaymentsItem = new EnterItem("communal_payments");
        numberOfCountersItem = new EnterItem("number_of_counters");

        loadAvailableTradeTypes().stream().map(e->new ChoiceUnit(((TradeType)e).getId(), ((TradeType)e).getName())).forEach(typeItem::addItemsToSelect);

        hBox.getChildren().addAll(typeItem, nameItem, pointSizeItem, rentPriceItem, communalPaymentsItem, numberOfCountersItem);
    }

    @Override
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(typeItem.getColumnName(), typeItem.getSelectedItem().getId());
        valuesMap.put(nameItem.getColumnName(), nameItem.getEnteredText());
        valuesMap.put(pointSizeItem.getColumnName(), pointSizeItem.getEnteredText());
        valuesMap.put(rentPriceItem.getColumnName(), rentPriceItem.getEnteredText());
        valuesMap.put(communalPaymentsItem.getColumnName(), communalPaymentsItem.getEnteredText());
        valuesMap.put(numberOfCountersItem.getColumnName(), numberOfCountersItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        TradePoint value = (TradePoint) entity;
        getIdItem().setText(value.getId());
        typeItem.setSelectItem(value.getType());
        nameItem.setText(value.getName());
        pointSizeItem.setText(value.getPointSize());
        rentPriceItem.setText(value.getRentPrice());
        communalPaymentsItem.setText(value.getCommunalPayments());
        numberOfCountersItem.setText(value.getNumberOfCounters());
    }

    private ObservableList<Entity> loadAvailableTradeTypes(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).getTableRows();
    }

}
