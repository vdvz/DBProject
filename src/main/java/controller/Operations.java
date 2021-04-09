package controller;

import utils.Connection;

import java.util.List;

public abstract class Operations implements TableOperations {

    private Connection connection;

    private static final String tableName  = "GOODS";

    @Override
    public void insertRows(List<Row> rows) {

    }

    @Override
    public void updateRows(List<Row> rows) {

    }

    @Override
    public void deleteRows(List<Row> rows) {

    }

}
