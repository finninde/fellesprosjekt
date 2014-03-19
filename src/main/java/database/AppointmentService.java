package database;

import helperclasses.*;

import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public interface AppointmentService {


    public void addAppointment(Appointment appointment);
    public void addTimeFrame(TimeFrame timeFrame);
    public void addParticipant(Participant participant, Appointment appointment);
    public void addParticipants(ArrayList<Participant> participants, Appointment appointment);
    public void addAlarm(Alarm alarm);

    public Appointment getAppointment(int id);
    public TimeFrame getTimeFrame(int id);
    public ArrayList<Participant> getParticipants(int id);
    public Alarm getAlarm(String username, int appointmentID);

    public String updateAppointment(Appointment appointment);
    public void updateTimeFrame(TimeFrame timeFrame);
    public void updateAlarm(Alarm alarm);

    public void deleteParticipant(Participant participant, Appointment appointment);
    public void deleteAppointment(Appointment appointment);
    public void deleteAlarm(Alarm alarm);

    public void updateParticipantStatus(int AppointmentID, Status newStatus, String username);

}
