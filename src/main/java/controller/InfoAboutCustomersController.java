package controller;

import entities.*;
import init.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import utils.ChoiceUnit;
import utils.TableNames;
import utils.table_managers.InfoAboutCustomersManager;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class InfoAboutCustomersController extends Controller implements Initializable {
    public static final String INFO_ABOUT_CUSTOMERS_WINDOW_FXML = "/InfoAboutCustomers_window.fxml";

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

    @FXML
    private TableView resultTable;

    private final InfoAboutCustomersManager manager = new InfoAboutCustomersManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadGood().stream().map(e->new ChoiceUnit(e.getId(), ((Good)e).getName())).forEach(this::addItemsToGood);
        loadTradePoint().stream().map(e->new ChoiceUnit(e.getId(), ((TradePoint)e).getName())).forEach(this::addItemsToTradePoints);
        loadTradePointType().stream().map(e->new ChoiceUnit(e.getId(), ((TradeType)e).getName())).forEach(this::addItemsToTradePointType);

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

        tradePoint.setConverter(new StringConverter<ChoiceUnit>() {
            @Override
            public String toString(ChoiceUnit object) {
                return object.getDisplayedName();
            }

            @Override
            public ChoiceUnit fromString(String string) {
                return tradePoint.getItems().stream().filter(e -> e.getDisplayedName().equals(string)).findFirst().orElse(null);
            }
        });

        tradePointType.setConverter(new StringConverter<ChoiceUnit>() {
            @Override
            public String toString(ChoiceUnit object) {
                return object.getDisplayedName();
            }

            @Override
            public ChoiceUnit fromString(String string) {
                return tradePointType.getItems().stream().filter(e -> e.getDisplayedName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private ObservableList<Entity> loadGood(){
        return Main.getDatabaseManager().getTableManager(TableNames.GOODS).getTableRows();
    }

    private ObservableList<Entity> loadTradePointType(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_TYPE).getTableRows();
    }

    private ObservableList<Entity> loadTradePoint(){
        return Main.getDatabaseManager().getTableManager(TableNames.TRADE_POINTS).getTableRows();
    }

    public void addItemsToGood(ChoiceUnit ... items) {
        good.getItems().addAll(items);
    }

    public void addItemsToTradePoints(ChoiceUnit ... items) {
        tradePoint.getItems().addAll(items);
    }

    public void addItemsToTradePointType(ChoiceUnit ... items) {
        tradePointType.getItems().addAll(items);
    }

    @FXML
    public void clearTradePoint(){
        tradePoint.getItems().clear();
        tradePointType.getItems().clear();
    }

    @FXML
    public void clearDate(){
        dateFrom.setValue(null);
        dateTo.setValue(null);
    }

    @FXML
    public void query(){
        String query = "SELECT C2.ID, C2.NAME, C2.AGE " + " FROM SALES S " +
                " INNER JOIN CUSTOMERS C2 on C2.ID=S.CUSTOMER " +
                " INNER JOIN PURCHASE_COMPOSITIONS PC on PC.ID=S.PURCHASE_COMPOSITION " +
                " INNER JOIN GOODS G on G.ID=PC.GOOD " +
                " INNER JOIN SELLERS S2 on S2.ID=S.SELLER " +
                " INNER JOIN TRADE_POINTS TP on TP.ID=S2.TRADE_POINT " +
                " INNER JOIN TRADE_TYPES TT on TP.TYPE=TT.ID " +
                " WHERE G.NAME='";
        if(good.getValue()==null){
            //TODO exception
            return;
        }

        query+= good.getValue().getDisplayedName() + "' ";

        if((dateFrom.getValue()!=null & dateTo.getValue()==null)
                ||(dateFrom.getValue()==null & dateTo.getValue()!=null)){
            //TODO exception
            return;
        }

        if(dateFrom.getValue()!=null){
            query+= "AND PC.PURCHASE_DATE > " + dateFrom.getValue().toString() + " ";
            if(dateTo.getValue()!=null){
                query+= "AND PC.PURCHASE_DATE < " + dateTo.getValue().toString() + " ";
            }
        }

        if(tradePoint.getValue()!=null && tradePointType.getValue()!=null){
            //TODO throw exception
            return;
        }

        if(tradePoint.getValue()!=null && tradePointType.getValue()==null){
            query+= " AND TP.NAME='" + tradePoint.getValue().getDisplayedName() +"' ";
        }else if(tradePoint.getValue()==null && tradePointType.getValue()!=null){
            query+= " AND TT.NAME='" + tradePointType.getValue().getDisplayedName() +"' ";
        }

        System.out.println("Query: " + query);

        updateResultTable(manager.executeQuery(query));
    }

    private void clearResultTable(){
        resultTable.getItems().clear();
    }

    private void updateResultTable(ObservableList<Entity> result){
        clearResultTable();
        resultTable.getItems().addAll(result);
    }

}
