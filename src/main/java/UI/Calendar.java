package sample;

import UI.AppointmentButton;
import client.ClientConnection;
import helperclasses.Appointment;
import helperclasses.Status;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;


public class Calendar {
    
    private AppointmentButton[][] btn = new AppointmentButton[7][8];
    private DateTime startOfWeek ;
    private DateTime endOfWeek;
    private String username;
    private ClientConnection connection;
    private ArrayList<Appointment> appointmentsWhereUserIsOwner;
    private ArrayList<Appointment> appointmentsWhereUserIsParticipant;

    public Calendar(ArrayList<Appointment> appointmentsWhereUserIsOwner,
                    ArrayList<Appointment> appointmentsWhereUserIsParticipant,
                    DateTime startOfWeek,
                    ClientConnection connection,
                    String username) throws Exception{
        this.startOfWeek = startOfWeek;
        this.endOfWeek = startOfWeek.plusDays(7);
        this.username = username;
        this.connection = connection;
        this.appointmentsWhereUserIsOwner = appointmentsWhereUserIsOwner;
        this.appointmentsWhereUserIsParticipant = appointmentsWhereUserIsParticipant;

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(gridPane, 800, 600 );

        scene.getStylesheets().add(Calendar.class.getResource(("calendar.css")).toExternalForm());




        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn[i].length;j++){

                btn[i][j] = new AppointmentButton(i,j);
                btn[i][j].setPrefSize(100, 50);
                gridPane.add(btn[i][j], i, j);
            }
        }

        // make events highlighted

        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn.length;j++){

                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                            System.out.println(event.getSource());
                        if (((AppointmentButton)(event.getSource())).getId() == "important") {
                            //TODO:handle red event
                        } else if (((AppointmentButton)(event.getSource())).getId() == "accepted") {
                            //TODO:all is fine, nothing to do.
                        } else if (((AppointmentButton)(event.getSource())).getId() == "pending") {
                            //TODO:handle whatever yellow event is.
                        } else {
                            //TODO:make new appointment.
                        }
                    }
                });
            }
        }
        //show appointments in calendar
        updateView();
    }
    public void updateView() {
        for(Appointment appointment: this.appointmentsWhereUserIsOwner) {
            showAppointmentInCalendar(appointment);
        }
        for(Appointment appointment: appointmentsWhereUserIsParticipant) {
            showAppointmentInCalendar(appointment);
        }

    }

    public void changeDate(DateTime newStartDate) {
        startOfWeek = newStartDate;
        updateView();

    }

    public void showAppointmentInCalendar(Appointment appointment){
        DateTime startDate = appointment.getTimeFrame().getStartDate();
        if (startDate.isBefore(endOfWeek) && startDate.isAfter(startOfWeek)){
            Status status = this.connection.getStatusForAppointment(appointment.getId());
            if(status == Status.HIDE) {
                return;
            }
            int column = startDate.getDayOfWeek() - 1;
            int row = startDate.getHourOfDay() -8;

            btn[row][column].setText(appointment.getTitle());

            if (appointment.getOwner().equals(username) ){
                btn[row][column].setId("accepted");
                return;
            }

            if(status==Status.PENDING) {
                btn[row][column].setId("pending");
            } else if(status == Status.ACCEPTED) {
                btn[row][column].setId("accepted");
            }else if(status == Status.DECLINED) {
                btn[row][column].setId("important");
            }

        }

    }



}