package helperclasses;

import java.util.ArrayList;

/**
 * Created by kradalby on 05/03/14.
 */
public class User {


    private String password;
    private String name;
    private String email;
    private ArrayList<Alarm> alarms;

    public User(String username) {
        this.username = username;

    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
    }

    @Override
    public String toString(){
        return this.username;
    }
}
