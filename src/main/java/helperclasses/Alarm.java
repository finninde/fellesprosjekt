package helperclasses;

import org.joda.time.DateTime;

/**
 * Created by kradalby on 05/03/14.
 */
public class Alarm {

    private int id;
    private DateTime executeAlarm;
    private User user;
    private Appointment appointment;

    public Alarm() {
    }

    public boolean executeAlarm() throws NotYetImplementedException {
        throw new NotYetImplementedException();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getExecuteAlarm() {
        return executeAlarm;
    }

    public void setExecuteAlarm(DateTime executeAlarm) {
        this.executeAlarm = executeAlarm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
