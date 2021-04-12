package utils.tableManagers;

import TableRows.ProviderTableRow;
import controller.tables.TableWindowController;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Connection;

import java.util.List;

public class SalesTableManager extends TableManager {


    public SalesTableManager(Connection connection) {
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
