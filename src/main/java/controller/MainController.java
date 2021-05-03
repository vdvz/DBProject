package controller;

import init.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import utils.DatabaseManager;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable, RoleController {
    public final static String URL_FXML = "/window/main.fxml";
    private final DatabaseManager manager;

    public MainController() {
        manager = Main.getDatabaseManager();
    }

    @FXML
    public ContextMenu openTable;

    @FXML
    private ListView<String> tableListView;

    @FXML
    private Button createDB;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTableView();
    }

    @FXML
    private void createButtonTapped() throws SQLException {
        manager.createDatabase();
        configureTableView();
    }

    private void configureTableView() {

        manager.getExistingTables().forEach(e->{
            if (!tableListView.getItems().contains(e)) {
                tableListView.getItems().add(e);
            }
        });
    }

    public static void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Role role;

    @Override
    public void setRole(Role role) {
        this.role = role;
    }

}
