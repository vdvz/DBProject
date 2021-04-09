package controller;

import controller.Row;

import java.util.List;

public interface TableOperations {

    void insertRows(List<Row> rows);

    void deleteRows(List<Row> rows);

    void updateRows(List<Row> rows);

}
