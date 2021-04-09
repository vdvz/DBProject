package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import init.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import utils.Connection;
import utils.DatabaseManager;

public class MainWindowController extends Controller implements Initializable {

  public static final String MAIN_WINDOW_FXML = "/main__window.fxml";
  private final DatabaseManager manager;

  private Map<String, String> tableNameToControllerPath = new HashMap<String, String>(){{
    put("GOODS","/");
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
    contextMenu.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

        Controller controller = Main.getNavigation().loadTable(TableWindowController.MAIN_WINDOW_FXML, new GoodsTableWindowController());
        controller.setStage(Main.getNavigation().createNewStage());
        controller.show();
        System.out.println(tableNamesView.getSelectionModel().getSelectedItems());
      }
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
    items.addAll(manager.getExistingTables());
  }

  @FXML
  public void dropTableButtonHandler() {
    try {
      manager.dropTables();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    updateTableView();
  }

  public void handleMouseClickOnTable(MouseEvent mouseEvent) {
    String selectedTable = tableNamesView.getSelectionModel().getSelectedItems().get(0);

  }

}
