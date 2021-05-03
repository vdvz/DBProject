package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Store implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty tradePoint = new SimpleStringProperty("");
    public Store(String id, String name, String tradePoint){
        setId(id);
        setName(name);
        setTradePoint(tradePoint);
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTradePoint() {
        return tradePoint.get();
    }

    public StringProperty tradePointProperty() {
        return tradePoint;
    }

    public void setTradePoint(String tradePoint) {
        this.tradePoint.set(tradePoint);
    }
}
