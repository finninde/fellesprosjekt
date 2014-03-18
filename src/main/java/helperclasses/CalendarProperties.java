package helperclasses;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by kradalby on 06/03/14.
 */
public class CalendarProperties {

    // Database settings
    private String dbname;
    private String dbhost;
    private String dbport;
    private String dbuser;
    private String dbpass;

    //Email settings
    private String emailuser;
    private String emailpass;

    //Client settings
    private int srvport;
    private String srvhost;

    //Server settings
    private int listenport;
    private String listenaddress;


    public CalendarProperties(){


        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.prop"));

            this.dbname = properties.getProperty("dbname");
            this.dbhost = properties.getProperty("dbhost");
            this.dbport = properties.getProperty("dbport");
            this.dbuser = properties.getProperty("dbuser");
            this.dbpass = properties.getProperty("dbpass");

            this.emailuser = properties.getProperty("emailuser");
            this.emailpass = properties.getProperty("emailpass");

            this.srvport = Integer.parseInt(properties.getProperty("srvport"));
            this.srvhost = properties.getProperty("srvhost");

            this.listenport = Integer.parseInt(properties.getProperty("listenport"));
            this.listenaddress = properties.getProperty("listenaddress");



        } catch (IOException e) {
            Logger.getLogger(CalendarProperties.class.getName()).log(Level.SEVERE, "failed to load properties. ", e);
            //throw new IllegalStateException("failed to load properties. ");
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

    public String getEmailuser() {
        return emailuser;
    }

    public String getEmailpass() {
        return emailpass;
    }

    public int getSrvport() {
        return srvport;
    }

    public String getSrvhost() {
        return srvhost;
    }

    public int getListenport() {
        return listenport;
    }

    public String getListenaddress() {
        return listenaddress;
    }
}
