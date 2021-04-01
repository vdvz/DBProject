package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Executor {

  private final Connection connection;

  public Executor(Connection connection){
    this.connection = connection;
  }

  public void insertAll(List<String> queryList){
    queryList.forEach(this::insert);
  }

  public void insert(String query) {
    try {
      connection.execute(query);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public List<List<String>> select(String sql){
      return select(sql, result -> {
          try {
              ArrayList<String> list = new ArrayList<>(1);
              list.add(result.getString(1));
              return list;
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return null;
      });
  }

  public List<List<String>> select(String sql, Function<ResultSet, List<String>> toString){

    List<List<String>> names = new LinkedList<>();
    try {
      ResultSet result = connection.executeQuery(sql);
      while (result.next()){
        names.add(toString.apply(result));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return names;
  }

  public String createQuery(String template, String data){
      return template + data;
  }

  public void delete(String query) {
    try {
      connection.execute(query);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }

}
