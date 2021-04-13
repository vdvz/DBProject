package controller.insertion;

import Entities.Entity;
import Entities.Good;
import Entities.TradePoint;
import init.Main;
import javafx.collections.ObservableList;
import utils.ChoiceUnit;
import utils.EnterItem;
import utils.SelectItem;
import utils.TableNames;
import utils.table_managers.TradeSectionPointTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradeSectionPointInsertionWindowController extends InsertionWindowController {

    private TradeSectionPointTableManager tableManager = (TradeSectionPointTableManager) Main.getDatabaseManager().getTableManager(TableNames.TRADE_SECTION_POINT);
    private SelectItem tradePointsItem;
    private EnterItem floorItem;
    private EnterItem managersNameItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        tradePointsItem = new SelectItem("trade_point");
        floorItem = new EnterItem("floor");
        managersNameItem = new EnterItem("managers_name");

        loadAvailableTradePoints().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(tradePointsItem::addItemsToSelect);

        hBox.getChildren().addAll(tradePointsItem, floorItem, managersNameItem);

    }

    @Override
    public void insertRow() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(tradePointsItem.getColumnName(), tradePointsItem.getSelectedItem().getId());
        valuesMap.put(floorItem.getColumnName(), floorItem.getEnteredText());
        valuesMap.put(managersNameItem.getColumnName(), managersNameItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }

    }

    @Override
    public void initUpdating(Entity value) {

    }

    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

}
