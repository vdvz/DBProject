package controller;


import init.Main;

public class ModeWindowController {
  public static final String MODE_WINDOW_FXML = "/mode_window.fxml";

    public void onAdminMode(){
        Controller controller = Main.getNavigation().load(MainWindowController.MAIN_WINDOW_FXML);
        controller.setStage(Main.getNavigation().createNewStage());

        controller.show();
    }

}