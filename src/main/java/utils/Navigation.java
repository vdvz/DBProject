package utils;

import controller.Controller;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Navigation {

  private final Map<String, Controller> loadedControllers = new HashMap<>();
  Map<Controller, Stage> activeControllers = new HashMap<>();

  public static void showAlert(String header, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.showAndWait();
  }

  public Controller load(String sUrl) {
    Controller controller;
    if ((controller = loadedControllers.get(sUrl)) != null) {
      return controller;
    }
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
      Node root = fxmlLoader.load();

      controller = fxmlLoader.getController();
      controller.setView(root);
      loadedControllers.put(sUrl, controller);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return controller;
  }

  public Controller loadTable(String sUrl, String classPathController, Stage stage) {
/*
    Controller controller;
    if ((controller = loadedControllers.get(classPathController)) != null) {
      return controller;
    }
*/
    Controller targetController = null;
    try {
      targetController =
          (Controller) Class.forName(classPathController).getConstructor().newInstance();
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
      System.out.println("Load new controller");
      fxmlLoader.setController(targetController);
      Node root = fxmlLoader.load();
      targetController.setView(root);
      Controller finalTargetController = targetController;
      targetController.setStage(stage);
      stage.setOnCloseRequest(
          e -> {
            System.out.println("Close satge");
            loadedControllers.remove(classPathController);
            System.out.println("Remove classpath: " + loadedControllers.get(classPathController));
            finalTargetController.close();
          });
      loadedControllers.put(classPathController, targetController);
    } catch (IOException
        | InstantiationException
        | InvocationTargetException
        | NoSuchMethodException
        | IllegalAccessException
        | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return targetController;
  }

  public Stage createNewStage() {
    return new Stage();
  }

  public void show(Controller controller, Stage stage) {
    if (activeControllers.containsKey(controller)) {
      stage.close();
      Stage activeStage = activeControllers.get(controller);
      activeStage.show();
      return;
    }
    try {
      stage.setOnCloseRequest(
          e -> {
            activeControllers.remove(e.getTarget());
          });
      stage.setScene(new Scene((Parent) controller.getView()));
      activeControllers.put(controller, stage);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void shutdownAllStage(Stage except) {
    activeControllers.values().stream().filter(e -> !e.equals(except)).forEach(Stage::close);
    activeControllers.clear();
  }

  public void shutdownAllControllers(Controller except) {
    loadedControllers.values().stream()
        .filter(e -> !e.equals(except))
        .forEach(Controller::shutdown);
    loadedControllers.clear();
  }

  public void closeStage(Stage stage) {
    activeControllers.remove(stage);
    System.out.println("Cloose");
  }
}
