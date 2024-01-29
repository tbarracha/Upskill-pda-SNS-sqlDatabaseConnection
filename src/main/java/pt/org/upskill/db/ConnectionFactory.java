package pt.org.upskill.db;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nunocastro
 */
public class ConnectionFactory {
    //This is the size of the connection pool.
    private final Integer connectionPoolCount = 1;
    //
    private Integer connectionPoolRequest = 0;
    private static ConnectionFactory cf;
    private final List<DatabaseConnection> dbConnectionList = new ArrayList<>();
/*
    public static ConnectionFactory getConnectionFactory() {
        if (cf == null) {
            cf = new ConnectionFactory();
        }
        return cf;
    }
*/
    public DatabaseConnection getDatabaseConnection() {
        DatabaseConnection c;
        if (++connectionPoolRequest > connectionPoolCount) {
            connectionPoolRequest = 1;
        }
        if (connectionPoolRequest > dbConnectionList.size()) {
            c = new DatabaseConnection(
                    "jdbc:oracle:thin:@upskill.dnsfor.me:1521/freepdb1",
                    "upskill05",
                    "Barracha91");
            dbConnectionList.add(c);
        }
        else {
            c = dbConnectionList.get(connectionPoolRequest-1);
        }
        return c;
    }
}
