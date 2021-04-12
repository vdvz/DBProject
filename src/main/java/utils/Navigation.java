package utils;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Navigation {

    private final List<Controller> controllers = new ArrayList<>();

    private Map<Controller, Stage> existingStages = new HashMap<>();
    private Map<String, Controller> loadedScenes = new HashMap<>();


    public Navigation(Stage stage) {

    }

    public Controller load(String sUrl) {
        Controller controller = null;
        if((controller = loadedScenes.get(sUrl)) != null){
            return controller;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            Node root = fxmlLoader.load();

            controller = fxmlLoader.getController();
            controller.setView(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controller;
    }

    public boolean isLoaded(String path){
        return loadedScenes.containsKey(path);
    }

    public Controller loadTable(String sUrl, String classPathController) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Controller targetController = (Controller) Class.forName(classPathController).getConstructor().newInstance();
        try {
            System.out.println(targetController.getClass());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            fxmlLoader.setController(targetController);
            Node root = fxmlLoader.load();
            targetController.setView(root);
            System.out.println("Loaded");
            loadedScenes.put(classPathController, targetController);
            return targetController;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Stage createNewStage(){
        return new Stage();
    }

    public void show(Controller controller, Stage stage) {
        try {
            stage.setScene(new Scene((Parent) controller.getView()));
            controllers.add(controller);
            stage.show();
            System.out.println("Add to history: " + controller.toString() + ". Total scenes: " + controllers.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearHistory() {
        while (controllers.size() > 1) {
            controllers.remove(0);
        }

        System.out.println("ClearHistory. Total scenes: " + controllers.size());
    }

}
