package controller.insertion;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class InsertionWindowController extends Controller implements Initializable {
    @FXML
    public HBox hBox;

    @FXML
    public Button button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setOnAction(e->{
            insertRow();
        });
    }

    public abstract void insertRow();

}
