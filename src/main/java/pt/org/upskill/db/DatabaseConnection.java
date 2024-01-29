package pt.org.upskill.db;

/**
 * @author nunocastro
 */

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private OracleDataSource ods;
    private Connection conn;
    private SQLException error;

    public DatabaseConnection(String url, String username, String password) {
        try {
            ods = new OracleDataSource();
            ods.setURL(url);
            conn = ods.getConnection(username, password);
            //This is for debugging only...
            if (conn != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Failed to make the connection");
            }
        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
            System.err.format("SQL State: %s/n%s", e.getSQLState(), e.getMessage());
            registerError(e);
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void registerError(SQLException error) {
        this.error = error;
    }

    public SQLException getLastError() {
        SQLException lastError = this.error;
        //Clear after reading
        registerError(null);
        return lastError;
    }
}
