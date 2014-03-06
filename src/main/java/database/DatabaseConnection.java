package database;

import database.repository.DatabaseProperties;

import java.sql.*;

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
            DatabaseProperties dbp = new DatabaseProperties();
            String url = "jdbc:mysql://" + dbp.getDbhost() + ":" + dbp.getDbport() + "/" + dbp.getDbname();
            try {
                connectionInstance = DriverManager.getConnection(url, dbp.getDbuser(), dbp.getDbpass());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(url);
            }

        }
        return connectionInstance;
    }

    public static void main(String args[]) {
        DatabaseConnection instance = new DatabaseConnection();
        Connection con = instance.getConnectionInstance();
        try {
            System.out.println(con.getMetaData());
            String q = "show databases;";
            PreparedStatement statement = con.prepareStatement(q);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                System.out.println(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
