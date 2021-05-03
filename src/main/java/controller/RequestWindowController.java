package controller;

import controller.request.CountAndInfoAboutCustomersController;
import controller.request.InfoAboutCustomersController;
import init.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestWindowController extends Controller implements Initializable, RoleController{

    public static final String REQUESTS_WINDOW_FXML = "/window/requests.fxml";

    @FXML
    public Button allCustomers;
    @FXML
    public Button allProviders;
    @FXML
    public Button infoAboutActiveCustomers;
    @FXML
    public Button infoAboutDeliveriesGood;
    @FXML
    public Button infoAboutDeliveriesGoodByNumber;
    @FXML
    public Button infoAboutTradeTurnover;
    @FXML
    public Button infoAboutTradePointProfitability;
    @FXML
    public Button infoAboutVolumeOfSales;
    @FXML
    public Button infoAboutSellerSalary;
    @FXML
    public Button infoAboutGoodCustomers;

    /*
    @FXML
    public Button allCustomers;
    @FXML
    public Button allCustomers;
    @FXML
    public Button allCustomers;
    @FXML
    public Button allCustomers;
    @FXML
    public Button allCustomers;
    */

    @Override
    public void setRole(Role role) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        allCustomers.setOnAction(event -> {
            Controller controller = Main.getNavigation()
                    .load(CountAndInfoAboutCustomersController.COUNT_AND_INFO_ABOUT_CUSTOMERS_WINDOW_FXML);
            controller.setStage(Main.getNavigation().createNewStage());
            controller.show();
        });

        allProviders.setOnAction(event -> {
        });

        infoAboutActiveCustomers.setOnAction(event -> {
        });

        infoAboutDeliveriesGood.setOnAction(event -> {
        });

        infoAboutDeliveriesGoodByNumber.setOnAction(event -> {
        });

        infoAboutTradeTurnover.setOnAction(event -> {
        });

        infoAboutTradePointProfitability.setOnAction(event -> {
        });

        infoAboutVolumeOfSales.setOnAction(event -> {
        });

        infoAboutSellerSalary.setOnAction(event -> {
        });

        infoAboutGoodCustomers.setOnAction(event -> {
            Controller controller = Main.getNavigation().load(InfoAboutCustomersController.INFO_ABOUT_CUSTOMERS_WINDOW_FXML);
            controller.setStage(Main.getNavigation().createNewStage());
            controller.show();
        });
    }


}
