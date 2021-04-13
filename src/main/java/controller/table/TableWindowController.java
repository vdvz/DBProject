package controller.table;

import controller.Controller;
import init.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import utils.DatabaseManager;
import utils.tableManagers.TableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class TableWindowController extends Controller implements Initializable {

    public static final String TABLE_WINDOW_FXML = "/table_window.fxml";
    final DatabaseManager manager;

    public Button createNewRowButton;
    public Button updateTableButton;
    public TableView table;
    private final String tableName;
    private TableManager tableManager;

    TableWindowController(String tableName){
        this.tableName = tableName;
        manager = Main.getDatabaseManager();
        tableManager = Main.getDatabaseManager().getTableManager(tableName);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem("Edit");
        MenuItem deleteItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(editItem, deleteItem);
        contextMenu.setOnAction(event -> {
            MenuItem targetItem = (MenuItem)event.getTarget();
            if(targetItem.equals(editItem)){
                System.out.println("Selected edit item " + table.getSelectionModel().getSelectedItems());
            }
            if(targetItem.equals(deleteItem)){
                System.out.println("Selected delete item " + table.getSelectionModel().getSelectedItems());
                deleteRow(table.getSelectionModel().getSelectedItems().get(0).toString());
            }
        });
        table.setContextMenu(contextMenu);

        createNewRowButton.setOnAction(e->{
            createNewRow();
        });
        updateTableButton.setOnAction(e->{
            updateTable();
        });
    }

    public abstract void deleteRow(String id);

    public void generateTable() {
        table.getItems().addAll(tableManager.getTableRows());
    }
    abstract public void createNewRow();

    public void updateTable(){
        table.getItems().clear();
        generateTable();
    }
}
