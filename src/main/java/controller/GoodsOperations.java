package controller;

import utils.Connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GoodsOperations implements TableOperations{

    private static final String tableName  = "GOODS";
    private String insertScript;
    private String deleteScript;
    private String updateScript;

    private final Connection connection;

    GoodsOperations(Connection connection) throws IOException {
        this.connection = connection;
        loadInsertScript();
        loadUpdateScript();
        loadDeleteScript();
    }

    private void loadInsertScript() throws IOException {
        insertScript = new String(Files.readAllBytes(Paths.get(
                "src/main/resources//*TODO*/")));
    }

    private void loadDeleteScript() throws IOException {
        deleteScript = new String(Files.readAllBytes(Paths.get(
                "src/main/resources//*TODO*/")));
    }

    private void loadUpdateScript() throws IOException {
        updateScript = new String(Files.readAllBytes(Paths.get(
                "src/main/resources//*TODO*/")));
    }

    @Override
    public void insertRows(List<Row> rows) {

    }

    @Override
    public void deleteRows(List<Row> rows) {

    }

    @Override
    public void updateRows(List<Row> rows) {

    }


}
