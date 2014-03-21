package UI;

import client.ClientConnection;
import helperclasses.Appointment;
import helperclasses.Status;
import helperclasses.TimeFrame;
import helperclasses.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by Wien on 17.03.14.
 */

public class CalendarScreen {
    private ChoiceBox<User> otherUsersChoiceBox;
    private Button newAppointmentButton;
    private Button logOutButton;
    private Button previousWeekButton;
    private Button nextWeekButton;
    private Button closeButton;
    private AppointmentButton[][] btn = new AppointmentButton[7][8];
    private DateTime startOfWeek ;
    private DateTime endOfWeek;
    private String username;
    private ArrayList<Appointment> appointmentsWhereUserIsOwner;
    private ArrayList<Appointment> appointmentsWhereUserIsParticipant;
    private ClientConnection connection;
    private GridPane calendarGrid;
    private int week;
    private User user;

    private Label hour;
    private ArrayList<Label> days;
    private Label otherUsersLabel;
    private ClientConnection clientConnection;

    private ArrayList<User> allUsers;

    public CalendarScreen(final Stage calendarStage, ArrayList<User> users, User u, final boolean owner, ClientConnection cC){
        user = u;
        clientConnection = cC;
        week = 0;
        allUsers = users;
        days = new ArrayList<Label>();
        calendarGrid = new GridPane();
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);

/*
        Appointment q = new Appointment("julekake");
        q.setTimeFrame(new TimeFrame(new DateTime().withTime(10,0,0,0), new DateTime().withTime(15,0,0,0)));
        q.setOwner(user);
        clientConnection.newAppointment(q);*/

        if (owner){
            newAppointmentButton = new Button("New Appointment");
            newAppointmentButton.setFont(Font.font("Helvetica-Light", 15));
            newAppointmentButton.setMaxSize(160,20);
            calendarGrid.add(newAppointmentButton, 0,0);
            newAppointmentButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    System.out.println("newAppointmentButton clicked");
                    new EditScreen(new Stage(), clientConnection);
                }
            });
            logOutButton = new Button("Logout");
            logOutButton.setFont(Font.font("Helvetica-Light", 15));
            logOutButton.setMaxSize(120,20);
            logOutButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    clientConnection.logout();
                    new LoginScreen(calendarStage);
                }
            });

            calendarGrid.add(logOutButton, 8,0);
        }
        else {
            closeButton = new Button("Close");
            closeButton.setMaxSize(120,20);
            closeButton.setFont(Font.font("Helvetica-Light", 15));
            closeButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    Stage stage = (Stage) closeButton.getScene().getWindow();
                    stage.close();
                }
            });
            calendarGrid.add(closeButton, 8,0);
        }

        previousWeekButton = new Button("Previous week");
        previousWeekButton.setFont(Font.font("Helvetica-Light", 11));
        previousWeekButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                week--;
                updateView();}});

        nextWeekButton = new Button("Next week");
        nextWeekButton.setFont(Font.font("Helvetica-Light", 11));
        nextWeekButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                week++;
                updateView();}  });

        previousWeekButton.setMaxSize(80, 20);
        nextWeekButton.setMaxSize(80, 20);
        GridPane weekButtons = new GridPane();


        weekButtons.add(previousWeekButton, 0,0);
        weekButtons.add(nextWeekButton, 1, 0);
        calendarGrid.add(weekButtons, 0, 1);

        otherUsersChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(users));
        otherUsersChoiceBox.setMaxSize(120, 20);
        otherUsersChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>(){
            @Override
            public void changed(ObservableValue observableValue, User o, User o2) {
                new CalendarScreen(new Stage(), allUsers, o2, false, clientConnection);
            }
        });
        otherUsersLabel = new Label();
        otherUsersLabel.setText("Other users:");
        otherUsersLabel.setFont(Font.font("Helvetica-Light", 11));

        calendarGrid.add(otherUsersLabel,7,1);
        calendarGrid.add(otherUsersChoiceBox,8,1);


        this.startOfWeek = new DateTime().withDayOfWeek(1);
        this.endOfWeek = startOfWeek.plusDays(7);
        this.username = user.getUsername();
        this.connection = clientConnection;
        this.appointmentsWhereUserIsOwner = clientConnection.getAppointmentsWhereUserIsOwner();
        this.appointmentsWhereUserIsParticipant = clientConnection.getAppointmentsWhereUserIsParticipant();


        for (Appointment a: appointmentsWhereUserIsOwner){
            System.out.println(a.getTimeFrame().getStartDate().toString("HH:mm:ss"));
            System.out.println(a.getTitle());
        }
        for (Appointment b: appointmentsWhereUserIsParticipant){
            System.out.println(b.getTimeFrame().getStartDate().toString("HH:mm:ss"));
            System.out.println(b.getTitle());
        }
        calendarGrid.getStylesheets().add(Calendar.class.getResource(("calendar.css")).toExternalForm());




        for (int i=0; i<7; i++){
            initDateLabels(week, i);
        }
        for (int i=0; i<btn[0].length; i++){
            DateTime dt = new DateTime().withHourOfDay(8+i);
            hour = new Label(dt.toString("HH:00"));
            calendarGrid.add(hour, 0, i+4);
        }

        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn[i].length;j++){

                btn[i][j] = new AppointmentButton(i,j);
                btn[i][j].setPrefSize(100, 50);
                calendarGrid.add(btn[i][j], i+1, j+4);
            }
        }

        // make events highlighted

        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn[i].length;j++){

                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        AppointmentButton a = (AppointmentButton) event.getSource();

                        System.out.println(event.getSource());
                        if (a.getId() == "important") {
                            new ViewScreen(new Stage(), a.getAppointment(), clientConnection);
                            //TODO:handle red event
                        } else if (a.getId() == "accepted" && a.getAppointment().getOwner().getUsername().equals(user.getUsername())){
                            new EditScreen(a.getAppointment(), clientConnection);
                            //TODO:all is fine, nothing to do.
                        } else if (a.getId() == "accepted"){
                            new ViewScreen(new Stage(), a.getAppointment(), clientConnection);
                        } else if (a.getId() == "pending") {
                            new ViewScreen(new Stage(), a.getAppointment(), clientConnection);
                            //TODO:handle whatever yellow event is.
                        } else {
                            new EditScreen(new Stage(), clientConnection);
                            //TODO:make new appointment.
                        }
                    }
                });
            }
        }

        String calendarTitle = user + "'s Calendar";
        calendarStage.setTitle(calendarTitle);
        calendarStage.setScene(new Scene(calendarGrid, 1024, 683));
        calendarStage.show();
        //show appointments in calendar
        updateView();
    }

    private void initDateLabels(int week, int i) {
        DateTime dt = new DateTime().withDayOfWeek(i + 1).plusWeeks(week);
        Label day = new Label(dt.toString("dd-MMMM-yyyy"));
        days.add(day);
        calendarGrid.add(day, i+1, 3);
    }

    public void updateView() {
        this.appointmentsWhereUserIsOwner = clientConnection.getAppointmentsWhereUserIsOwner();
        this.appointmentsWhereUserIsParticipant = clientConnection.getAppointmentsWhereUserIsParticipant();
        startOfWeek = new DateTime().withDayOfWeek(1).plusWeeks(week);
        endOfWeek = startOfWeek.plusDays(7);
        int i = 0;
        for (Label label: days){
            DateTime dt = new DateTime().withDayOfWeek(i + 1).plusWeeks(week);
            label.setText(dt.toString("dd-MMMM.yyyy"));
            i++;
        }
        for(int j=0; j<btn.length; j++){
            for(int k=0; k<btn[j].length; k++){
                if (btn[j][k].getAppointment() != null){
                    btn[j][k].setText("");
                    btn[j][k].setAppointment(null);
                    btn[j][k].setId(null);
                    System.out.println("resetting");
                }
            }
        }
        for(Appointment appointment: this.appointmentsWhereUserIsOwner) {
            showAppointmentInCalendar(appointment);
        }
        for(Appointment appointment: this.appointmentsWhereUserIsParticipant) {
            showAppointmentInCalendar(appointment);
        }
    }

    public void showAppointmentInCalendar(Appointment appointment){
        DateTime startDate = appointment.getTimeFrame().getStartDate();
        if (startDate.isBefore(endOfWeek.plusDays(1)) && startDate.isAfter(startOfWeek.minusDays(1))){
            Status status = this.connection.getStatusForAppointment(appointment.getId());
            if(status == Status.HIDE) {
                return;
            }
            int column = startDate.getDayOfWeek() - 1;
            int row = startDate.getHourOfDay()-8;
            System.out.println("row"+row);
            System.out.println("column"+column);
            if (column>btn.length || row>btn[column].length || column < 0 || row < 0){
                return;
            }
            btn[column][row].setText(appointment.getTitle());
            btn[column][row].setAppointment(appointment);
            if (appointment.getOwner().getUsername().equals(username) ){
                System.out.println("waolsdjiaoshd");
                System.out.println("wololo: " + appointment.getTimeFrame().getStartDate().toString("dd- HH:00"));
                btn[column][row].setId("accepted");
                return;
            }
            if(status==Status.PENDING) {
                btn[column][row].setId("pending");
            } else if(status == Status.ACCEPTED) {
                btn[column][row].setId("accepted");
            }else if(status == Status.DECLINED) {
                btn[column][row].setId("important");
            }

        }




    }

}
