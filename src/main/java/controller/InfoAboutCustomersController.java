package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import utils.ChoiceUnit;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoAboutCustomersController extends Controller implements Initializable {

    @FXML
    private ChoiceBox<ChoiceUnit> good;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private ChoiceBox<ChoiceUnit> tradePointType;

    @FXML
    private ChoiceBox<ChoiceUnit> tradePoint;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addItemsToTradePoints(ChoiceUnit ... items) {
        tradePoint.getItems().addAll(items);
    }

    public void addItemsToTradePointType(ChoiceUnit ... items) {
        tradePointType.getItems().addAll(items);
    }

    //TODO
    public void setTradePoint(ChoiceBox<ChoiceUnit> tradePoint) {
        this.tradePoint = tradePoint;
    }

    //TODO
    public void setTradePointType(ChoiceBox<ChoiceUnit> tradePointType) {
        this.tradePointType = tradePointType;
    }

    @FXML
    public void clearTradePoint(){
    }

    @FXML
    public void clearDate(){
    }

    @FXML
    public void query(){
    }


}
