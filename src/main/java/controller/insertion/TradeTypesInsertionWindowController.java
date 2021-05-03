package controller.insertion;

import entities.Entity;
import entities.TradeType;
import init.Main;
import utils.EnterItem;
import utils.TableNames;
import database_managers.table_managers.TradeTypesTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TradeTypesInsertionWindowController extends InsertionWindowController {

    private final TradeTypesTableManager tableManager = (TradeTypesTableManager) Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE);
    private EnterItem nameItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        nameItem = new EnterItem("name");

        hBox.getChildren().addAll(nameItem);
    }

    @Override
    public void insertRow() throws SQLException {
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
        TradeType value = (TradeType) entity;
        getIdItem().setText(value.getId());
        nameItem.setText(value.getName());
    }

}
