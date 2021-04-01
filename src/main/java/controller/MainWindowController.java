package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import init.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utils.Connection;
import utils.DatabaseManager;

public class MainWindowController extends BaseController implements Initializable {

  public static final String MAIN_WINDOW_FXML = "/main__window.fxml";
  private final Connection connection;
  private final DatabaseManager manager;
  public MainWindowController() {
    connection = Main.getConnection();
    manager = new DatabaseManager(connection);
  }

  @FXML
  private ListView<String> tableNamesView;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    updateTableView();
  }


  public void openTable(){

  }

  @FXML
  private void createTableButtonHandler() throws SQLException {
    manager.createDatabase();
    updateTableView();
  }

  private void updateTableView() {
    List<String> items = tableNamesView.getItems();
    manager.getExistingTables().stream().filter(e->!items.contains(e)).forEach(e -> tableNamesView.getItems().add(e));
  }

}
