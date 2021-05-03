package controller.insertion;

import entities.*;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import database_managers.table_managers.SellersTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SellersInsertionWindowController extends InsertionWindowController {


    private final SellersTableManager tableManager = (SellersTableManager) Main.getDatabaseManager().getTableManager(TableNames.SELLERS);
    private EnterItem nameItem;
    private EnterItem salaryItem;
    private SelectItem tradePointItem;
    private SelectItem tradeRoomItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameItem = new EnterItem("name");
        salaryItem = new EnterItem("salary");
        tradePointItem = new SelectItem("trade_point");
        tradeRoomItem = new SelectItem("trade_room");

        loadAvailableTradePoint().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(tradePointItem::addItemsToSelect);
        loadAvailableTradeRoom().stream().map(e->new ChoiceUnit(((TradeRoom)e).getId(), ((TradeRoom)e).getId())).forEach(tradeRoomItem::addItemsToSelect);

        hBox.getChildren().addAll(nameItem, salaryItem, tradePointItem, tradeRoomItem);
    }

    @Override
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(nameItem.getColumnName(), nameItem.getEnteredText());
        valuesMap.put(salaryItem.getColumnName(), salaryItem.getEnteredText());
        valuesMap.put(tradePointItem.getColumnName(), tradePointItem.getSelectedItem().getId());
        valuesMap.put(tradeRoomItem.getColumnName(), tradeRoomItem.getSelectedItem().getId());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        Seller value = (Seller) entity;
        getIdItem().setText(value.getId());
        nameItem.setText(value.getName());
        salaryItem.setText(value.getSalary());
        tradePointItem.setSelectItem(value.getTradePoint());
        tradeRoomItem.setSelectItem(value.getTradeRoom());
    }

    private ObservableList<Entity> loadAvailableTradePoint(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }
    private ObservableList<Entity> loadAvailableTradeRoom(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_ROOM).getTableRows();
    }

}
