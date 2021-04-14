package controller.insertion;

import entities.Customer;
import entities.Entity;
import init.Main;
import utils.EnterItem;
import utils.TableNames;
import utils.table_managers.CustomersTableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CustomersInsertionWindowController extends InsertionWindowController {

    private final CustomersTableManager tableManager = (CustomersTableManager) Main.getDatabaseManager().getTableManager(TableNames.CUSTOMERS);
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
    public void insertRow() throws SQLException {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put(getIdItem().getColumnName(), getIdItem().getEnteredText());
        valuesMap.put(nameItem.getColumnName(), nameItem.getEnteredText());
        valuesMap.put(ageItem.getColumnName(), ageItem.getEnteredText());

        if (getMode().equals(MODE.INSERTING)) {
            tableManager.insertRow(valuesMap);
        } else {
            tableManager.updateRow(valuesMap);
        }
    }

    @Override
    public void initUpdating(Entity entity) {
        Customer value = (Customer) entity;
        getIdItem().setText(value.getId());
        nameItem.setText(value.getName());
        ageItem.setText(value.getAge());
    }


}
