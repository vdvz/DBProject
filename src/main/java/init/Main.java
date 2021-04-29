package init;

import controller.Controller;
import controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.Connection;
import utils.DatabaseManager;
import utils.Navigation;

import java.sql.SQLException;

public class Main extends Application {
    private static Navigation navigation;
    private static DatabaseManager databaseManager;

    public static void main(String[] args) {
        launch(args);
    }

    public static void openConnection(String login, String password) throws SQLException, ClassNotFoundException {
        databaseManager = new DatabaseManager(new Connection(login, password));
    }

    public static void openConnection() throws SQLException, ClassNotFoundException {
        databaseManager = new DatabaseManager(new Connection());
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DB PROJECT");
        navigation = new Navigation();

        Controller controller = Main.getNavigation().load(LoginController.ENTRANCE_WINDOW_FXML);
        controller.setStage(primaryStage);
        controller.show();
    }

    @Override
    public void stop() throws Exception {
        databaseManager.closeConnection();
        super.stop();
    }

    public static Navigation getNavigation() {
        return navigation;
    }

    public static DatabaseManager getDatabaseManager(){
        return databaseManager;
    }

}
