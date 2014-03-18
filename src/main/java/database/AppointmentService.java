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
    public void addParticipant(User user, Appointment appointment);
    public void addParticipants(ArrayList<User> users, Appointment appointment);

    public Appointment getAppointment(int id);
    public TimeFrame getTimeFrame(int id);
    public ArrayList<Participant> getParticipants(int id);

    public String updateAppointment(Appointment appointment);
    public void updateTimeFrame(TimeFrame timeFrame);

    public void deleteParticipant(User user, Appointment appointment);
    public void deleteAppointment(Appointment appointment);


}
