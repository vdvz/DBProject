package controller.insertion;

import Entities.Entity;
import Entities.Good;
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

public class TradeTypesInsertionWindowController extends InsertionWindowController {


    public TradeTypesInsertionWindowController() {
    }


    EnterItem nameItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameItem = new EnterItem("name");

        hBox.getChildren().addAll(nameItem);
    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(nameItem.getColumnName(), nameItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).insertRow(insertionMap);
    }

}
