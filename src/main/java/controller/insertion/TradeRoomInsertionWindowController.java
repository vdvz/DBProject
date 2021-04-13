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

public class TradeRoomInsertionWindowController extends InsertionWindowController {


    public TradeRoomInsertionWindowController() {
    }

    SelectItem tradePointsId;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        tradePointsId = new SelectItem("trade_points_id");

        loadAvailableTradePoints().stream().map(e->new ChoiceUnit(((TradePoint)e).getId(), ((TradePoint)e).getName())).forEach(tradePointsId::addItemsToSelect);

        hBox.getChildren().addAll(tradePointsId);

    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(tradePointsId.getColumnName(), tradePointsId.getSelectedItem().getId());

        Main.getDatabaseManager().getTableManager(TableNames.SELLERS).insertRow(insertionMap);
    }

    private ObservableList<Entity> loadAvailableTradePoints(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

}
