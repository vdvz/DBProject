package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TradeRoom implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty tradeSectionPointId = new SimpleStringProperty("");

    public TradeRoom(String id, String tradeSectionPointId){
        setId(id);
        setTradeSectionPointId(tradeSectionPointId);
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

    public String getTradeSectionPointId() {
        return tradeSectionPointId.get();
    }

    public StringProperty tradeSectionPointIdProperty() {
        return tradeSectionPointId;
    }

    public void setTradeSectionPointId(String tradePointId) {
        this.tradeSectionPointId.set(tradePointId);
    }
}
