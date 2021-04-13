package controller.insertion;

import Entities.DeliveriesGood;
import Entities.Entity;
import Entities.Good;
import init.Main;
import utils.EnterItem;
import utils.TableNames;
import utils.table_managers.GoodsTableManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GoodsInsertionWindowController extends InsertionWindowController {

    private final GoodsTableManager tableManager = (GoodsTableManager) Main.getDatabaseManager().getTableManager(TableNames.GOODS);
    private EnterItem nameItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameItem = new EnterItem("name");

        hBox.getChildren().addAll(nameItem);
    }

    @Override
    public void insertRow() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(nameItem.getColumnName(), nameItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        Good value = (Good) entity;
        getIdItem().setText(value.getId());
        nameItem.setText(value.getName());
    }

}
