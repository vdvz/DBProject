package database_managers.request_managers;

import entities.Customer;
import entities.Entity;
import init.Main;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connection;

public class InfoAboutDeliveriesGoodManager {

    private final Connection connection;
    public InfoAboutDeliveriesGoodManager(){
        this.connection = Main.getDatabaseManager().getConnection();
    }

    public ObservableList<Entity> executeQuery(String query){

        ObservableList<Entity> resultList = FXCollections.observableArrayList();
        ResultSet result;
        try {
            result = connection.executeQuery(query);
            while(result.next()){
                String id = result.getObject("id").toString();
                String name = result.getObject("name").toString();
                String age = result.getObject("age").toString();
                resultList.add(new Customer(id, name, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

}
