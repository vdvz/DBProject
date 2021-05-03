package controller.insertion;

import entities.Entity;
import entities.TradePoint;
import entities.TradeSectionPoint;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import database_managers.table_managers.TradeSectionPointTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradeSectionPointInsertionWindowController extends InsertionWindowController {

    private TradeSectionPointTableManager tableManager = (TradeSectionPointTableManager) Main.getDatabaseManager().getTableManager(TableNames.TRADE_SECTION_POINT);
    private SelectItem tradePointItem;
    private EnterItem floorItem;
    private EnterItem managersNameItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        tradePointItem = new SelectItem("trade_point");
        floorItem = new EnterItem("floor");
        managersNameItem = new EnterItem("managers_name");

        loadAvailableStores().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(
            tradePointItem::addItemsToSelect);

        hBox.getChildren().addAll(tradePointItem, floorItem, managersNameItem);

    }

    @Override
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(tradePointItem.getColumnName(), tradePointItem.getSelectedItem().getId());
        valuesMap.put(floorItem.getColumnName(), floorItem.getEnteredText());
        valuesMap.put(managersNameItem.getColumnName(), managersNameItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }

    }

    @Override
    public void initUpdating(Entity entity) {
        TradeSectionPoint value = (TradeSectionPoint) entity;
        getIdItem().setText(value.getId());
        tradePointItem.setSelectItem(value.getStore());
        floorItem.setText(value.getFloor());
        managersNameItem.setText(value.getManagersName());
    }

    private ObservableList<Entity> loadAvailableStores(){
        return Main.getDatabaseManager().getTableManager(TableNames.STORES).getTableRows();
    }

}
