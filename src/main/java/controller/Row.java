package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Row {

    Map<String, Ceil> cells = new HashMap<>();

    public void addCeil(String columnName, Ceil ceil){
        cells.put(columnName, ceil);
    }

    public Ceil getCeil(String columnName){
        return cells.get(columnName);
    }

}
