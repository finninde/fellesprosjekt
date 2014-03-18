package UI;

import javafx.application.Application;
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


/**
 * Created by jonasandredalseth on 11.03.14.
 */
public class EditScreen /*extends Application*/ {

    protected Scene scene;

    protected TextField eventName;
    protected TextField locationText;
    protected TextArea descriptionText;

    protected SimpleCalendar fromDate;
    protected SimpleCalendar toDate;

    protected ComboBox fromTime;
    protected ComboBox toTime;
    protected ComboBox alarmCombo;
    protected ComboBox roomCombo;
    protected ComboBox editUsers;
    protected ComboBox editGroups;

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

    private void closeButtonLogic(Stage stage){
        stage.close();
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
                "00:00", "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00", "23:00"
        );

        alarmOptions = FXCollections.observableArrayList(
                "1 hour before",
                "2 hours before",
                "1 day before",
                "2 days before",
                "1 week before"
        );

        userOptions = FXCollections.observableArrayList(
                "Jonas"

        );

        groupOptions = FXCollections.observableArrayList(
                "Group 6"
        );

        roomOptions = FXCollections.observableArrayList(
                "None",
                "Drivhuset"
        );

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
        fromDate = new SimpleCalendar();
        toDate = new SimpleCalendar();
        //fromDate.getStylesheets().add("simple_calendar.css");


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
        editUsers = new ComboBox(userOptions);
        editUsers.setMinWidth(160);
        editUsers.setValue(userOptions.get(0));
        editGrid.add(addRemoveUsers, 0, 14);
        editGrid.add(editUsers, 1, 14);

        addRemoveGroup = new Label("Invite group:");
        editGroups = new ComboBox(groupOptions);
        editGroups.setMinWidth(160);
        editGroups.setValue(groupOptions.get(0));
        editGrid.add(addRemoveGroup, 0, 15);
        editGrid.add(editGroups, 1, 15);

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


    }

    /*public static void main(String[] args){
       launch(args);
    }*/

}
