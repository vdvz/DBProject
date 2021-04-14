package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Seller implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty salary = new SimpleStringProperty("");
    private final StringProperty tradePoint = new SimpleStringProperty("");
    private final StringProperty tradeRoom = new SimpleStringProperty("");

    public Seller(String id, String name, String salary, String tradePoint, String tradeRoom){
        setId(id);
        setName(name);
        setSalary(salary);
        setTradePoint(tradePoint);
        setTradeRoom(tradeRoom);
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

    public String getSalary() {
        return salary.get();
    }

    public StringProperty salaryProperty() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
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

    public String getTradeRoom() {
        return tradeRoom.get();
    }

    public StringProperty tradeRoomProperty() {
        return tradeRoom;
    }

    public void setTradeRoom(String tradeRoom) {
        this.tradeRoom.set(tradeRoom);
    }
}
