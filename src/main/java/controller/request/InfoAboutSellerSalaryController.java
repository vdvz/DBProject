package controller.request;

import controller.Controller;
import database_managers.request_managers.InfoAboutSellerSalaryManager;
import entities.Entity;
import entities.Seller;
import entities.TradePoint;
import entities.TradeType;
import init.Main;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.TableNames;

public class InfoAboutSellerSalaryController extends Controller implements Initializable {
  public static final String INFO_ABOUT_SELLER_SALARY_WINDOW_FXML =
      "/window/request/InfoAboutSellerSalary.fxml";
  private final InfoAboutSellerSalaryManager manager = new InfoAboutSellerSalaryManager();
  public ChoiceBox<ChoiceUnit> tradePoint;
  public ChoiceBox<ChoiceUnit> tradePointType;
  public TableView resultTable;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadTradePoint().stream()
        .map(e -> new ChoiceUnit(e.getId(), ((TradePoint) e).getName()))
        .forEach(tradePoint.getItems()::addAll);
    loadTradePointType().stream()
        .map(e -> new ChoiceUnit(e.getId(), ((TradeType) e).getName()))
        .forEach(tradePointType.getItems()::addAll);

    TableColumn<Seller, String> columnId = new TableColumn<>("id");
    TableColumn<Seller, String> columnName = new TableColumn<>("name");
    TableColumn<Seller, String> columnSalary = new TableColumn<>("salary");
    TableColumn<Seller, String> columnTradePoint = new TableColumn<>("trade_point");
    TableColumn<Seller, String> columnTradeRoom = new TableColumn<>("trade_room");

    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    columnTradePoint.setCellValueFactory(new PropertyValueFactory<>("tradePoint"));
    columnTradeRoom.setCellValueFactory(new PropertyValueFactory<>("tradeRoom"));

    resultTable
        .getColumns()
        .addAll(columnId, columnName, columnSalary, columnTradePoint, columnTradeRoom);

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

  private ObservableList<Entity> loadTradePointType() {
    return Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).getTableRows();
  }

  private ObservableList<Entity> loadTradePoint() {
    return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
  }

  public void clearTradePoint() {
    tradePoint.getItems().clear();
    tradePointType.getItems().clear();
  }

  public void query() {
    checkCorrectness();

    String query =
        "SELECT S.ID, S.NAME, S.SALARY, S.TRADE_POINT, S.TRADE_ROOM"
            + " FROM TRADE_POINTS TP\n"
            + " INNER JOIN SELLERS S ON S.TRADE_POINT=TP.ID"
            + " INNER JOIN TRADE_TYPES TT on TP.TYPE = TT.ID"
            + " WHERE 1=1 ";

    if (tradePoint.getValue() != null && tradePointType.getValue() != null) {
      // TODO throw exception
      return;
    }

    if (tradePoint.getValue() != null && tradePointType.getValue() == null) {
      query += " AND TP.NAME='" + tradePoint.getValue().getDisplayedName() + "' ";
    } else if (tradePoint.getValue() == null && tradePointType.getValue() != null) {
      query += " AND TT.NAME='" + tradePointType.getValue().getDisplayedName() + "' ";
    }

    System.out.println(query);

    try {
      updateResultTable(manager.executeQuery(query));
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  private void clearResultTable() {
    resultTable.getItems().clear();
  }

  private void updateResultTable(ObservableList<Entity> result) {
    clearResultTable();
    resultTable.getItems().addAll(result);
  }

  private void checkCorrectness() {
    /*TODO*/
  }
}
