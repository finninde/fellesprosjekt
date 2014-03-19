package database;

import helperclasses.Alarm;
import helperclasses.Appointment;
import helperclasses.User;

import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public interface UserService {

    public ArrayList<User> getUsers();
    public User getUser(String username);
    public ArrayList<Appointment> getAppointmentsWhereUserIsOwner(String username);
    public ArrayList<Appointment> getAppointmentsWhereUserIsParticipant(String userame);
//    public Alarm getAlarm(int id);
    public ArrayList<Alarm> getAllAlarmsForUser(User user);
    public void addUser(User user);
    public void addAlarm(Alarm alarm);

    public void updateAlarm(Alarm alarm);
    public void updateUser(User user);
}
