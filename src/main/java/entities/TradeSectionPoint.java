package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TradeSectionPoint implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty tradePoint = new SimpleStringProperty("");
    private final StringProperty floor = new SimpleStringProperty("");
    private final StringProperty managersName = new SimpleStringProperty("");

    public TradeSectionPoint(String id, String tradePoint, String floor, String managersName){
        setId(id);
        setTradePoint(tradePoint);
        setFloor(floor);
        setManagersName(managersName);
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

    public String getTradePoint() {
        return tradePoint.get();
    }

    public StringProperty tradePointProperty() {
        return tradePoint;
    }

    public void setTradePoint(String tradePoint) {
        this.tradePoint.set(tradePoint);
    }

    public String getFloor() {
        return floor.get();
    }

    public StringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getManagersName() {
        return managersName.get();
    }

    public StringProperty managersNameProperty() {
        return managersName;
    }

    public void setManagersName(String managersName) {
        this.managersName.set(managersName);
    }
}
