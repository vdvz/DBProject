package database_managers.request_managers;

import entities.Entity;
import entities.Provider;
import init.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

public class CountAndInfoAboutProvidersManager {

  private final Connection connection;

  public CountAndInfoAboutProvidersManager() {
    this.connection = Main.getDatabaseManager().getConnection();
  }

  public ObservableList<Entity> executeQuery(String query) {
    ObservableList<Entity> resultList = FXCollections.observableArrayList();
    ResultSet result;
    try {
      result = connection.executeQuery(query);
      while (result.next()) {
        String id = result.getObject("id").toString();
        String name = result.getObject("name").toString();
        resultList.add(new Provider(id, name));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return resultList;

  }
}
