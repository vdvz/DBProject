package controller;

import init.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DatabaseManager;

import java.net.URL;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class TableWindowController extends Controller implements Initializable {

    public static final String MAIN_WINDOW_FXML = "/table_window.fxml";
    final DatabaseManager manager;

    public Button createNewRowButton;
    public Button updateButton;
    public Button backButton;
    public TableView table;

    TableWindowController(){
        manager = Main.getDatabaseManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createNewRowButton.setOnAction(e->{

        });
        updateButton.setOnAction(e->{

        });
        backButton.setOnAction(e->{

        });
        System.out.println("Wow");
        System.out.println(backButton.getId());
    }

    abstract public void generateTable();

    abstract public void back();

    abstract public void createNewRow();

    abstract public void update();
}
