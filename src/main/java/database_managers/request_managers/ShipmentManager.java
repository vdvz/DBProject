package database_managers.request_managers;

import init.Main;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Connection;

public class ShipmentManager {

  private final Connection connection;

  public ShipmentManager() {
    this.connection = Main.getDatabaseManager().getConnection();
  }

  public Integer addDelivery(Map<String, String> value) throws SQLException {
    String query =
        "insert into DELIVERIES( provider_id, trade_point_id, deliver_date) values (?, ?, ?)";

    PreparedStatement statement =
        connection.getConnection().prepareStatement(query, new String[] {"id"});

    statement.setInt(1, Integer.parseInt(value.get("provider_id")));
    statement.setInt(2, Integer.parseInt(value.get("trade_point_id")));
    statement.setDate(3, Date.valueOf(value.get("deliver_date")));

    statement.execute();

    ResultSet result = statement.getGeneratedKeys();
    result.next();
    return result.getInt(1);
  }

  public void addDeliveredGoods(List<Map<String, String>> values) throws SQLException {
    String query =
        "insert into Deliveries_goods(good_id, delivery_id, count, price) "
            + "values (?, ?, ?, ?)";
    PreparedStatement statement = connection.prepareStatement(query);
    for (Map<String, String> value : values) {
      statement.setInt(1, Integer.parseInt(value.get("good_id")));
      statement.setInt(2, Integer.parseInt(value.get("delivery_id")));
      statement.setInt(3, Integer.parseInt(value.get("count")));
      statement.setInt(4, Integer.parseInt(value.get("price")));

      statement.execute();
    }
  }

  public void startTransaction() {
    connection.setAutoCommit(false);
  }

  public void rollbackTransaction() {
    connection.rollback();
    connection.setAutoCommit(true);
  }

  public void commitTransaction() {
    connection.commit();
    connection.setAutoCommit(true);
  }

}
