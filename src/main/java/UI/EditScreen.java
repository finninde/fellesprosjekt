package UI;

import client.ClientConnection;
import helperclasses.Appointment;
import helperclasses.TimeFrame;
import helperclasses.User;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by jonasandredalseth on 11.03.14.
 */
public class EditScreen /*extends Application*/ {

    protected Scene scene;
    protected TextField eventName;
    protected TextField locationText;
    protected TextArea descriptionText;

    private ClientConnection clientConnection;

    private Appointment model;

    protected TextField fromDate;
    protected TextField toDate;

    private DateTime fromDateTime;
    private DateTime toDateTime;


    protected ComboBox fromTime;
    protected ComboBox toTime;
    protected ComboBox alarmCombo;
    protected ComboBox roomCombo;
    //protected ComboBox editUsers;
    //protected ComboBox editGroups;
    protected ListView userListView;
    protected ListView groupListView;

    protected Label date;
    protected Label time;
    protected Label alarmLabel;
    protected Label location;
    protected Label roomLabel;
    protected Label descriptionLabel;
    protected Label addRemoveUsers;
    protected Label addRemoveGroup;

    protected Button cancelButton;
    protected Button commitButton;
    protected Button invisibleButton;

    protected GridPane editGrid;
    protected GridPane dateGrid;
    protected GridPane timeGrid;
    protected GridPane cancelButtonGrid;


    private ObservableList<String> timeOptions;
    private ObservableList<String> alarmOptions;
    private ObservableList<String> userOptions; //Change to User instead of String when implemented
    private ObservableList<String> groupOptions; //Change to User instead of String when implemented
    private ObservableList<String> roomOptions; //Change to User instead of String when implemented

    private void setFont(Font font){
        date.setFont(font);
        time.setFont(font);
        alarmLabel.setFont(font);
        location.setFont(font);
        roomLabel.setFont(font);
        descriptionLabel.setFont(font);
        addRemoveUsers.setFont(font);
        addRemoveGroup.setFont(font);
    }

    protected void closeButtonLogic(Stage stage){
        stage.close();
    }
public void  makeTimeFrameFromTextFields(){
        int startYear = Integer.parseInt(fromDate.getText().substring(0, 2));
        int startMonth = Integer.parseInt(fromDate.getText().substring(3,5));
        int startDay = Integer.parseInt(fromDate.getText().substring(6,8));
        int startHour = Integer.parseInt(fromTime.getValue().toString().substring(0, 2));

        int endYear = Integer.parseInt(toDate.getText().substring(0,2));
        int endMonth = Integer.parseInt(toDate.getText().substring(3,5));
        int endDay = Integer.parseInt(toDate.getText().substring(6,10));
        int endHour = Integer.parseInt(toTime.getValue().toString().substring(0, 2));

        //Setter Appointment sin startdate lik startdaten
        model.getTimeFrame().setStartDate(new DateTime().withDate(startYear, startMonth, startDay).withHourOfDay(startHour));
        //Setter appointment sin enddate lik enddaten
        model.getTimeFrame().setEndDate(new DateTime().withDate(endYear, endMonth, endDay).withHourOfDay(endHour));
    }


    private void commitButtonLogic(Stage stage){
        //TODO make logic for saving the content in the editscreen
    /*    Appointment(this.eventName.getText());
        Appointment.setLocation(this.locationText.getText());
        Appointment.setDescription(this.descriptionText.getText());
        Appointment.changeTimeFrame(); //TODO fix logic
        Appointment.alertChanges(this.alarmCombo.getValue());
        Appointment.setRoom(roomCombo.getValue());
        Appointment.addUser(editUsers.getValue());
        Appointment.addGroup(editGroups.getValue()); */
        stage.close();
    }


    /*public void start (final Stage editStage){*/
    public EditScreen(final Stage editStage){


        timeOptions = FXCollections.observableArrayList(
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00"
        );

        alarmOptions = FXCollections.observableArrayList(
                "No alarm",
                "1 hour before",
                "2 hours before",
                "1 day before",
                "2 days before"
        );

        //TODO Fill these lists with data from the database
        userOptions = FXCollections.observableArrayList(
                "Jonas"

        );

        groupOptions = FXCollections.observableArrayList(
                "Group 6"
        );

        roomOptions = FXCollections.observableArrayList(
                "None",
                "Drivhuset"
        ); //TODO Fill these lists with data from the database

        editGrid = new GridPane();
        editGrid.setPadding(new Insets(15,15,15,15));
        editGrid.setVgap(10);
        editGrid.setHgap(10);

        dateGrid = new GridPane();
        dateGrid.setHgap(60);
        editGrid.add(dateGrid,1,2);

        timeGrid = new GridPane();
        timeGrid.setHgap(10);
        editGrid.add(timeGrid,1,3);

        cancelButtonGrid = new GridPane();
        cancelButtonGrid.setHgap(10);
        editGrid.add(cancelButtonGrid,1,18);

        eventName = new TextField();
        eventName.setPromptText("Name of event");
        eventName.setMinWidth(200);
        editGrid.add(eventName, 0, 0, 2, 1);

        date = new Label("Date:");
        fromDate = new TextField();
        toDate = new TextField();
        fromTime.setPromptText("dd.mm.yyyy");
        toTime.setPromptText("dd.mm.yyyy");

        editGrid.add(date,0,2);
        dateGrid.add(fromDate,0,0); //1,1
        dateGrid.add(toDate,1,0); //2,1

        time = new Label("Time:");
        fromTime = new ComboBox(timeOptions);
        fromTime.setValue(timeOptions.get(0));
        toTime = new ComboBox(timeOptions);
        toTime.setValue(timeOptions.get(0));
        editGrid.add(time, 0,3);
        timeGrid.add(fromTime, 0,0); //1,2
        timeGrid.add(toTime,1,0);   //2,2

        alarmLabel = new Label("Alarm:");
        alarmCombo = new ComboBox(alarmOptions);
        alarmCombo.setMinWidth(160);
        alarmCombo.setValue(alarmOptions.get(0));
        editGrid.add(alarmLabel,0,4);
        editGrid.add(alarmCombo,1,4);

        location = new Label("Location:");
        locationText = new TextField();
        editGrid.add(location,0,7);
        editGrid.add(locationText,1,7);

        roomLabel = new Label("Room:");
        roomCombo = new ComboBox(roomOptions);
        roomCombo.setMinWidth(160);
        roomCombo.setValue(roomOptions.get(0));
        editGrid.add(roomLabel,0,8);
        editGrid.add(roomCombo,1,8);

        descriptionLabel = new Label("Description:");
        descriptionText = new TextArea();
        descriptionText.setPrefSize(160,90);
        descriptionText.setWrapText(true);
        editGrid.add(descriptionLabel,0,11);
        editGrid.add(descriptionText,1,11);

        addRemoveUsers = new Label("Invite users:");
        userListView = new ListView(userOptions);
        userListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        editGrid.add(userListView,1,14);
        editGrid.add(addRemoveUsers, 0, 14);




        addRemoveGroup = new Label("Invite group:");
        groupListView = new ListView(groupOptions);
        groupListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        editGrid.add(groupListView, 0, 15);
        editGrid.add(groupListView, 1, 15);


        //Code for combobox

        /*editUsers = new ComboBox(userOptions);
        editUsers.setMinWidth(160);
        editUsers.setSelectionModel();
        editUsers.setValue(userOptions.get(0));
        editGrid.add(editUsers, 1, 14);*/

        /*editGroups = new ComboBox(groupOptions);
        editGroups.setMinWidth(160);
        editGroups.setValue(groupOptions.get(0));*/


        commitButton = new Button("Commit");
        commitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                commitButtonLogic(editStage);}
        });
        editGrid.add(commitButton, 0, 18);

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                closeButtonLogic(editStage);}
        });
        cancelButtonGrid.add(cancelButton,1,0);

        //Make invisible button to push the cancel button to the left
        invisibleButton = new Button("invisblebu");
        invisibleButton.setVisible(false);
        cancelButtonGrid.add(invisibleButton,0,0);



        setFont(Font.font("Helvetica-Ultra-Light", 13));
        scene = new Scene(editGrid,285,500);

        editStage.setTitle("Edit");
        editStage.setScene(scene);
        editStage.show();

        //TODO make setModel-method
        //TODO Choose groups and users
        //TODO Make alarm and notify the database

    }
}
