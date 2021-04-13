package Entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeliveriesGood implements Entity {

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty providerId = new SimpleStringProperty("");
    private final StringProperty goodId = new SimpleStringProperty("");
    private final StringProperty deliveryId = new SimpleStringProperty("");
    private final StringProperty price = new SimpleStringProperty("");


    public DeliveriesGood(String id, String providerId, String goodId, String deliveryId, String price){
        setId(id);
        setProviderId(providerId);
        setGoodId(goodId);
        setDeliveryId(deliveryId);
        setPrice(price);
    }

    public String getGoodId() {
        return goodId.get();
    }

    public StringProperty goodIdProperty() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId.set(goodId);
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

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getDeliveryId() {
        return deliveryId.get();
    }

    public StringProperty deliveryIdProperty() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId.set(deliveryId);
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
