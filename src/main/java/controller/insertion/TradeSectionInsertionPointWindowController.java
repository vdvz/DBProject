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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradeSectionInsertionPointWindowController extends InsertionWindowController {

    public TradeSectionInsertionPointWindowController() {
    }
    SelectItem tradePointsItem;
    EnterItem floorItem;
    EnterItem managersNameItem;
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
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(tradePointsItem.getColumnName(), tradePointsItem.getSelectedItem().getId());
        insertionMap.put(floorItem.getColumnName(), floorItem.getEnteredText());
        insertionMap.put(managersNameItem.getColumnName(), managersNameItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.TRADE_SECTION).insertRow(insertionMap);

    }

    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

}
