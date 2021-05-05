package controller.insertion;

import controller.Controller;
import controller.RoleController;
import entities.Entity;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.EnterItem;
import utils.Navigation;

public abstract class InsertionWindowController extends Controller
    implements Initializable, RoleController {

  private static final Map<String, String> tableNameToController =
      new HashMap<String, String>() {
        {
          put("ACCOUNTING", "controller.insertion.AccountingInsertionWindowController");
          put("CUSTOMERS", "controller.insertion.CustomersInsertionWindowController");
          put("DELIVERIES_GOODS", "controller.insertion.DeliveriesGoodsInsertionWindowController");
          put("DELIVERIES", "controller.insertion.DeliveriesInsertionWindowController");
          put("GOODS", "controller.insertion.GoodsInsertionWindowController");
          put("PROVIDERS", "controller.insertion.ProvidersInsertionWindowController");
          put(
              "PURCHASE_COMPOSITIONS",
              "controller.insertion.PurchaseCompositionsInsertionWindowController");
          put("SALES", "controller.insertion.SalesInsertionWindowController");
          put("SELLERS", "controller.insertion.SellersInsertionWindowController");
          put("TRADE_POINTS", "controller.insertion.TradePointsInsertionWindowController");
          put("TRADE_ROOM", "controller.insertion.TradeRoomInsertionWindowController");
          put(
              "TRADE_SECTION_POINT",
              "controller.insertion.TradeSectionPointInsertionWindowController");
          put("TRADE_TYPES", "controller.insertion.TradeTypesInsertionWindowController");
        }
      };
  @FXML public HBox hBox;
  @FXML public Button button;
  private MODE mode = MODE.INSERTING;
  private EnterItem idItem;
  private Role role;

  public static String getNameOfController(String tableName) {
    return tableNameToController.get(tableName);
  }

  public MODE getMode() {
    return mode;
  }

  public void setMode(MODE mode) {
    this.mode = mode;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    idItem = new EnterItem("id");
    button.setOnAction(
        e -> {
          try {
            insertRow();
            getStage().close();
          } catch (SQLException throwables) {
            Navigation.showAlert(
                "Не удалость вставить значения",
                "Возможно, наругена уникальность одного из значений");
            throwables.printStackTrace();
          }
        });
  }

  public EnterItem getIdItem() {
    return idItem;
  }

  public abstract void insertRow() throws SQLException;

  public abstract void initUpdating(Entity value);

  @Override
  public void setRole(Role role) {
    this.role = role;
  }

  public enum MODE {
    INSERTING,
    UPDATING
  }
}
