package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TradeRoom implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty tradePointId = new SimpleStringProperty("");

    public TradeRoom(String id, String tradePointId){
        setId(id);
        setTradePointId(tradePointId);
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

    public String getTradePointId() {
        return tradePointId.get();
    }

    public StringProperty tradePointIdProperty() {
        return tradePointId;
    }

    public void setTradePointId(String tradePointId) {
        this.tradePointId.set(tradePointId);
    }
}
