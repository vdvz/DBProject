package utils;

import controller.Operations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

public class DatabaseManager extends Operations {
    private static final String[] tableNamesArray = {"TradePoints.sql", "Goods.sql", "TradeTypes.sql"}; //, "Seller.sql", "Sales.sql",
           // "Providers.sql", "Accounting.sql", "Customers.sql", "Deliveries.sql", "Deliveries_goods", "Purchase_compositions.sql",
           // "TradeRoom.sql", "TradeSectionPoints.sql"};


    private List<String> tablesName;
    private final Executor executor;
    private final Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public DatabaseManager(Connection connection) {
        this.connection = connection;
        executor = new Executor(connection);
        tablesName = new LinkedList<>();
        tablesName.addAll(Arrays.asList(tableNamesArray));

    }

    public List<String> getExistingTables(){
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet set = connection.executeQuery("select table_name from user_tables");
            if (set != null) {
                while (set.next()) {
                    System.out.println("HERE");
                    String name = set.getString(1);
                    result.add(name);
                }
            }
        }
        catch(SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public void createDatabase() throws SQLException {
        createTables();
    }

    private void execute(List<String> queries, Optional<Integer> additionalCode) throws SQLException {
        for (String query: queries) {
            try {
                connection.executeQuery(query);
            } catch (SQLIntegrityConstraintViolationException ignored) {
            } catch (SQLException e) {
                if (!(e.getErrorCode() == 6550 /*|| e.getErrorCode() == additionalCode.ifPresent().get()*/)) {
                    e.printStackTrace();
                }
            } finally {
                System.out.println("Execute query: " + query.toString());
            }
        }
    }

    public void dropTables() throws SQLException {
        execute(loadTableDrops(), null);
        execute(loadSequencesDrops(), null);
    }

    private void createTables() throws SQLException {
        List<String> tablesCreation = new LinkedList<>();
        for(String tableName: tablesName) {
            tablesCreation.add(loadScriptFromFile("tablesCreation/" + tableName));
        }

        execute(loadTableDrops(), null);
        execute(loadSequencesDrops(), null);
        execute(tablesCreation, Optional.of(955));
        execute(loadSequences(), null);
        execute(loadAutoincrement(), null);
    }

    private String loadScriptFromFile(String relativePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(
                    "src/main/resources/" + relativePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> loadSequences() {
        List<String> sequences = new LinkedList<>();
        for(String name: tablesName) {
            sequences.add(loadScriptFromFile("sequences/" + name));
        }
        return sequences;
    }

    private List<String> loadAutoincrement() {
        List<String> autoIncrements = new LinkedList<>();
        for(String name: tablesName) {
            autoIncrements.add(loadScriptFromFile("triggers/" + name));
        }
        return autoIncrements;
    }

    private List<String> loadSequencesDrops() {
        List<String> autoIncrements = new LinkedList<>();
        for(String tableName: tablesName) {
            autoIncrements.add(loadScriptFromFile("dropSequences/" + tableName));
        }
        return autoIncrements;
    }

    private List<String> loadTableDrops() {
        LinkedList<String> autoIncrements = new LinkedList<>();
        for(String tableName: tablesName) {
            autoIncrements.addFirst(loadScriptFromFile("dropTables/" + tableName));
        }
        return autoIncrements;
    }

    public List<Map.Entry<String, List<String>>> getTable(String table) {
        return null;
    }
}
