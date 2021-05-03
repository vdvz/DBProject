package utils;

import database_managers.table_managers.TableManager;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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
            add("DeliveriesGoods.sql");
            add("Customers.sql");
            add("Sales.sql");
            add("TradeSectionPoint.sql");
    }
    };
    private static final Map<String, String> classForTableManagers = new HashMap<String, String>(){{
        put("GOODS","database_managers.table_managers.GoodsTableManager");
        put("ACCOUNTING","database_managers.table_managers.AccountingTableManager");
        put("CUSTOMERS","database_managers.table_managers.CustomersTableManager");
        put("DELIVERIES","database_managers.table_managers.DeliveriesTableManager");
        put("DELIVERIES_GOODS","database_managers.table_managers.DeliveriesGoodsTableManager");
        put("PROVIDERS","database_managers.table_managers.ProvidersTableManager");
        put("PURCHASE_COMPOSITIONS","database_managers.table_managers.PurchaseCompositionsTableManager");
        put("SALES","database_managers.table_managers.SalesTableManager");
        put("SELLERS","database_managers.table_managers.SellersTableManager");
        put("TRADE_SECTION_POINT","database_managers.table_managers.TradeSectionPointTableManager");
        put("TRADE_POINTS","database_managers.table_managers.TradePointsTableManager");
        put("TRADE_TYPES","database_managers.table_managers.TradeTypesTableManager");
        put("TRADE_ROOM","database_managers.table_managers.TradeRoomTableManager");
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

    public Connection getConnection(){
        return connection;
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
            sequences.add(loadScriptFromFile("creation/tables/" + name));
        }
        return sequences;
    }

    private String loadScriptFromFile(String relativePath) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(DatabaseManager.class.getResourceAsStream("/" + relativePath)));
        return reader.lines().collect(Collectors.joining());
    }

    private List<String> loadSequences() {
        List<String> sequences = new ArrayList<>();
        for(String name: tableNames) {
            sequences.add(loadScriptFromFile("creation/sequences/" + name));
        }
        return sequences;
    }

    private List<String> loadAutoincrement() {
        List<String> autoIncrements = new ArrayList<>();
        for(String name: tableNames) {
            autoIncrements.add(loadScriptFromFile("creation/triggers/" + name));
        }
        return autoIncrements;
    }

    private List<String> loadSequencesDrops() {
        List<String> sequencesDrops = new ArrayList<>();
        for(String tableName: tableNames) {
            sequencesDrops.add(loadScriptFromFile("drop/sequences/" + tableName));
        }
        return sequencesDrops;
    }

    private List<String> loadTableDrops() {
        List<String> tableDrops = new ArrayList<>();
        for(String tableName: tableNames) {
            tableDrops.add(loadScriptFromFile("drop/tables/" + tableName));
        }
        return tableDrops;
    }

    public void initTestValues() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(DatabaseManager.class.getResourceAsStream("/testData/TestValues.sql"), StandardCharsets.UTF_8));
            String line = reader.readLine();
            while(line!=null){
                if(line.trim().length()==0) continue;
                System.out.println(line);
                connection.executeUpdate(line);
                line = reader.readLine();
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
