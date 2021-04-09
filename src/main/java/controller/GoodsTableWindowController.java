package controller;

import TableRows.ProviderTableRow;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class GoodsTableWindowController extends TableWindowController{

    GoodsTableWindowController() {

        TableColumn columnId = new TableColumn("good_id");
        TableColumn columnName = new TableColumn("name");

        columnId.setCellValueFactory(new PropertyValueFactory<ProviderTableRow, String>("good_id"));
        columnName.setCellValueFactory(new PropertyValueFactory<ProviderTableRow, String>("id"));

    }

    @Override
    public void generateTable() {

    }

    @Override
    public void back() {

    }

    @Override
    public void createNewRow() {

    }

    @Override
    public void update() {

    }
}
