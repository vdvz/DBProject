package controller.insertion;

import entities.*;
import init.Main;
import javafx.collections.ObservableList;
import utils.*;
import database_managers.table_managers.DeliveriesTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DeliveriesInsertionWindowController extends InsertionWindowController {

    private final DeliveriesTableManager tableManager = (DeliveriesTableManager) Main.getDatabaseManager().getTableManager(TableNames.DELIVERIES);
    private SelectItem providerIdItem;
    private SelectItem tradePointIdItem;
    private DateItem deliverDateItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        providerIdItem = new SelectItem("provider_id");
        tradePointIdItem = new SelectItem("trade_point_id");
        deliverDateItem = new DateItem("deliver_date");

        loadAvailableProviders().stream().map(e->new ChoiceUnit(((Provider)e).getId(), ((Provider)e).getName())).forEach(providerIdItem::addItemsToSelect);
        loadAvailableTradePoints().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(providerIdItem::addItemsToSelect);

        hBox.getChildren().addAll(providerIdItem, tradePointIdItem, deliverDateItem);

    }

    @Override
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(providerIdItem.getColumnName(), providerIdItem.getSelectedItem().getId());
        valuesMap.put(tradePointIdItem.getColumnName(), tradePointIdItem.getSelectedItem().getId());
        valuesMap.put(deliverDateItem.getColumnName(), deliverDateItem.getDate().toString());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        Delivery value = (Delivery) entity;
        getIdItem().setText(value.getId());
        providerIdItem.setSelectItem(value.getProviderId());
        tradePointIdItem.setSelectItem(value.getTradePointId());
        deliverDateItem.setDate(value.getDeliverDate());
    }

    private ObservableList<Entity> loadAvailableProviders(){
        return Main.getDatabaseManager().getTableManager(TableNames.PROVIDERS).getTableRows();
    }
    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

}
