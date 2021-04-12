package utils;

import utils.tableManagers.TableManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DatabaseManager {
    private static final List<String> tableNames = new ArrayList<String>(){
        {
            add("TradeTypes.sql");
            add("TradePoints.sql");
            add("Goods.sql");
            add("TradeRoom.sql");
            add("Seller.sql");
            add("Accounting.sql");
            add("PurchaseCompositions.sql");
            add("Providers.sql");
            add("Deliveries.sql");
            add("DeliveriesGood.sql");
            add("Customers.sql");
            add("Sales.sql");
            add("TradeSectionPoint.sql");
    }
    };
    private static final Map<String, String> classForTableManagers = new HashMap<String, String>(){{
        put("GOODS","utils.tableManagers.GoodsTableManager");
        put("ACCOUNTING","utils.tableManagers.AccountingTableManager");
        put("CUSTOMERS","utils.tableManagers.CustomersTableManager");
        put("DELIVERIES","utils.tableManagers.DeliveriesTableManager");
        put("DELIVERIES_GOODS","utils.tableManagers.DeliveriesGoodsTableManager");
        put("PROVIDERS","utils.tableManagers.ProvidersTableManager");
        put("PURCHASE_COMPOSITIONS","utils.tableManagers.PurchaseCompositionsTableManager");
        put("SALES","utils.tableManagers.SalesTableManager");
        put("SELLERS","utils.tableManagers.SellersTableManager");
        put("TRADE_SECTION_POINT","utils.tableManagers.TradeSectionPointTableManager");
        put("TRADE_POINTS","utils.tableManagers.TradePointsTableManager");
        put("TRADE_TYPES","utils.tableManagers.TradeTypesTableManager");
        put("TRADE_ROOM","utils.tableManagers.TradeRoomTableManager");
    }};


    private final Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }


    private final Map<String, TableManager> tableManagers = new HashMap<>();

    public TableManager getTableManager(String tableName){
        if(tableManagers.containsKey(tableName)){
            return tableManagers.get(tableName);
        }

        TableManager tableManager = null;
        try {
            tableManager = (TableManager) Class.forName(classForTableManagers.get(tableName)).getConstructor(Connection.class).newInstance(connection);
            tableManagers.put(tableName, tableManager);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return tableManager;
    }

    public List<String> getExistingTables(){
        ArrayList<String> result = new ArrayList<>();
        try {
            ResultSet set = connection.executeQuery("select table_name from user_tables");
            if (set != null) {
                while (set.next()) {
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

    public void createDatabase(){
        clearDatabase();

        createTables();
        createSequences();
        createAutoincrement();
    }

    public void clearDatabase(){
        dropTables();
        dropSequences();
    }

    private void dropTables(){
        List<String> sub = new ArrayList<>(loadTableDrops());
        Collections.reverse(sub);
        for (String query : sub) {
            try {
                System.out.println(query);
                connection.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void createTables(){
        for (String query : loadCreationTables()) {
            try {
                System.out.println(query);
                connection.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void dropSequences(){
        List<String> sub = new ArrayList<>(loadSequencesDrops());
        Collections.reverse(sub);
        for (String query : sub) {
            try {
                System.out.println(query);
                connection.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void createAutoincrement(){
        for (String query : loadAutoincrement()) {
            try {
                System.out.println(query);
                connection.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void createSequences(){
        for (String query : loadSequences()) {
            try {
                System.out.println(query);
                connection.execute(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private List<String> loadCreationTables(){
        List<String> sequences = new ArrayList<>();
        for(String name: tableNames) {
            sequences.add(loadScriptFromFile("tablesCreation/" + name));
        }
        return sequences;
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
        List<String> sequences = new ArrayList<>();
        for(String name: tableNames) {
            sequences.add(loadScriptFromFile("sequences/" + name));
        }
        return sequences;
    }

    private List<String> loadAutoincrement() {
        List<String> autoIncrements = new ArrayList<>();
        for(String name: tableNames) {
            autoIncrements.add(loadScriptFromFile("triggers/" + name));
        }
        return autoIncrements;
    }

    private List<String> loadSequencesDrops() {
        List<String> sequencesDrops = new ArrayList<>();
        for(String tableName: tableNames) {
            sequencesDrops.add(loadScriptFromFile("dropSequences/" + tableName));
        }
        return sequencesDrops;
    }

    private List<String> loadTableDrops() {
        List<String> tableDrops = new ArrayList<>();
        for(String tableName: tableNames) {
            tableDrops.add(loadScriptFromFile("dropTables/" + tableName));
        }
        return tableDrops;
    }

    public void initTestValues() {
        try {
            connection.execute(loadScriptFromFile("testValues"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
