package database;

import helperclasses.Appointment;
import helperclasses.User;

import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public interface UserService {

    public ArrayList<User> getUsers();
    public User getUser(String username);
    public ArrayList<Appointment> getAppointmentsWhereUserIsOwner(User user);
    public ArrayList<Appointment> getAppointmentsWhereUserIsParticipant(User user);

    public void addUser(User user);

}
