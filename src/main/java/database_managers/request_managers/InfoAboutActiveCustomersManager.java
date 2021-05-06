package database_managers.request_managers;

import entities.Entity;
import init.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

public class InfoAboutActiveCustomersManager {

  private final Connection connection;

  public InfoAboutActiveCustomersManager() {
    this.connection = Main.getDatabaseManager().getConnection();
  }

  public ObservableList<Entity> executeQuery(String query) {
    ObservableList<Entity> resultList = FXCollections.observableArrayList();
    ResultSet result;
    try {
      result = connection.executeQuery(query);
      while (result.next()) {
        /*TODO*/
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return resultList;

  }
}
