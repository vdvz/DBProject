package controller.request;

import controller.Controller;
import database_managers.request_managers.CountAndInfoAboutProvidersManager;
import database_managers.request_managers.InfoAboutDeliveriesGoodManager;
import entities.DeliveriesGood;
import entities.Delivery;
import entities.Entity;
import entities.Good;
import entities.Provider;
import init.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.TableNames;

public class InfoAboutDeliveriesGoodController extends Controller implements Initializable {

  public static final String INFO_ABOUT_DELIVERIES_GOOD_WINDOW_FXML = "/window/request/InfoAboutDeliveriesGood.fxml";

  @FXML
  private ChoiceBox<ChoiceUnit> good;

  @FXML
  private ChoiceBox<ChoiceUnit> provider;

  @FXML
  private DatePicker dateFrom;

  @FXML
  private DatePicker dateTo;

  @FXML
  private TableView resultTable;

  @FXML
  private TextField requestCount;

  private final InfoAboutDeliveriesGoodManager manager = new InfoAboutDeliveriesGoodManager();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadGood().stream().map(e->new ChoiceUnit(e.getId(), ((Good)e).getName())).forEach(good.getItems()::addAll);
    loadProvider().stream().map(e->new ChoiceUnit(e.getId(), ((Provider)e).getName())).forEach(provider.getItems()::addAll);

    TableColumn<DeliveriesGood, String> columnId = new TableColumn<>("id");
    TableColumn<DeliveriesGood, String> columnGoodId = new TableColumn<>("good_id");
    TableColumn<DeliveriesGood, String> columnDeliveryName = new TableColumn<>("delivery_id");
    TableColumn<Delivery, String> columnCount = new TableColumn<>("count");
    TableColumn<DeliveriesGood, String> columnPrice = new TableColumn<>("price");

    columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
    columnGoodId.setCellValueFactory(new PropertyValueFactory<>("goodId"));
    columnDeliveryName.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
    columnCount.setCellValueFactory(new PropertyValueFactory<>("count"));
    columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    resultTable.getColumns().addAll(columnId, columnGoodId, columnDeliveryName, columnCount, columnPrice);

    good.setConverter(new StringConverter<ChoiceUnit>() {
      @Override
      public String toString(ChoiceUnit object) {
        return object.getDisplayedName();
      }

      @Override
      public ChoiceUnit fromString(String string) {
        return good.getItems().stream().filter(e -> e.getDisplayedName().equals(string)).findFirst().orElse(null);
      }
    });

    provider.setConverter(new StringConverter<ChoiceUnit>() {
      @Override
      public String toString(ChoiceUnit object) {
        return object.getDisplayedName();
      }

      @Override
      public ChoiceUnit fromString(String string) {
        return provider.getItems().stream().filter(e -> e.getDisplayedName().equals(string)).findFirst().orElse(null);
      }
    });
  }
  @FXML
  public void clearDate(){
    dateFrom.setValue(null);
    dateTo.setValue(null);
  }

  private ObservableList<Entity> loadGood(){
    return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
  }
  private ObservableList<Entity> loadProvider(){
    return Main.getDatabaseManager().getTableManager(TableNames.PROVIDERS).getTableRows();
  }

  public void query() {
    /*TODO*/
  }
}
