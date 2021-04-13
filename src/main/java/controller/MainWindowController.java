package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import controller.table.TableWindowController;
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
    put("ACCOUNTING","controller.table.AccountingTableWindowController");
    put("CUSTOMERS","controller.table.CustomersTableWindowController");
    put("DELIVERIES_GOODS","controller.table.DeliveriesGoodsTableWindowController");
    put("DELIVERIES","controller.table.DeliveriesTableWindowController");
    put("GOODS","controller.table.GoodsTableWindowController");
    put("PROVIDERS","controller.table.ProvidersTableWindowController");
    put("PURCHASE_COMPOSITIONS","controller.table.PurchaseCompositionsTableWindowController");
    put("SALES","controller.table.SalesTableWindowController");
    put("SELLERS","controller.table.SellersTableWindowController");
    put("TRADE_POINTS","controller.table.TradePointsTableWindowController");
    put("TRADE_ROOM","controller.table.TradeRoomTableWindowController");
    put("TRADE_SECTION_POINT","controller.table.TradeSectionPointTableWindowController");
    put("TRADE_TYPES","controller.table.TradeTypesTableWindowController");
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
        controller = Main.getNavigation().loadTable(TableWindowController.TABLE_WINDOW_FXML,
                classForName);
        controller.setStage(Main.getNavigation().createNewStage());
        controller.show();
      }
      System.out.println(tableNamesView.getSelectionModel().getSelectedItems());
    });
    tableNamesView.setContextMenu(contextMenu);

  }


  @FXML
  private void createTableButtonHandler(){
    Main.getNavigation().shutdownAllControllers(this);
    Main.getNavigation().shutdownAllStage(this.getStage());
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
    Main.getNavigation().shutdownAllControllers(this);
    Main.getNavigation().shutdownAllStage(this.getStage());
    manager.clearDatabase();
    updateTableView();
  }

  public void openAvailableRequestsButtonHandler(ActionEvent actionEvent) {

  }

  public void loadTestValuesButtonHandler() {
    Main.getDatabaseManager().initTestValues();
  }

}
