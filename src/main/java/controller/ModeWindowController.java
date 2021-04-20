package controller;


import init.Main;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModeWindowController extends Controller implements Initializable{
  public static final String MODE_WINDOW_FXML = "/mode_window.fxml";

    public void onAdminMode(){
        MainWindowController controller = (MainWindowController) Main.getNavigation().load(MainWindowController.MAIN_WINDOW_FXML);
        controller.setStage(Main.getNavigation().createNewStage());
        controller.setRole(RoleController.Role.ADMIN);
        controller.show();
        this.close();
    }

    public void onManagerMode(){
        MainWindowController controller = (MainWindowController) Main.getNavigation().load(MainWindowController.MAIN_WINDOW_FXML);
        controller.setStage(Main.getNavigation().createNewStage());
        controller.setRole(RoleController.Role.MANAGER);
        controller.show();
        this.close();
    }

    public void onSellerMode(){
        MainWindowController controller = (MainWindowController) Main.getNavigation().load(MainWindowController.MAIN_WINDOW_FXML);
        controller.setStage(Main.getNavigation().createNewStage());
        controller.setRole(RoleController.Role.SELLER);
        controller.show();
        this.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}