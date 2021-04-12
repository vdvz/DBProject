package controller.table;

import controller.Controller;
import init.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import utils.DatabaseManager;

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

    TableWindowController(String tableName){
        this.tableName = tableName;
        manager = Main.getDatabaseManager();
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

    public void deleteRow(){

    }

    public void generateTable() {
        /*try {
            table.getItems().addAll(Main.getDatabaseManager().getTableManager(tableName).getTableRows());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
    abstract public void createNewRow();

    public void updateTable(){
        table.getItems().clear();
        generateTable();
    }
}
