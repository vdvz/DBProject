package controller.Entities;

import javafx.beans.property.StringProperty;

public class Account {

    private final StringProperty id;
    private final StringProperty tradePoint;
    private final StringProperty good;
    private final StringProperty count;
    private final StringProperty price;

    public Account(StringProperty id, StringProperty tradePoint, StringProperty good, StringProperty count, StringProperty price) {
        this.id = id;
        this.tradePoint = tradePoint;
        this.good = good;
        this.count = count;
        this.price = price;
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
