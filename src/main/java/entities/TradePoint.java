package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TradePoint implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty type = new SimpleStringProperty("");
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty pointSize = new SimpleStringProperty("");
    private final StringProperty rentPrice = new SimpleStringProperty("");
    private final StringProperty communalPayments = new SimpleStringProperty("");
    private final StringProperty numberOfCounters = new SimpleStringProperty("");

    public TradePoint(String id, String type, String name, String pointSize, String rentPrice,
                      String communalPayments, String numberOfCounters){
        setId(id);
        setType(type);
        setName(name);
        setPointSize(pointSize);
        setRentPrice(rentPrice);
        setCommunalPayments(communalPayments);
        setNumberOfCounters(numberOfCounters);
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public String getPointSize() {
        return pointSize.get();
    }

    public StringProperty pointSizeProperty() {
        return pointSize;
    }

    public void setPointSize(String pointSize) {
        this.pointSize.set(pointSize);
    }

    public String getRentPrice() {
        return rentPrice.get();
    }

    public StringProperty rentPriceProperty() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice.set(rentPrice);
    }

    public String getCommunalPayments() {
        return communalPayments.get();
    }

    public StringProperty communalPaymentsProperty() {
        return communalPayments;
    }

    public void setCommunalPayments(String communalPayments) {
        this.communalPayments.set(communalPayments);
    }

    public String getNumberOfCounters() {
        return numberOfCounters.get();
    }

    public StringProperty numberOfCountersProperty() {
        return numberOfCounters;
    }

    public void setNumberOfCounters(String numberOfCounters) {
        this.numberOfCounters.set(numberOfCounters);
    }
}
