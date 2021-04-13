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

public class SalesInsertionWindowController extends InsertionWindowController {


    public SalesInsertionWindowController() {
    }

    SelectItem sellerItem;
    SelectItem customerItem;
    SelectItem purchaseCompositionItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        sellerItem = new SelectItem("seller");
        customerItem = new SelectItem("customer");
        purchaseCompositionItem = new SelectItem("purchase_composition");

        loadAvailableSellers().stream().map(e->new ChoiceUnit(((Seller)e).getId(), ((Seller)e).getName())).forEach(sellerItem::addItemsToSelect);
        loadAvailableCustomers().stream().map(e->new ChoiceUnit(((Customer)e).getId(), ((Customer)e).getName())).forEach(customerItem::addItemsToSelect);
        loadAvailablePurchaseCompositions().stream().map(e->new ChoiceUnit(((PurchaseComposition)e).getId(), ((PurchaseComposition)e).getId())).forEach(purchaseCompositionItem::addItemsToSelect);

        hBox.getChildren().addAll(sellerItem, customerItem, purchaseCompositionItem);
    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(sellerItem.getColumnName(), sellerItem.getSelectedItem().getId());
        insertionMap.put(customerItem.getColumnName(), customerItem.getSelectedItem().getId());
        insertionMap.put(purchaseCompositionItem.getColumnName(), purchaseCompositionItem.getSelectedItem().getId());

        Main.getDatabaseManager().getTableManager(TableNames.SALES).insertRow(insertionMap);
    }

    private ObservableList<Entity> loadAvailableSellers(){
        return Main.getDatabaseManager().getTableManager(TableNames.SELLERS).getTableRows();
    }

    private ObservableList<Entity> loadAvailableCustomers(){
        return Main.getDatabaseManager().getTableManager(TableNames.CUSTOMERS).getTableRows();
    }

    private ObservableList<Entity> loadAvailablePurchaseCompositions(){
        return Main.getDatabaseManager().getTableManager(TableNames.PURCHASE_COMPOSITIONS).getTableRows();
    }

}
