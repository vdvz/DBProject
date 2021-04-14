package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty tradePoint = new SimpleStringProperty("");;
    private final StringProperty good = new SimpleStringProperty("");;
    private final StringProperty count = new SimpleStringProperty("");;
    private final StringProperty price = new SimpleStringProperty("");;

    public Account(String id, String tradePoint, String good, String count, String price) {
        setId(id);
        setTradePoint(tradePoint);
        setGood(good);
        setCount(count);
        setPrice(price);
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

    public String getGood() {
        return good.get();
    }

    public StringProperty goodProperty() {
        return good;
    }

    public void setGood(String good) {
        this.good.set(good);
    }

    public String getCount() {
        return count.get();
    }

    public StringProperty countProperty() {
        return count;
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
