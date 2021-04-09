package TableRows;

import com.sun.istack.internal.NotNull;
import javafx.beans.property.SimpleStringProperty;

public class ProviderTableRow {
    @NotNull
    private final SimpleStringProperty good_id;
    @NotNull
    private final SimpleStringProperty name;


    public ProviderTableRow(SimpleStringProperty id, SimpleStringProperty name) {
        this.good_id = id;
        this.name = name;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return good_id.get();
    }

    public SimpleStringProperty idProperty() {
        return good_id;
    }
}
