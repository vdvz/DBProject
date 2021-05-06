package database_managers.request_managers;

import entities.Entity;
import entities.Seller;
import init.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

public class InfoAboutTradeTurnoverManager {

  private final Connection connection;
  public InfoAboutTradeTurnoverManager(){
    this.connection = Main.getDatabaseManager().getConnection();
  }

  public ObservableList<Entity> executeQuery(String query) {
    ObservableList<Entity> resultList = FXCollections.observableArrayList();
    ResultSet result;
    try {
      result = connection.executeQuery(query);
      while(result.next()){
        String id = result.getObject("id").toString();
        String name = result.getObject("name").toString();
        String salary = result.getObject("salary").toString();
        String tradePoint = result.getObject("trade_point").toString();
        String tradeRoom = result.getObject("trade_room").toString();

        resultList.add(new Seller(id, name, salary, tradePoint, tradeRoom));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return resultList;
  }
}
