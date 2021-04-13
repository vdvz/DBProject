package Entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Delivery implements Entity {

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty providerId = new SimpleStringProperty("");;
    private final StringProperty tradePointId = new SimpleStringProperty("");;
    private final StringProperty count = new SimpleStringProperty("");;
    private final StringProperty deliverDate = new SimpleStringProperty("");;


    public Delivery(String id, String providerId, String tradePointId, String count, String deliverDate){
        setId(id);
        setProviderId(providerId);
        setTradePointId(tradePointId);
        setCount(count);
        setDeliverDate(deliverDate);
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

    public String getProviderId() {
        return providerId.get();
    }

    public StringProperty providerIdProperty() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId.set(providerId);
    }

    public String getTradePointId() {
        return tradePointId.get();
    }

    public StringProperty tradePointIdProperty() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId.set(tradePointId);
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

    public String getDeliverDate() {
        return deliverDate.get();
    }

    public StringProperty deliverDateProperty() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate.set(deliverDate);
    }
}
