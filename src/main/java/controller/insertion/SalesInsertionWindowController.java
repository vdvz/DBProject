package controller.insertion;

import entities.*;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.SelectItem;
import utils.TableNames;
import database_managers.table_managers.SalesTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SalesInsertionWindowController extends InsertionWindowController {

    private final SalesTableManager tableManager = (SalesTableManager) Main.getDatabaseManager().getTableManager(TableNames.SALES);
    private SelectItem sellerItem;
    private SelectItem customerItem;
    private SelectItem purchaseCompositionItem;

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
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(sellerItem.getColumnName(), sellerItem.getSelectedItem().getId());
        valuesMap.put(customerItem.getColumnName(), customerItem.getSelectedItem().getId());
        valuesMap.put(purchaseCompositionItem.getColumnName(), purchaseCompositionItem.getSelectedItem().getId());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        Sale value = (Sale) entity;
        getIdItem().setText(value.getId());
        sellerItem.setSelectItem(value.getSeller());
        customerItem.setSelectItem(value.getCustomer());
        purchaseCompositionItem.setSelectItem(value.getPurchaseComposition());
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
