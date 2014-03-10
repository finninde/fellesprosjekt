package database;

import helperclasses.Appointment;
import helperclasses.TimeFrame;

/**
 * Created by kradalby on 06/03/14.
 */
public interface AppointmentService {

    public void addAppointment(Appointment appointment);
    public void addTimeFrame(TimeFrame timeFrame);

    public Appointment getAppointment(int id);
    public TimeFrame getTimeFrame(int id);
}
