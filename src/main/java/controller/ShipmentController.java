package controller;

import database_managers.request_managers.ShipmentManager;
import entities.Entity;
import entities.Good;
import entities.Provider;
import entities.TradePoint;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.TableNames;

public class ShipmentController extends Controller implements Initializable {

  private final Map<Good, Integer> goodInCart = new HashMap<>();
  private final Map<Good, Integer> priceGoodInCart = new HashMap<>();
  public ChoiceBox<ChoiceUnit> good;
  public TextField pricePerOne;
  public Spinner<Integer> countGoods;
  public ChoiceBox<ChoiceUnit> tradePoint;
  public DatePicker date;
  public ChoiceBox<ChoiceUnit> provider;
  public ListView<String> goodList;
  private Map<Integer, Good> allGoods;
  private ShipmentManager manager = new ShipmentManager();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    countGoods.setValueFactory(new IntegerSpinnerValueFactory(1, 1000, 1));
    provider
        .getItems()
        .addAll(
            loadProviders().stream()
                .map(e -> new ChoiceUnit(e.getId(), ((Provider) e).getName()))
                .collect(Collectors.toList()));

    tradePoint
        .getItems()
        .addAll(
            loadTradePoints().stream()
                .map(e -> new ChoiceUnit(e.getId(), ((TradePoint) e).getName()))
                .collect(Collectors.toList()));

    good.getItems()
        .addAll(
            loadGoods().stream()
                .map(e -> new ChoiceUnit(e.getId(), ((Good) e).getName()))
                .collect(Collectors.toList()));

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

    provider.setConverter(
        new StringConverter<ChoiceUnit>() {
          @Override
          public String toString(ChoiceUnit object) {
            return object.getDisplayedName();
          }

          @Override
          public ChoiceUnit fromString(String string) {
            return provider.getItems().stream()
                .filter(e -> e.getDisplayedName().equals(string))
                .findFirst()
                .orElse(null);
          }
        });

    tradePoint.setConverter(
        new StringConverter<ChoiceUnit>() {
          @Override
          public String toString(ChoiceUnit object) {
            return object.getDisplayedName();
          }

          @Override
          public ChoiceUnit fromString(String string) {
            return tradePoint.getItems().stream()
                .filter(e -> e.getDisplayedName().equals(string))
                .findFirst()
                .orElse(null);
          }
        });
  }

  private ObservableList<Entity> loadTradePoints() {
    return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
  }

  private ObservableList<Entity> loadGoods() {
    allGoods = new HashMap<>();
    ObservableList<Entity> goodsFromDB =
        Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    goodsFromDB.forEach(e -> allGoods.put(Integer.valueOf(e.getId()), (Good) e));
    return goodsFromDB;
  }

  private ObservableList<Entity> loadProviders() {
    return Main.getDatabaseManager().getTableManager(TableNames.PROVIDERS).getTableRows();
  }

  public void addGood() {
    if (pricePerOne.getText().equals("")) {
      // TODO EXCEPTION and also check if value is valid
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
          .add(
              goodChoice.getDisplayedName()
                  + ", количество: "
                  + countGoods.getValue()
                  + ", по цене: "
                  + Integer.valueOf(pricePerOne.getText()));
    }
  }

  private Good fromIdToGood(Integer id) {
    return allGoods.get(id);
  }

  public void addShipment() {

    checkCorrectness();

    manager.startTransaction();

    try {
      Map<String, String> valuesMap = new HashMap<>();
      valuesMap.put("provider_id", provider.getSelectionModel().getSelectedItem().getId());
      valuesMap.put("trade_point_id", tradePoint.getSelectionModel().getSelectedItem().getId());
      valuesMap.put("deliver_date", date.getValue().toString());

      int deliveryId = manager.addDelivery(valuesMap);

      List<Map<String, String>> values = new ArrayList<>();
      goodInCart.forEach(
          (k, v) -> {
            Map<String, String> value = new HashMap<>();
            value.put("good_id", k.getId());
            value.put("delivery_id", String.valueOf(deliveryId));
            value.put("count", String.valueOf(v));
            value.put("price", String.valueOf(priceGoodInCart.get(k)));
            values.add(value);
          });

      manager.addDeliveredGoods(values);

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      manager.rollbackTransaction();
    }

    manager.commitTransaction();

    getStage().close();
    System.out.println("Successful transaction");
  }

  private void checkCorrectness() {}
}
