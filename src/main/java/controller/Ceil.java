package controller;

public class Ceil {

    public enum TYPE{

    }

    private final TYPE type;
    private final String column;
    private final String value;

    Ceil(String column, String value, TYPE type){
        this.type = type;
        this.value = value;
        this.column = column;

    }

    public String getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    public TYPE getType() {
        return type;
    }


}
