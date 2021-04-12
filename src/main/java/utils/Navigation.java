package utils;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Navigation {

    private final Map<String, Controller> loadedControllers = new HashMap<>();

    public Navigation(Stage primaryStage) {
    }

    public Controller load(String sUrl) {
        Controller controller;
        if((controller = loadedControllers.get(sUrl)) != null){
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

    public Controller loadTable(String sUrl, String classPathController) {
        Controller controller;
        if((controller = loadedControllers.get(classPathController)) != null){
            return controller;
        }

        Controller targetController = null;
        try {
            targetController = (Controller) Class.forName(classPathController).getConstructor().newInstance();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            fxmlLoader.setController(targetController);
            Node root = fxmlLoader.load();
            targetController.setView(root);
            loadedControllers.put(classPathController, targetController);
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return targetController;
    }

    public Stage createNewStage(){
        return new Stage();
    }

    Map<Controller, Stage> activeControllers = new HashMap<>();
    public void show(Controller controller, Stage stage) {
        if(activeControllers.containsKey(controller)){
            stage.close();
            Stage activeStage = activeControllers.get(controller);
            activeStage.show();
            return;
        }
        try {
            stage.setOnCloseRequest(e -> {
                activeControllers.remove(e.getTarget());
            });
            stage.setScene(new Scene((Parent) controller.getView()));
            activeControllers.put(controller, stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdownAllStage(){
        activeControllers.values().forEach(Stage::close);
        activeControllers.clear();
    }

    public void shutdownAllControllers(){
        loadedControllers.values().forEach(Controller::shutdown);
        loadedControllers.clear();
    }

}
