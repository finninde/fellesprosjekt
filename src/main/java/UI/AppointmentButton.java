package UI;

import helperclasses.Appointment;
import javafx.scene.control.Button;

/**
 * Created by finn on 19/03/14.
 */
public class AppointmentButton extends Button {
    private Appointment appointment = null;
    private int row;
    private int column;

    public AppointmentButton(int row, int column){
        this.row = row;
        this.column = column;

    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
