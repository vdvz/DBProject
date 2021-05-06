package controller;

import controller.request.CountAndInfoAboutCustomersController;
import controller.request.CountAndInfoAboutProvidersController;
import controller.request.InfoAboutActiveCustomersController;
import controller.request.InfoAboutCustomersController;
import controller.request.InfoAboutDeliveriesGoodController;
import controller.request.InfoAboutSellerSalaryController;
import controller.request.InfoAboutSellerWorkController;
import controller.request.InfoAboutTradeTurnoverController;
import init.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RequestWindowController extends Controller implements Initializable, RoleController {

  public static final String REQUESTS_WINDOW_FXML = "/window/requests.fxml";

  @FXML public Button allCustomers;
  @FXML public Button allProviders;
  @FXML public Button infoAboutActiveCustomers;
  @FXML public Button infoAboutDeliveriesGood;
  @FXML public Button infoAboutDeliveriesGoodByNumber;
  @FXML public Button infoAboutTradeTurnover;
  @FXML public Button infoAboutTradePointProfitability;
  @FXML public Button infoAboutVolumeOfSales;
  @FXML public Button infoAboutSellerSalary;
  @FXML public Button infoAboutGoodCustomers;
  @FXML public Button infoAboutSellerWork;

  /*
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
  public void setRole(Role role) {}

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    allCustomers.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(
                      CountAndInfoAboutCustomersController
                          .COUNT_AND_INFO_ABOUT_CUSTOMERS_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    allProviders.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(
                      CountAndInfoAboutProvidersController
                          .COUNT_AND_INFO_ABOUT_PROVIDERS_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    infoAboutActiveCustomers.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(InfoAboutActiveCustomersController.INFO_ABOUT_ACTIVE_CUSTOMERS_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    infoAboutDeliveriesGood.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(InfoAboutDeliveriesGoodController.INFO_ABOUT_DELIVERIES_GOOD_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    infoAboutDeliveriesGoodByNumber.setOnAction(event -> {});

    infoAboutTradeTurnover.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(InfoAboutTradeTurnoverController.INFO_ABOUT_TRADE_TURNOVER_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    infoAboutTradePointProfitability.setOnAction(event -> {});

    infoAboutVolumeOfSales.setOnAction(event -> {});

    infoAboutSellerSalary.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(InfoAboutSellerSalaryController.INFO_ABOUT_SELLER_SALARY_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    infoAboutGoodCustomers.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(InfoAboutCustomersController.INFO_ABOUT_CUSTOMERS_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

    infoAboutSellerWork.setOnAction(
        event -> {
          Controller controller =
              Main.getNavigation()
                  .load(InfoAboutSellerWorkController.INFO_ABOUT_SELLER_WORK_WINDOW_FXML);
          controller.setStage(Main.getNavigation().createNewStage());
          controller.show();
        });

  }



}
