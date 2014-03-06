package database.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by kradalby on 06/03/14.
 */
public class DatabaseProperties {

    private String dbname;
    private String dbhost;
    private String dbport;
    private String dbuser;
    private String dbpass;

    public DatabaseProperties(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.prop"));

            this.dbname = properties.getProperty("dbname");
            this.dbhost = properties.getProperty("dbhost");
            this.dbport = properties.getProperty("dbport");
            this.dbuser = properties.getProperty("dbuser");
            this.dbpass = properties.getProperty("dbpass");

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("failed to load properties. ");
        }


    }

    public String getDbname() {
        return dbname;
    }

    public String getDbhost() {
        return dbhost;
    }

    public String getDbport() {
        return dbport;
    }

    public String getDbuser() {
        return dbuser;
    }

    public String getDbpass() {
        return dbpass;
    }
}
