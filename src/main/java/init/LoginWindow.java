package init;


import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class LoginWindow extends Group {

  public LoginWindow() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(12);

    HBox hbButtons = new HBox();
    hbButtons.setSpacing(10.0);

    Button btnSubmit = new Button("Login");
    Button btnExit = new Button("Exit");
    btnSubmit.setFont(Font.font("-fx-font-size: 15pt;"));

    Label lblName = new Label("User name:");
    TextField tfName = new TextField();
    Label lblPwd = new Label("Password:");
    PasswordField pfPwd = new PasswordField();

    hbButtons.getChildren().addAll(btnSubmit, btnExit);
    grid.add(lblName, 0, 0);
    grid.add(tfName, 1, 0);
    grid.add(lblPwd, 0, 1);
    grid.add(pfPwd, 1, 1);
    grid.add(hbButtons, 0, 2, 2, 1);

  }

}
