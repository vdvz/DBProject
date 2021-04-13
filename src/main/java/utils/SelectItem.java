package utils;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.util.StringConverter;

public class SelectItem extends AnchorPane {
    private final Label columnNameLabel = new Label();
    private final ComboBox<ChoiceUnit> comboBox = new ComboBox<>();

    public SelectItem(String columnName){
        super();
        setPrefHeight(35.0);
        columnNameLabel.setAlignment(Pos.CENTER);
        columnNameLabel.setTextAlignment(TextAlignment.CENTER);
        columnNameLabel.setText(columnName);
        setBottomAnchor(comboBox,0.0);
        setLeftAnchor(comboBox,0.0);
        setRightAnchor(comboBox,0.0);
        this.getChildren().addAll(columnNameLabel, comboBox);
        comboBox.setConverter(new StringConverter<ChoiceUnit>() {
          @Override
          public String toString(ChoiceUnit object) {
            return object.getDisplayedName();
          }

          @Override
          public ChoiceUnit fromString(String string) {
            return comboBox.getItems().stream().filter(e -> e.getDisplayedName().equals(string)).findFirst().orElse(null);
          }
        });
    }

    public void addItemsToSelect(ChoiceUnit ... items){
        comboBox.getItems().addAll(items);
    }

    public void setSelectItem(String id){
        comboBox.getSelectionModel().select(comboBox.getItems().filtered(e->e.getId().equals(id)).get(0));
    }

    public void removeAllItems(){
        comboBox.getItems().clear();
    }

    public ChoiceUnit getSelectedItem(){
        return comboBox.getValue();
    }

    public String getColumnName(){
        return columnNameLabel.getText();
    }

}
