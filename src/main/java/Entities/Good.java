package Entities;

import javafx.beans.property.StringProperty;

public class Good implements Entity{

    private StringProperty id;
    private StringProperty name;

    public Good(StringProperty id, StringProperty name){
        this.id = id;
        this.name = name;
    }
    public Good(String id, String name){
        idProperty().set(id);
        nameProperty().set(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
