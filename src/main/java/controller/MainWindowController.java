package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import controller.tables.TableWindowController;
import init.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import utils.DatabaseManager;

public class MainWindowController extends Controller implements Initializable {

  public static final String MAIN_WINDOW_FXML = "/main_window.fxml";
  private final DatabaseManager manager;

  private final Map<String, String> tableNameToController = new HashMap<String, String>(){{
    put("GOODS","controller.tables.GoodsTableWindowController");
    put("ACCOUNTING","controller.tables.AccountingTableWindowController");
    put("CUSTOMERS","controller.tables.CustomersTableWindowController");
    put("DELIVERIES","controller.tables.DeliveriesTableWindowController");
    put("DELIVERIES_GOODS","controller.tables.DeliveriesGoodsTableWindowController");
    put("PROVIDERS","controller.tables.ProvidersTableWindowController");
    put("PURCHASE_COMPOSITIONS","controller.tables.PurchaseCompositionsTableWindowController");
    put("SALES","controller.tables.SalesTableWindowController");
    put("SELLERS","controller.tables.SellersTableWindowController");
    put("TRADE_SECTION_POINT","controller.tables.TradeSectionPointTableWindowController");
    put("TRADE_POINTS","controller.tables.TradePointsTableWindowController");
    put("TRADE_SECTION","controller.tables.TradeSectionTableWindowController");
    put("TRADE_TYPES","controller.tables.TradeTypesTableWindowController");
    put("TRADE_ROOM","controller.tables.TradeRoomTableWindowController");
  }
  };

  @FXML
  private ListView<String> tableNamesView;

  public MainWindowController() {
    manager = Main.getDatabaseManager();

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    updateTableView();
    ContextMenu contextMenu = new ContextMenu();
    contextMenu.getItems().add(new MenuItem("Open"));
    contextMenu.setOnAction(event -> {
      String selectedItem = tableNamesView.getSelectionModel().getSelectedItems().get(0);

      String classForName = null;
      if((classForName = tableNameToController.get(selectedItem))!=null){
        Controller controller = null;
        try {
          controller = Main.getNavigation().loadTable(TableWindowController.TABLE_WINDOW_FXML,
                  classForName);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
          e.printStackTrace();
        }
        controller.setStage(Main.getNavigation().createNewStage());
        controller.show();
      }
      System.out.println(tableNamesView.getSelectionModel().getSelectedItems());
    });
    tableNamesView.setContextMenu(contextMenu);

  }


  @FXML
  private void createTableButtonHandler() throws SQLException {
    manager.createDatabase();
    updateTableView();
  }

  private void updateTableView() {
    tableNamesView.getItems().clear();
    List<String> items = tableNamesView.getItems();
    items.addAll(manager.getExistingTables().stream().map(String::toUpperCase).collect(Collectors.toList()));
  }

  @FXML
  public void dropTableButtonHandler() {
    manager.clearDatabase();
    updateTableView();
  }

  public void handleMouseClickOnTable(MouseEvent mouseEvent) {
    String selectedTable = tableNamesView.getSelectionModel().getSelectedItems().get(0);

  }

  public void openAvailableRequestsButtonHandler(ActionEvent actionEvent) {

  }

  public void loadTestValuesButtonHandler() {
    Main.getDatabaseManager().initTestValues();

  }
}
