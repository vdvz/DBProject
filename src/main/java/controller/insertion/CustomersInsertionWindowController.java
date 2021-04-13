package controller.insertion;

import Entities.Entity;
import Entities.Good;
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

public class CustomersInsertionWindowController extends InsertionWindowController {


    public CustomersInsertionWindowController() {
    }


    EnterItem nameItem;
    EnterItem ageItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameItem = new EnterItem("name");
        ageItem = new EnterItem("age");

        hBox.getChildren().addAll(nameItem, ageItem);

    }

    @Override
    public void insertRow() {
        Map<String, String> insertionMap = new HashMap<>();
        insertionMap.put(nameItem.getColumnName(), nameItem.getEnteredText());
        insertionMap.put(ageItem.getColumnName(), ageItem.getEnteredText());

        Main.getDatabaseManager().getTableManager(TableNames.PURCHASE_COMPOSITIONS).insertRow(insertionMap);

    }


}
