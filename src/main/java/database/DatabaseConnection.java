package database;

import helperclasses.CalendarProperties;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kradalby on 06/03/14.
 */
public class DatabaseConnection {

    private static Connection connectionInstance;

    public DatabaseConnection() {
    }

    public static void setConnectionInstance(Connection connectionInstance) {
        DatabaseConnection.connectionInstance = connectionInstance;
    }

    public static Connection getConnectionInstance() {
        if (connectionInstance == null) createConnectionInstance();
        return connectionInstance;
    }

    private static Connection createConnectionInstance() {
        if (connectionInstance == null) {
            CalendarProperties dbp = new CalendarProperties();
            String url = "jdbc:mysql://" + dbp.getDbhost() + ":" + dbp.getDbport() + "/" + dbp.getDbname();
            try {
                connectionInstance = DriverManager.getConnection(url, dbp.getDbuser(), dbp.getDbpass());
            } catch (SQLException e) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
            }

        }
        return connectionInstance;
    }

}
