package database_managers.request_managers;

import init.Main;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Connection;

public class PurchaseManager {

  private final Connection connection;

  public PurchaseManager() {
    this.connection = Main.getDatabaseManager().getConnection();
  }

  public int addCustomer(Map<String, String> value) throws SQLException {
    CallableStatement statement =
        connection.createCallableStatement("{call ADD_CUSTOMER(?, ?, ?)}");

    statement.setString(1, value.get("name"));
    statement.setInt(2, Integer.parseInt(value.get("age")));
    statement.registerOutParameter(3, Types.INTEGER);

    statement.execute();

    return statement.getInt(3);
  }

  public List<Integer> addPurchaseCompositions(List<Map<String, String>> values)
      throws SQLException {
    List<Integer> result = new ArrayList<>();
    CallableStatement statement =
        connection.createCallableStatement("{call add_purchase(?, ?, ?, ?, ?)}");

    statement.registerOutParameter(5, Types.INTEGER);
    for (Map<String, String> value: values) {
      statement.setInt(1, Integer.parseInt(value.get("good")));
      statement.setInt(2, Integer.parseInt(value.get("count")));
      statement.setInt(3, Integer.parseInt(value.get("result_price")));
      statement.setDate(4, Date.valueOf(value.get("purchase_date")));

      statement.execute();
      result.add(statement.getInt(5));
    }

    return result;
  }

  public void addSales(List<Map<String, String>> values) throws SQLException {
    for (Map<String, String> value : values) {
      String query = "INSERT INTO SALES(seller, customer, purchase_composition) VALUES ("
          +value.get("seller") + ", " + value.get("customer") + value.get("purchase_composition") + ")";
      connection.execute(query);
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
