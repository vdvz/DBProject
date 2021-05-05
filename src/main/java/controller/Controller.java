package controller;

import init.Main;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class Controller {

    private Node view;
    private Stage stage;
    public Node getView(){
        return view;
    }

    public void setView(Node view){
        this.view = view;
    }

    public void show(){
        Main.getNavigation().show(this, getStage());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void close(){
        getStage().close();
    }

    public void shutdown(){

    }
}
