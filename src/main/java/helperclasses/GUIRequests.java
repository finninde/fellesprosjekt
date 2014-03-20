package helperclasses;

import java.util.ArrayList;

/**
 * Created by Wien on 17.03.14.
 */
public interface GUIRequests {
    public ArrayList<User> getUsers();
    public ArrayList<Group> getGroups();
    public ArrayList<Participant> getParticipantsOfAppointment(int id);
    public void updateAppointment(Appointment appointment);
    public User getLoggedInUser();
    public void updateParticipantStatus(int appointmentID, Status status);
    public ArrayList<Appointment> getAppointmentsWhereUserIsOwner();
    public ArrayList<Appointment> getAppointmentsWhereUserIsParticipant();
    public boolean login(String username, String password);
	public Status getStatusForAppointment(int ID);
    public void newAppointment(Appointment appointment);
}
