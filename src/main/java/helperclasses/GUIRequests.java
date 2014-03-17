package helperclasses;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Wien on 17.03.14.
 */
public interface GUIRequests {
    public ArrayList<User> getArrayListOfUsers();
    public ArrayList<Group> getArrayListOfGroups();
    public ArrayList<Participant> getParticipantsOfAppointment(int id);
    public Appointment getAppointment();
    public Alarm getAlarm();
    public void updateAppointment(Appointment appointment);
    public ArrayList<Appointment> getUsersAppointments();

}
