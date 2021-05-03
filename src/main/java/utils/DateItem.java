package utils;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateItem extends AnchorPane {
    private final Label columnNameLabel = new Label();
    private final DatePicker datePicker = new DatePicker();

    public DateItem(String columnName){
        super();
        //setPrefHeight(35.0);
        columnNameLabel.setAlignment(Pos.BASELINE_CENTER);
        //columnNameLabel.setAlignment(Pos.CENTER);
        columnNameLabel.setContentDisplay(ContentDisplay.CENTER);
        columnNameLabel.setTextAlignment(TextAlignment.CENTER);
        columnNameLabel.setText(columnName);
        setBottomAnchor(datePicker,0.0);
        setLeftAnchor(datePicker,0.0);
        setRightAnchor(datePicker,0.0);
        this.getChildren().addAll(columnNameLabel, datePicker);
    }

    public LocalDate getDate(){
        System.out.println(datePicker.getValue().toString());
        return datePicker.getValue();
    }

    public void setDate(String date){
        datePicker.setValue(LocalDate.parse(date));
    }

    public String getColumnName(){
        return columnNameLabel.getText();
    }

}
