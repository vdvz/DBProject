package utils.tableManagers;

import controller.tables.TableWindowController;
import javafx.scene.control.TableColumn;
import utils.Connection;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TradePointsTableManager extends TableManager {

    public TradePointsTableManager(Connection connection) {
        super(connection);
    }

    @Override
    public String loadSelectionQuery() {
        return null;
    }

    @Override
    public String loadInsertionQuery() {
        return null;
    }

    @Override
    public String loadDeleteQuery() {
        return null;
    }

    @Override
    public String loadUpdateQuery() {
        return null;
    }

    @Override
    List<String> getColumnNames() {
        return null;
    }

}
