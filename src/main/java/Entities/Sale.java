package Entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sale implements Entity{

    private final StringProperty id = new SimpleStringProperty("");
    private final StringProperty seller = new SimpleStringProperty("");
    private final StringProperty customer = new SimpleStringProperty("");
    private final StringProperty purchaseComposition = new SimpleStringProperty("");

    Sale(String id, String seller, String customer, String purchaseComposition){
        setId(id);
        setSeller(seller);
        setCustomer(customer);
        setPurchaseComposition(purchaseComposition);
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

    public String getSeller() {
        return seller.get();
    }

    public StringProperty sellerProperty() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller.set(seller);
    }

    public String getCustomer() {
        return customer.get();
    }

    public StringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getPurchaseComposition() {
        return purchaseComposition.get();
    }

    public StringProperty purchaseCompositionProperty() {
        return purchaseComposition;
    }

    public void setPurchaseComposition(String purchaseComposition) {
        this.purchaseComposition.set(purchaseComposition);
    }
}
