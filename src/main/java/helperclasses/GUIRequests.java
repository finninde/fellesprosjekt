package helperclasses;

import java.util.ArrayList;

/**
 * Created by Wien on 17.03.14.
 */
public interface GUIRequests {
    public ArrayList<User> getUsers();
    public ArrayList<Group> getGroups();
    public ArrayList<Participant> getParticipantsOfAppointment(int id);
    public Appointment getAppointment();
    public Alarm getAlarm();
    public void updateAppointment(Appointment appointment);
    public ArrayList<Appointment> getAppointmentsWhereUserIsOwner();
    public ArrayList<Appointment> getAppointmentsWhereUserIsParticipant();
    public boolean login(String username, String password);
}
