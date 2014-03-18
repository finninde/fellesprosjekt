package database;

import helperclasses.Appointment;
import helperclasses.Participant;
import helperclasses.TimeFrame;
import helperclasses.User;

import java.util.ArrayList;

/**
 * Created by kradalby on 06/03/14.
 */
public interface AppointmentService {


    public void addAppointment(Appointment appointment);
    public void addTimeFrame(TimeFrame timeFrame);
    public void addParticipant(Participant participant, Appointment appointment);
    public void addParticipants(ArrayList<Participant> participants, Appointment appointment);

    public Appointment getAppointment(int id);
    public TimeFrame getTimeFrame(int id);
    public ArrayList<Participant> getParticipants(Appointment appointment);

    public String updateAppointment(Appointment appointment);
    public void updateTimeFrame(TimeFrame timeFrame);

    public void deleteParticipant(Participant participant, Appointment appointment);
    public void deleteAppointment(Appointment appointment);


}
