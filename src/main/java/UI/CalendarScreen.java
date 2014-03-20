package UI;

import client.ClientConnection;
import helperclasses.Appointment;
import helperclasses.Status;
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

    private Label hour;
    private ArrayList<Label> days;
    private Label otherUsersLabel;

    private ArrayList<User> allUsers;

    public CalendarScreen(final Stage calendarStage, ArrayList<User> users, User user, final boolean owner/*, final ClientConnection clientConnection*/){ //TODO
        week = 0;
        allUsers = users;
        days = new ArrayList<Label>();
        calendarGrid = new GridPane();
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);

        if (owner){
            newAppointmentButton = new Button("New Appointment");
            newAppointmentButton.setFont(Font.font("Helvetica-Light", 15));
            newAppointmentButton.setMaxSize(160,20);
            calendarGrid.add(newAppointmentButton, 0,0);
            newAppointmentButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    System.out.println("newAppointmentButton clicked");
                    new EditScreen(new Stage());
                }
            });
            logOutButton = new Button("Logout");
            logOutButton.setFont(Font.font("Helvetica-Light", 15));
            logOutButton.setMaxSize(120,20);
            logOutButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    //clientConnection.logout();    //TODO
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
                new CalendarScreen(new Stage(), allUsers, o2, false/*, clientConnection*/); //TODO
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
        //this.connection = clientConnection;   //TODO
        //this.appointmentsWhereUserIsOwner = clientConnection.getAppointmentsWhereUserIsOwner();   //TODO
        //this.appointmentsWhereUserIsParticipant = clientConnection.getAppointmentsWhereUserIsParticipant();   //TODO

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
        startOfWeek = new DateTime().withDayOfWeek(1).plusWeeks(week);
        endOfWeek = startOfWeek.plusDays(7);
        int i = 0;
        for (Label label: days){
            DateTime dt = new DateTime().withDayOfWeek(i + 1).plusWeeks(week);
            label.setText(dt.toString("dd-MMMM.yyyy"));
            i++;
        }
        for(Appointment appointment: this.appointmentsWhereUserIsOwner) {   //TODO blank out calendar
            showAppointmentInCalendar(appointment);
        }
        for(Appointment appointment: appointmentsWhereUserIsParticipant) {
            showAppointmentInCalendar(appointment);
        }
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

            if (appointment.getOwner().getName().equals(username) ){
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
