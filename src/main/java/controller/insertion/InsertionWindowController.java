package controller.insertion;

import Entities.Entity;
import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.EnterItem;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class InsertionWindowController extends Controller implements Initializable {

    public enum MODE{
        INSERTING,
        UPDATING
    }

    private MODE mode = MODE.INSERTING;

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public MODE getMode() {
        return mode;
    }

    @FXML
    public HBox hBox;

    @FXML
    public Button button;

    private EnterItem idItem;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idItem = new EnterItem("id");
        button.setOnAction(e->{
            insertRow();
        });
    }


    public EnterItem getIdItem() {
        return idItem;
    }

    public abstract void insertRow();

    public abstract void initUpdating(Entity value);

}
