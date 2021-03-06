package controller.table;

import controller.RoleController;
import entities.Entity;
import controller.Controller;
import controller.insertion.InsertionWindowController;
import init.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import utils.DatabaseManager;
import database_managers.table_managers.TableManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class TableWindowController extends Controller implements Initializable, RoleController, Request {

    public static final String TABLE_WINDOW_FXML = "/window/table.fxml";
    final DatabaseManager manager;

    public Button createNewRowButton;
    public Button updateTableButton;
    public TableView table;
    private final String tableName;
    private final TableManager tableManager;

    TableWindowController(String tableName){
        this.tableName = tableName;
        manager = Main.getDatabaseManager();
        tableManager = Main.getDatabaseManager().getTableManager(tableName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem("Изменить");
        MenuItem deleteItem = new MenuItem("Удалить");
        contextMenu.getItems().addAll(editItem, deleteItem);
        contextMenu.setOnAction(event -> {
            MenuItem targetItem = (MenuItem)event.getTarget();
            if(targetItem.equals(editItem)){
                System.out.println("Selected edit item " + table.getSelectionModel().getSelectedItems());
                updateRow((Entity) table.getSelectionModel().getSelectedItems().get(0));
            }
            if(targetItem.equals(deleteItem)){
                System.out.println("Selected delete item " + table.getSelectionModel().getSelectedItems());
                try {
                    Main.getDatabaseManager().getTableManager(tableName)
                            .deleteRow(((Entity)table.getSelectionModel().getSelectedItems().get(0)).getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
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

    public void generateTable() {
        System.out.println(tableManager.getTableRows());
        table.getItems().addAll(tableManager.getTableRows());
    }

    public void createNewRow(){
        InsertionWindowController controller = (InsertionWindowController) Main.getNavigation()
                .loadTable("/window/insertion.fxml", InsertionWindowController.getNameOfController(tableName), Main.getNavigation().createNewStage());
        controller.setMode(InsertionWindowController.MODE.INSERTING);
        controller.show();
    }

    public void updateRow(Entity entity){
        InsertionWindowController controller = (InsertionWindowController) Main.getNavigation()
                .loadTable("/window/insertion.fxml", InsertionWindowController.getNameOfController(tableName),Main.getNavigation().createNewStage());
        controller.setMode(InsertionWindowController.MODE.UPDATING);
        controller.initUpdating(entity);
        controller.show();
    }

    public void updateTable(){
        table.getItems().clear();
        generateTable();
    }

    private Role role;
    @Override
    public void setRole(Role role) {
        this.role = role;
    }
}
