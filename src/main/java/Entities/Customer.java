package Entities;

import javafx.beans.property.StringProperty;

public class Customer {

    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty age;

    public Customer(StringProperty id, StringProperty name, StringProperty age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }
}
