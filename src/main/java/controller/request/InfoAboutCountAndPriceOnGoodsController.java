package controller.request;

import controller.Controller;
import controller.table.Request;
import database_managers.request_managers.InfoAboutCustomersManager;
import entities.Customer;
import entities.Entity;
import entities.Good;
import entities.TradePoint;
import entities.TradeType;
import init.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.Navigation;
import utils.TableNames;

public class InfoAboutCountAndPriceOnGoodsController extends Controller implements Initializable, Request {
  public static final String INFO_ABOUT_COUNT_AND_PRICE_ON_GOODS_WINDOW_FXML =
      "/window/request/InfoAboutCountAndPriceOnGoods.fxml";
  private final InfoAboutCustomersManager manager = new InfoAboutCustomersManager();
  @FXML private ChoiceBox<ChoiceUnit> good;
  @FXML private ChoiceBox<ChoiceUnit> tradePointType;
  @FXML private ChoiceBox<ChoiceUnit> tradePoint;
  @FXML private TableView resultTable;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadGood().stream()
        .map(e -> new ChoiceUnit(e.getId(), ((Good) e).getName()))
        .forEach(good.getItems()::addAll);
    loadTradePoint().stream()
        .map(e -> new ChoiceUnit(e.getId(), ((TradePoint) e).getName()))
        .forEach(tradePoint.getItems()::addAll);
    loadTradePointType().stream()
        .map(e -> new ChoiceUnit(e.getId(), ((TradeType) e).getName()))
        .forEach(tradePointType.getItems()::addAll);

    TableColumn<Customer, String> columnId = new TableColumn<>("id");
    TableColumn<Customer, String> columnName = new TableColumn<>("name");
    TableColumn<Customer, String> columnAge = new TableColumn<>("age");

    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));

    resultTable.getColumns().addAll(columnId, columnName, columnAge);

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

    tradePointType.setConverter(
        new StringConverter<ChoiceUnit>() {
          @Override
          public String toString(ChoiceUnit object) {
            return object.getDisplayedName();
          }

          @Override
          public ChoiceUnit fromString(String string) {
            return tradePointType.getItems().stream()
                .filter(e -> e.getDisplayedName().equals(string))
                .findFirst()
                .orElse(null);
          }
        });
  }

  private ObservableList<Entity> loadGood() {
    return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
  }

  private ObservableList<Entity> loadTradePointType() {
    return Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).getTableRows();
  }

  private ObservableList<Entity> loadTradePoint() {
    return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
  }

  @FXML
  public void clearTradePoint() {
    tradePoint.getItems().clear();
    tradePointType.getItems().clear();
  }

  @FXML
  public void query() {
    String query =
        "SELECT C2.ID, C2.NAME, C2.AGE "
            + " FROM SALES S "
            + " INNER JOIN CUSTOMERS C2 on C2.ID=S.CUSTOMER "
            + " INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID=S.PURCHASE_COMPOSITION "
            + " INNER JOIN GOODS G on G.ID=PC.GOOD "
            + " INNER JOIN SELLERS S2 on S2.ID=S.SELLER "
            + " INNER JOIN TRADE_POINTS TP on TP.ID=S2.TRADE_POINT "
            + " INNER JOIN TRADE_TYPES TT on TP.TYPE=TT.ID "
            + " WHERE 1=1";

    query += " AND G.ID=" + good.getValue().getId();

    if (tradePoint.getValue() != null && tradePointType.getValue() == null) {
      query += " AND TP.NAME='" + tradePoint.getValue().getDisplayedName() + "'";
    } else if (tradePoint.getValue() == null && tradePointType.getValue() != null) {
      query += " AND TT.NAME='" + tradePointType.getValue().getDisplayedName() + "'";
    }

    System.out.println("Query: " + query);

    updateResultTable(manager.executeQuery(query));
  }

  @Override
  public void checkCorrectness() throws Exception {
    if (good.getValue() == null) {
      Navigation.showAlert("Ввод невалидных данных", "Выберите товар");
      throw new Exception();
    }

    if (tradePoint.getValue() != null && tradePointType.getValue() != null) {
      Navigation.showAlert(
          "Ввод невалидных данных",
          "Выберите торговую точку, тип торговой точки, либо оставьте оба значения пустыми");
      throw new Exception();
    }
  }

  private void clearResultTable() {
    resultTable.getItems().clear();
  }

  private void updateResultTable(ObservableList<Entity> result) {
    clearResultTable();
    resultTable.getItems().addAll(result);
  }
}
