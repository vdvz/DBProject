package utils;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

import javax.swing.*;

public class EnterItem extends AnchorPane {

    private final Label columnNameLabel = new Label();
    private final TextField textField = new TextField();

    public EnterItem(String columnName){
        super();
        columnNameLabel.setText(columnName);
        columnNameLabel.setAlignment(Pos.BASELINE_CENTER);
        columnNameLabel.setContentDisplay(ContentDisplay.CENTER);
        columnNameLabel.setTextAlignment(TextAlignment.CENTER);
        setBottomAnchor(textField,0.0);
        setLeftAnchor(textField,0.0);
        setRightAnchor(textField,0.0);

        textField.setOnAction(e->{
            String gettingText = textField.getText();
            if(gettingText.length()>50) {
                textField.setText(gettingText.substring(0, 50));
            }else{
                textField.setText(gettingText);
            }

        });

        this.getChildren().addAll(columnNameLabel, textField);
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public String getColumnName(){
        return columnNameLabel.getText();
    }

    public String getEnteredText(){
        return textField.getText();
    }

}
