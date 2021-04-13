package controller.insertion;

import Entities.Delivery;
import Entities.Entity;
import Entities.Good;
import Entities.Provider;
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

public class DeliveriesGoodsInsertionWindowController extends InsertionWindowController {


    public DeliveriesGoodsInsertionWindowController() {
    }

    SelectItem providerIdItem;
    SelectItem goodIdItem;
    SelectItem deliveryIdItem;
    EnterItem priceItem;

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
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(providerIdItem.getColumnName(), providerIdItem.getSelectedItem().getId());
        insertionMap.put(goodIdItem.getColumnName(), goodIdItem.getSelectedItem().getId());
        insertionMap.put(deliveryIdItem.getColumnName(), deliveryIdItem.getSelectedItem().getId());
        insertionMap.put(priceItem.getColumnName(), priceItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.DELIVERIES_GOODS).insertRow(insertionMap);
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
