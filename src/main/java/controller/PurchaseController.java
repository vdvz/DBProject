package controller;

import database_managers.request_managers.PurchaseManager;
import database_managers.table_managers.CustomersTableManager;
import entities.Customer;
import entities.Entity;
import entities.Good;
import entities.Seller;
import init.Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.TableNames;

public class PurchaseController extends Controller implements Initializable {

    private final Map<Good, Integer> goodInCart = new HashMap<>();
    private final Map<Good, Integer> priceGoodInCart = new HashMap<>();
    private final PurchaseManager manager = new PurchaseManager();
    private final CustomersTableManager customerTableManager =
            (CustomersTableManager) Main.getDatabaseManager().getTableManager(TableNames.CUSTOMERS);
    public ChoiceBox<ChoiceUnit> customer;
    public DatePicker date;
    public ChoiceBox<ChoiceUnit> good;
    public ChoiceBox<ChoiceUnit> seller;
    public ListView<String> goodList;
    public TextField name;
    public TextField age;
    public Button addNewCustomerButton;
    public Spinner<Integer> countGoods;
    public TextField pricePerOne;
    private Map<Integer, Customer> allCustomers;
    private Map<Integer, Good> allGoods;
    private boolean withAddNewCustomer = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countGoods.setValueFactory(new IntegerSpinnerValueFactory(1, 1000, 1));
        customer
                .getItems()
                .addAll(
                        loadCustomers().stream()
                                .map(
                                        e ->
                                                new ChoiceUnit(
                                                        e.getId(), ((Customer) e).getName() + ", " + ((Customer) e).getAge()))
                                .collect(Collectors.toList()));

        seller.getItems().addAll(
                loadSellers().stream()
                        .map(e -> new ChoiceUnit(e.getId(), ((Seller) e).getName()))
                        .collect(Collectors.toList()));

        good.getItems()
                .addAll(
                        loadGoods().stream()
                                .map(e -> new ChoiceUnit(e.getId(), ((Good) e).getName()))
                                .collect(Collectors.toList()));

        customer.setConverter(
                new StringConverter<ChoiceUnit>() {
                    @Override
                    public String toString(ChoiceUnit object) {
                        return object.getDisplayedName();
                    }

                    @Override
                    public ChoiceUnit fromString(String string) {
                        return customer.getItems().stream()
                                .filter(e -> e.getDisplayedName().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });

        good.setConverter(
                new StringConverter<ChoiceUnit>() {
                    @Override
                    public String toString(ChoiceUnit object) {
                        return object.getDisplayedName();
                    }

                    @Override
                    public ChoiceUnit fromString(String string) {
                        return good.getItems().stream()
                                .filter(e -> e.getDisplayedName().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });

        seller.setConverter(
                new StringConverter<ChoiceUnit>() {
                    @Override
                    public String toString(ChoiceUnit object) {
                        return object.getDisplayedName();
                    }

                    @Override
                    public ChoiceUnit fromString(String string) {
                        return seller.getItems().stream()
                                .filter(e -> e.getDisplayedName().equals(string))
                                .findFirst()
                                .orElse(null);
                    }
                });
    }

    private ObservableList<Entity> loadSellers() {
        return Main.getDatabaseManager().getTableManager(TableNames.SELLERS).getTableRows();
    }

    private Good fromIdToGood(Integer id) {
        return allGoods.get(id);
    }

    private ObservableList<Entity> loadGoods() {
        allGoods = new HashMap<>();
        ObservableList<Entity> goodsFromDB =
                Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
        goodsFromDB.forEach(e -> allGoods.put(Integer.valueOf(e.getId()), (Good) e));
        return goodsFromDB;
    }

    private ObservableList<Entity> loadCustomers() {
        allCustomers = new HashMap<>();
        ObservableList<Entity> customersFromDB =
                Main.getDatabaseManager().getTableManager(TableNames.CUSTOMERS).getTableRows();
        customersFromDB.forEach(e -> allCustomers.put(Integer.valueOf(e.getId()), (Customer) e));
        return customersFromDB;
    }

    public void addGood() {
        if (pricePerOne.getText().equals("")) {
            //TODO EXCEPTION and also check if value is valid

        }

        ChoiceUnit goodChoice = good.getSelectionModel().getSelectedItem();
        if (goodChoice != null) {
            Good target = fromIdToGood(Integer.valueOf(goodChoice.getId()));
            if (goodInCart.containsKey(target)) {
                goodInCart.put(target, goodInCart.get(target) + countGoods.getValue());
            } else {
                goodInCart.put(target, countGoods.getValue());
                priceGoodInCart.put(target, Integer.valueOf(pricePerOne.getText()));
            }
            goodList
                    .getItems()
                    .add(goodChoice.getDisplayedName() + ", количество: " + countGoods.getValue());
        }
    }

    public void addCustomer() {
        addNewCustomerButton.setDisable(true);
        customer.setDisable(true);
        name.setVisible(true);
        age.setVisible(true);
        withAddNewCustomer = true;
    }

    public void addPurchase() {
        manager.startTransaction();

        if (seller.getSelectionModel().getSelectedItem() == null) {
            //TODO EXCEPTION
            return;
        }
        if (customer.getSelectionModel().getSelectedItem() == null && !withAddNewCustomer) {
            //TODO EXCEPTION
            return;
        }
        if (date.getValue() == null) {
            //TODO EXCEPTION
            return;
        }


        String customerId;
        if (withAddNewCustomer) {
            if (name.getText().equals("")) {
                /*TODO Exception*/
                manager.rollbackTransaction();
                return;
            }
            Map<String, String> valuesMap = new HashMap<>();
            valuesMap.put("name", name.getText());
            valuesMap.put("age", age.getText());
            try {
                customerId = String.valueOf(manager.addCustomer(valuesMap));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                manager.rollbackTransaction();
                return;
            }
        } else {
            customerId = customer.getSelectionModel().getSelectedItem().getId();
        }

        List<Map<String, String>> values = new ArrayList<>();
        goodInCart.forEach((k, v) -> {
            Map<String, String> value = new HashMap<>();
            value.put("good", k.getId());
            value.put("count", String.valueOf(v));
            value.put("result_price", String.valueOf(Integer.parseInt(pricePerOne.getText()) * v));
            value.put("purchase_date", date.getValue().toString());
            values.add(value);
        });

        List<Integer> insertedPurchaseCompositionIds = null;
        try {
            insertedPurchaseCompositionIds = manager.addPurchaseCompositions(values);
        } catch (SQLException throwables) {
            manager.rollbackTransaction();
            /*TODO exception*/
            throwables.printStackTrace();
            return;
        }

        List<Map<String, String>> sales = new ArrayList<>();
        for (Integer id : insertedPurchaseCompositionIds) {
            Map<String, String> value = new HashMap<>();
            value.put("seller", seller.getSelectionModel().getSelectedItem().getId());
            value.put("customer", customerId);
            value.put("purchase_composition", String.valueOf(id));
            sales.add(value);
        }

        try {
            manager.addSales(sales);
        } catch (SQLException throwables) {
            manager.rollbackTransaction();
            /*TODO Exception*/
            throwables.printStackTrace();
            return;
        }

        manager.commitTransaction();

        System.out.println("Successful transaction");
    }

}
