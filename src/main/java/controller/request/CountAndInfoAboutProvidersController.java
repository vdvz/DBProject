package controller.request;

import controller.Controller;
import controller.table.Request;
import database_managers.request_managers.CountAndInfoAboutProvidersManager;
import entities.Entity;
import entities.Good;
import entities.Provider;
import init.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import utils.Navigation;
import utils.TableNames;

public class CountAndInfoAboutProvidersController extends Controller implements Initializable,
    Request {

    public static final String COUNT_AND_INFO_ABOUT_PROVIDERS_WINDOW_FXML = "/window/request/CountAndInfoAboutProviders.fxml";

    @FXML
    private ChoiceBox<ChoiceUnit> good;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Label count;

    @FXML
    private TableView resultTable;

    @FXML
    private TextField requestCount;

    private final CountAndInfoAboutProvidersManager manager = new CountAndInfoAboutProvidersManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadGood().stream().map(e->new ChoiceUnit(e.getId(), ((Good)e).getName())).forEach(good.getItems()::addAll);

        TableColumn<Provider, String> columnId = new TableColumn<>("id");
        TableColumn<Provider, String> columnName = new TableColumn<>("name");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        resultTable.getColumns().addAll(columnId, columnName);

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
    }

    private ObservableList<Entity> loadGood(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

    @FXML
    public void clearRequestCount(){
        requestCount.setText("");
    }

    @FXML
    public void clearDate(){
        dateFrom.setValue(null);
        dateTo.setValue(null);
    }

    @FXML
    public void query(){
        String query = "SELECT * FROM PROVIDERS P "
            + " INNER JOIN DELIVERIES D on D.PROVIDER_ID = P.ID"
            + " INNER JOIN DELIVERIES_GOODS DG on D.ID = DG.DELIVERY_ID"
            + " INNER JOIN GOODS G on G.ID = DG.GOOD_ID"
            + " WHERE 1=1";

        try {
            checkCorrectness();
        } catch (Exception e) {
            return;
        }

        query+= " AND G.ID=" + good.getValue().getId();

        if(dateFrom.getValue()!=null){
            query+= " AND D.DELIVER_DATE > TO_DATE('" + dateFrom.getValue().toString() + "', 'YYYY-MM-DD')";
        }
        if(dateTo.getValue()!=null){
            query+= " AND D.DELIVER_DATE < TO_DATE('" + dateTo.getValue().toString() + "', 'YYYY-MM-DD')";
        }
        if(dateTo.getValue()==null && dateFrom.getValue()==null){
            query+=" AND COUNT >= " + Integer.valueOf(requestCount.getText());
        }
        System.out.println("Query: " + query);

        updateResultTable(manager.executeQuery(query));
    }

     public void checkCorrectness() throws Exception {
         if(good.getValue()==null){
             Navigation.showAlert("Ввод невалидных данных", "Выберите товар");
             throw new Exception();
         }
         if(dateTo.getValue()==null && dateFrom.getValue()==null){
             Navigation.showAlert("Ввод невалидных данных", "Введите дату.");
             throw new Exception();
         }
         if(requestCount.getText().equals("")){
             Navigation.showAlert("Ввод невалидных данных", "Введите количество.");
             throw new Exception();
         }
    }

    private void clearResultTable(){
        count.setText(String.valueOf(0));
        resultTable.getItems().clear();
    }

    private void updateResultTable(ObservableList<Entity> result){
        clearResultTable();
        count.setText(String.valueOf(result.size()));
        resultTable.getItems().addAll(result);
    }

}
