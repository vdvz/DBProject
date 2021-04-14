package controller.insertion;

import entities.Entity;
import entities.Good;
import entities.PurchaseComposition;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import utils.table_managers.PurchaseCompositionsTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PurchaseCompositionsInsertionWindowController extends InsertionWindowController {

    private final PurchaseCompositionsTableManager tableManager = (PurchaseCompositionsTableManager) Main.getDatabaseManager().getTableManager(TableNames.PURCHASE_COMPOSITIONS);
    private EnterItem countItem;
    private SelectItem goodItem;
    private EnterItem resultPriceItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        countItem = new EnterItem("count");
        goodItem = new SelectItem("good");
        resultPriceItem = new EnterItem("result_price");

        loadAvailableGoods().stream().map(e->new ChoiceUnit(((Good)e).getId(), ((Good)e).getName())).forEach(goodItem::addItemsToSelect);

        hBox.getChildren().addAll(countItem, goodItem, resultPriceItem);

    }

    @Override
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(countItem.getColumnName(), countItem.getEnteredText());
        valuesMap.put(goodItem.getColumnName(), goodItem.getSelectedItem().getId());
        valuesMap.put(resultPriceItem.getColumnName(), resultPriceItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        PurchaseComposition value = (PurchaseComposition) entity;
        getIdItem().setText(value.getId());
        countItem.setText(value.getCount());
        goodItem.setSelectItem(value.getGood());
        resultPriceItem.setText(value.getResultPrice());
    }

    private ObservableList<Entity> loadAvailableGoods(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

}
