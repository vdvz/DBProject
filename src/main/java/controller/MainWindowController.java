package controller;

import controller.insertion.InsertionWindowController;
import controller.table.TableWindowController;
import init.Main;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import utils.DatabaseManager;
import utils.TableNames;

public class MainWindowController extends Controller implements Initializable, RoleController {

  public static final String MAIN_WINDOW_FXML = "/window/main.fxml";
  private final DatabaseManager manager;

  private final Map<String, String> tableNameToController =
      new HashMap<String, String>() {
        {
          put("ACCOUNTING", "controller.table.AccountingTableWindowController");
          put("CUSTOMERS", "controller.table.CustomersTableWindowController");
          put("DELIVERIES_GOODS", "controller.table.DeliveriesGoodsTableWindowController");
          put("DELIVERIES", "controller.table.DeliveriesTableWindowController");
          put("GOODS", "controller.table.GoodsTableWindowController");
          put("PROVIDERS", "controller.table.ProvidersTableWindowController");
          put(
              "PURCHASE_COMPOSITIONS",
              "controller.table.PurchaseCompositionsTableWindowController");
          put("SALES", "controller.table.SalesTableWindowController");
          put("SELLERS", "controller.table.SellersTableWindowController");
          put("TRADE_POINTS", "controller.table.TradePointsTableWindowController");
          put("TRADE_SECTION_POINT", "controller.table.TradeSectionPointTableWindowController");
          put("TRADE_ROOM", "controller.table.TradeRoomTableWindowController");
          put("TRADE_TYPES", "controller.table.TradeTypesTableWindowController");
          put("STORES", "controller.table.StoresTableWindowController");
          put("KIOSK", "controller.table.KioskTableWindowController");
        }
      };

  public Button createTableButton;
  public Button dropTableButton;
  public Button loadTestValuesButton;
  public Button addAccountingButton;
  public Button addPurchaseButton;
  public Button addDeliveryButton;
  public Button addGoodButton;
  public Button addProviderButton;
  public Button removeProviderButton;

  @FXML private ListView<String> tableNamesView;
  private Role role;

  public MainWindowController() {
    manager = Main.getDatabaseManager();
  }

  @Override
  public void setRole(Role role) {
    if (role.equals(Role.ADMIN)) {
      createTableButton.setVisible(true);
      dropTableButton.setVisible(true);
    }
    if (role.equals(Role.SELLER)) {
      addAccountingButton.setVisible(true);
      addPurchaseButton.setVisible(true);
    }
    if (role.equals(Role.MANAGER)) {
      addProviderButton.setVisible(true);
      removeProviderButton.setVisible(true);
    }
    if (role.equals(Role.PROVIDER)) {
      addGoodButton.setVisible(true);
      addDeliveryButton.setVisible(true);
    }

    this.role = role;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadTestValuesButton.setVisible(false);
    updateTableView();
    ContextMenu contextMenu = new ContextMenu();
    contextMenu.getItems().add(new MenuItem("Открыть"));
    contextMenu.setOnAction(
        event -> {
          String selectedItem = tableNamesView.getSelectionModel().getSelectedItems().get(0);
          String classForName = null;
          if ((classForName = tableNameToController.get(selectedItem)) != null) {
            Controller controller = null;
            controller =
                Main.getNavigation()
                    .loadTable(TableWindowController.TABLE_WINDOW_FXML, classForName, Main.getNavigation().createNewStage());
            controller.show();
          }
          System.out.println(tableNamesView.getSelectionModel().getSelectedItems());
        });
    tableNamesView.setContextMenu(contextMenu);
  }

  @FXML
  private void createTable() {
    Main.getNavigation().shutdownAllControllers(this);
    Main.getNavigation().shutdownAllStage(this.getStage());
    manager.clearDatabase();
    manager.createDatabase();
    updateTableView();
    loadTestValuesButton.setVisible(true);
  }

  private void updateTableView() {
    tableNamesView.getItems().clear();
    List<String> items = tableNamesView.getItems();
    Set<String> allTableNames = tableNameToController.keySet();
    items.addAll(
        manager.getExistingTables().stream()
            .map(String::toUpperCase)
            .filter(allTableNames::contains)
            .collect(Collectors.toList()));
  }

  @FXML
  public void dropTable() {
    Main.getNavigation().shutdownAllControllers(this);
    Main.getNavigation().shutdownAllStage(this.getStage());
    manager.clearDatabase();
    updateTableView();
    loadTestValuesButton.setVisible(false);
  }

  public void openAvailableRequests() {
    Controller controller = Main.getNavigation().load(RequestWindowController.REQUESTS_WINDOW_FXML);
    controller.setStage(Main.getNavigation().createNewStage());
    controller.show();
  }

  public void loadTestValues() {
    Main.getDatabaseManager().initTestValues();
    loadTestValuesButton.setVisible(false);
  }

  public void addAccounting() {
    InsertionWindowController controller =
        (InsertionWindowController)
            Main.getNavigation()
                .loadTable(
                    "/window/insertion.fxml",
                    InsertionWindowController.getNameOfController(TableNames.ACCOUNTING), Main.getNavigation().createNewStage());
    controller.setMode(InsertionWindowController.MODE.INSERTING);
    controller.show();
  }

  public void addPurchase() {
    Controller controller =  Main.getNavigation().load("/window/purchase.fxml");
    Stage newStage = Main.getNavigation().createNewStage();
    controller.setStage(newStage);
    controller.show();
  }

  public void addDelivery() {
    Controller controller =  Main.getNavigation().load("/window/shipment.fxml");
    Stage newStage = Main.getNavigation().createNewStage();
    controller.setStage(newStage);
    controller.show();
  }

  public void addGood() {
    InsertionWindowController controller =
        (InsertionWindowController)
            Main.getNavigation()
                .loadTable(
                    "/window/insertion.fxml",
                    InsertionWindowController.getNameOfController(TableNames.GOODS), Main.getNavigation().createNewStage());
    controller.setMode(InsertionWindowController.MODE.INSERTING);
    controller.show();
  }

  public void addProvider() {
    InsertionWindowController controller =
        (InsertionWindowController)
            Main.getNavigation()
                .loadTable(
                    "/window/insertion.fxml",
                    InsertionWindowController.getNameOfController(TableNames.PROVIDERS), Main.getNavigation().createNewStage());
    controller.setMode(InsertionWindowController.MODE.INSERTING);
    controller.show();
  }

  public void removeProvider() {

  }
}
