package controller.request;

import controller.Controller;
import controller.table.Request;
import entities.*;
import init.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.Navigation;
import utils.TableNames;
import database_managers.request_managers.CountAndInfoAboutCustomersManager;

import java.net.URL;
import java.util.ResourceBundle;

public class CountAndInfoAboutCustomersController extends Controller implements Initializable,
    Request {

    public static final String COUNT_AND_INFO_ABOUT_CUSTOMERS_WINDOW_FXML = "/window/request/CountAndInfoAboutCustomers.fxml";

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

    private final CountAndInfoAboutCustomersManager manager = new CountAndInfoAboutCustomersManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadGood().stream().map(e->new ChoiceUnit(e.getId(), ((Good)e).getName())).forEach(good.getItems()::addAll);

        TableColumn<Customer, String> columnId = new TableColumn<>("id");
        TableColumn<Customer, String> columnName = new TableColumn<>("name");
        TableColumn<Customer, String> columnAge = new TableColumn<>("age");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age") );

        resultTable.getColumns().addAll(columnId, columnName, columnAge);

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
        String query = "SELECT C.ID, C.NAME, C.AGE " +
                " FROM SALES S " +
                " INNER JOIN CUSTOMERS C on C.ID=S.CUSTOMER " +
                " INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID=S.PURCHASE_COMPOSITION " +
                " INNER JOIN GOODS G on G.ID=PC.GOOD " +
                " WHERE 1=1 ";

        try {
            checkCorrectness();
        } catch (Exception e) {
            return;
        }

        query+= " AND G.ID=" + good.getValue().getId();

        if(dateFrom.getValue()!=null){
            query+= " AND PC.PURCHASE_DATE > TO_DATE('" + dateFrom.getValue().toString() + "', 'YYYY-MM-DD')";
        }
        if(dateTo.getValue()!=null){
            query+= " AND PC.PURCHASE_DATE < TO_DATE('" + dateTo.getValue().toString() + "', 'YYYY-MM-DD')";
        }
        if(dateTo.getValue()==null && dateFrom.getValue()==null){
            query+=" AND COUNT >= " + Integer.valueOf(requestCount.getText());
        }
        System.out.println("Query: " + query);

        updateResultTable(manager.executeQuery(query));
    }

    @Override
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
