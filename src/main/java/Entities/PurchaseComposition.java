package Entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PurchaseComposition implements Entity {

    private StringProperty id = new SimpleStringProperty("");
    private StringProperty good = new SimpleStringProperty("");
    private StringProperty count = new SimpleStringProperty("");
    private StringProperty resultPrice = new SimpleStringProperty("");


    public PurchaseComposition(String id, String good, String count, String resultPrice){
        setId(id);
        setGood(good);
        setCount(count);
        setResultPrice(resultPrice);
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

    public String getResultPrice() {
        return resultPrice.get();
    }

    public StringProperty resultPriceProperty() {
        return resultPrice;
    }

    public void setResultPrice(String resultPrice) {
        this.resultPrice.set(resultPrice);
    }
}
