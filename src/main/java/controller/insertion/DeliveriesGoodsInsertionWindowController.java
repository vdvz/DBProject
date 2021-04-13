package controller.insertion;

import Entities.*;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import utils.table_managers.DeliveriesGoodsTableManager;
import utils.table_managers.DeliveriesTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DeliveriesGoodsInsertionWindowController extends InsertionWindowController {


    private SelectItem providerIdItem;
    private SelectItem goodIdItem;
    private SelectItem deliveryIdItem;
    private EnterItem priceItem;
    private final DeliveriesGoodsTableManager tableManager = (DeliveriesGoodsTableManager) Main.getDatabaseManager().getTableManager(TableNames.DELIVERIES_GOODS);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        providerIdItem = new SelectItem("provider_id");
        goodIdItem = new SelectItem("good_id");
        deliveryIdItem = new SelectItem("delivery_id");
        priceItem = new EnterItem("price");

        loadAvailableProviders().stream().map(e->new ChoiceUnit(((Provider)e).getId(), ((Provider)e).getName())).forEach(providerIdItem::addItemsToSelect);
        loadAvailableGoods().stream().map(e->new ChoiceUnit(((Good)e).getId(), ((Good)e).getName())).forEach(goodIdItem::addItemsToSelect);
        loadAvailableDeliveries().stream().map(e->new ChoiceUnit(((Delivery)e).getId(), ((Delivery)e).getId())).forEach(deliveryIdItem::addItemsToSelect);

        hBox.getChildren().addAll(providerIdItem, goodIdItem, deliveryIdItem, priceItem);
    }

    @Override
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(providerIdItem.getColumnName(), providerIdItem.getSelectedItem().getId());
        valuesMap.put(goodIdItem.getColumnName(), goodIdItem.getSelectedItem().getId());
        valuesMap.put(deliveryIdItem.getColumnName(), deliveryIdItem.getSelectedItem().getId());
        valuesMap.put(priceItem.getColumnName(), priceItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        DeliveriesGood value = (DeliveriesGood) entity;
        getIdItem().setText(value.getId());
        providerIdItem.setSelectItem(value.getProviderId());
        goodIdItem.setSelectItem(value.getGoodId());
        deliveryIdItem.setSelectItem(value.getDeliveryId());
        priceItem.setText(value.getPrice());
    }

    private ObservableList<Entity> loadAvailableGoods(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

    private ObservableList<Entity> loadAvailableDeliveries(){
        return Main.getDatabaseManager().getTableManager(TableNames.DELIVERIES).getTableRows();
    }

    private ObservableList<Entity> loadAvailableProviders(){
        return Main.getDatabaseManager().getTableManager(TableNames.PROVIDERS).getTableRows();
    }

}
