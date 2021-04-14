package controller.insertion;

import entities.Entity;
import controller.Controller;
import init.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import utils.EnterItem;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class InsertionWindowController extends Controller implements Initializable {

    public enum MODE{
        INSERTING,
        UPDATING
    }

    private static final Map<String, String> tableNameToController = new HashMap<String, String>(){{
        put("ACCOUNTING","controller.insertion.AccountingInsertionWindowController");
        put("CUSTOMERS","controller.insertion.CustomersInsertionWindowController");
        put("DELIVERIES_GOODS","controller.insertion.DeliveriesGoodsInsertionWindowController");
        put("DELIVERIES","controller.insertion.DeliveriesInsertionWindowController");
        put("GOODS","controller.insertion.GoodsInsertionWindowController");
        put("PROVIDERS","controller.insertion.ProvidersInsertionWindowController");
        put("PURCHASE_COMPOSITIONS","controller.insertion.PurchaseCompositionsInsertionWindowController");
        put("SALES","controller.insertion.SalesInsertionWindowController");
        put("SELLERS","controller.insertion.SellersInsertionWindowController");
        put("TRADE_POINTS","controller.insertion.TradePointsInsertionWindowController");
        put("TRADE_ROOM","controller.insertion.TradeRoomInsertionWindowController");
        put("TRADE_SECTION_POINT","controller.insertion.TradeSectionPointInsertionWindowController");
        put("TRADE_TYPES","controller.insertion.TradeTypesInsertionWindowController");
    }
    };

    public static String getNameOfController(String tableName){
        return tableNameToController.get(tableName);
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
            try {
                insertRow();
                Main.getNavigation().closeStage(getStage());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public EnterItem getIdItem() {
        return idItem;
    }

    public abstract void insertRow() throws SQLException;

    public abstract void initUpdating(Entity value);

}
