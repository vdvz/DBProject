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
import utils.table_managers.TradeRoomTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradeRoomInsertionWindowController extends InsertionWindowController {

    private TradeRoomTableManager tableManager = (TradeRoomTableManager) Main.getDatabaseManager().getTableManager(TableNames.TRADE_ROOM);
    private SelectItem tradePointsId;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        tradePointsId = new SelectItem("trade_points_id");

        loadAvailableTradePoints().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(tradePointsId::addItemsToSelect);

        hBox.getChildren().addAll(tradePointsId);

    }

    @Override
    public void insertRow() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(tradePointsId.getColumnName(), tradePointsId.getSelectedItem().getId());

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
