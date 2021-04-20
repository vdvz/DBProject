package controller;

import init.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Connection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {
    public final static String ENTRANCE_WINDOW_FXML = "/entrance_window.fxml";
    public final static String LOGIN_WINDOW_FXML = "/login_window.fxml";

    @FXML
    private TextArea loginText;

    @FXML
    private PasswordField passwordText;

    @FXML
    public void defaultButtonTapped() {
        try {
            Main.openConnection();
            Controller controller = Main.getNavigation().load(ModeWindowController.MODE_WINDOW_FXML);
            controller.setStage(Main.getNavigation().createNewStage());
            controller.show();
            this.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: error with connection to server");
            showAlert("error with connection to server", "");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: error with driver manager");
            showAlert("error with driver manager", "");
        } catch (ExceptionInInitializerError ex) {
            System.out.println("ExceptionInInitializerError: session is already exist");
            showAlert("session is already exist", "");
        }
    }

    @FXML
    public void localhostButtonTapped() {
        Main.getNavigation().load(LOGIN_WINDOW_FXML).show();
    }

    @FXML
    public void backButtonTapped() {
    }

    @FXML
    public void loginButtonTapped() {
        if (isNotEmpty()) {
            try {
                Main.openConnection(loginText.getText(), passwordText.getText());
                Controller controller = Main.getNavigation().load(ModeWindowController.MODE_WINDOW_FXML);
                controller.setStage(Main.getNavigation().createNewStage());
                controller.show();
                this.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: error with connection to server");
                showAlert("error with connection to server", "Check your login and password");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException: error with driver manager");
                showAlert("error with driver manager", "");
            } catch (ExceptionInInitializerError ex) {
                System.out.println("ExceptionInInitializerError: session is already exist");
                showAlert("session is already exist", "");
            }
        } else {
            showAlert("Empty field!", "Enter login and password");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private boolean isNotEmpty() {
        return loginText.getText().length() != 0
                && passwordText.getText().length() != 0;
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}