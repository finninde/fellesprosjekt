package UI;

import client.ClientConnection;
import helperclasses.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


/**
 * Created by jonasandredalseth on 11.03.14.
 */

public class ViewScreen {
    private Appointment model;
    private ClientConnection clientConnection;

    private Stage viewStage;
    private EditScreen viewScreen;

    protected TextField fromDate;
    protected TextField toDate;
    protected TextArea attendingUsers;
    protected TextArea declinedUsers;
    protected TextArea waitingUsers;
    protected TextField fromTime;
    protected TextField toTime;
    protected TextField room;

    protected GridPane viewGrid;
    protected GridPane viewDateGrid;
    protected GridPane viewTimeGrid;
    protected GridPane buttonGrid;
    protected GridPane buttonGrid2;

    protected Label declined;
    protected Label accepted;
    protected Label waiting;

    protected Scene viewScene;




    //Try to use cancel button from EditScreen instead of making a new one
    private Button accept;
    private Button decline;
    private Button hide;

    private void fillUserStatusFields(ArrayList<Participant> participants) {
        String accepted = "";
        String declined = "";
        String waiting = "";
        for (int i = 0; i < participants.size(); i++){
            if (participants.get(i).getStatus() == Status.ACCEPTED){
                accepted += participants.get(i) + "\n";
            }
            else if (participants.get(i).getStatus() == Status.PENDING){
                waiting += participants.get(i) + "\n";
            }
            else if (participants.get(i).getStatus() == Status.DECLINED){
                declined += participants.get(i) + "\n";
            }
        }
        attendingUsers.setText(accepted);
        declinedUsers.setText(declined);
        waitingUsers.setText(waiting);
    }

    private String fromTimeFrameToFromDateString(){
        String fromDateString;
        int day = model.getTimeFrame().getStartDate().getDayOfMonth();
        int month = model.getTimeFrame().getStartDate().getMonthOfYear();
        int year = model.getTimeFrame().getStartDate().getYear();
        fromDateString = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
        return fromDateString;
    }

    private String fromTimeFrameToToDateString(){
        String toDateString;
        int day = model.getTimeFrame().getEndDate().getDayOfMonth();
        int month = model.getTimeFrame().getEndDate().getMonthOfYear();
        int year = model.getTimeFrame().getEndDate().getYear();
        toDateString = Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
        return toDateString;
    }

    private String fromTimeFrameToFromTimeString(){
        String fromTime;
        int hour = model.getTimeFrame().getStartDate().getHourOfDay();
        fromTime = Integer.toString(hour) + ":00";
        return fromTime;
    }

    private String fromTimeFrameToToTimeString(){
        String toTime;
        int hour = model.getTimeFrame().getEndDate().getHourOfDay();
        toTime = Integer.toString(hour) + ":00";
        return toTime;
    }

    private void setModel(Appointment model){
        this.model = model;
        model.addPropertyListener(this);
        viewScreen.eventName.setText(model.getTitle());
        fromDate.setText(fromTimeFrameToFromDateString());
        toDate.setText(fromTimeFrameToToDateString());
        fromTime.setText(fromTimeFrameToFromTimeString());
        toTime.setText(fromTimeFrameToToTimeString());
        viewScreen.locationText.setText(model.getLocation());
        if (model.getRoom() != null)
            room.setText(model.getRoom().getRoom());
        viewScreen.descriptionText.setText(model.getDescription());
        fillUserStatusFields(model.getParticipants());
    }

    /*private void setIrrelevantElementsFromEditScreenInvisble(){
        viewScreen.fromDate.setVisible(false);
        viewScreen.toDate.setVisible(false);
        viewScreen.fromTime.setVisible(false);
        viewScreen.toTime.setVisible(false);
        viewScreen.roomCombo.setVisible(false);
        viewScreen.editUsers.setVisible(false);
        viewScreen.editGroups.setVisible(false);
    }*/

    private void setObjectsNonEditable(){
        viewScreen.eventName.setEditable(false);
        viewScreen.descriptionText.setEditable(false);
        fromDate.setEditable(false);
        toDate.setEditable(false);
        fromTime.setEditable(false);
        toTime.setEditable(false);
        room.setEditable(false);
        attendingUsers.setEditable(false);
        waitingUsers.setEditable(false);
        declinedUsers.setEditable(false);
    }

    private void setWidth(int width){
        viewScreen.eventName.setMaxWidth(width + 77);
        viewScreen.locationText.setMaxWidth(width);
        viewScreen.alarmCombo.setMaxWidth(width);
        room.setMaxWidth(width);
        fromDate.setMaxWidth(width/2);
        toDate.setMaxWidth(width/2);
        fromTime.setMaxWidth(width/2);
        toTime.setMaxWidth(width/2);

    }

    private void fillSpaces(){ //fill spaces to make space between different categories of information in viewscreen
        viewGrid.add(new Label(),0,1);
        viewGrid.add(new Label(),0,6);
        viewGrid.add(new Label(),0,9);
        viewGrid.add(new Label(),0,12);
        viewGrid.add(new Label(),0,18);
    }

//TODO: fix alarmlogic if we get time
/*
    private void alarmLogic(){
        if (viewScreen.alarmCombo.getValue() == "No alarm")

        int alarmHour = model.getTimeFrame().getStartDate().getHourOfDay();
        int alarmDay = model.getTimeFrame().getStartDate().getDayOfMonth();
        int alarmMonth = model.getTimeFrame().getStartDate().getMonthOfYear();
        int alarmYear = model.getTimeFrame().getStartDate().getYear();
    }*/


    private void acceptButtonLogic(){
        clientConnection.updateParticipantStatus(model.getId(), Status.ACCEPTED);
        viewScreen.closeButtonLogic(viewStage);
    }

    private void declineButtonLogic(){
        clientConnection.updateParticipantStatus(model.getId(), Status.DECLINED);
        viewScreen.closeButtonLogic(viewStage);
    }

    private void hideButtonLogic(){
        clientConnection.updateParticipantStatus(model.getId(), Status.HIDE);
        viewScreen.closeButtonLogic(viewStage);
    }


    public ViewScreen(final Stage viewStage, Appointment model, ClientConnection cC) {
        clientConnection = cC;
        this.viewStage = viewStage;
        viewScreen = new EditScreen(viewStage, clientConnection);

        viewGrid = new GridPane();
        viewDateGrid = new GridPane();
        viewTimeGrid = new GridPane();
        buttonGrid = new GridPane();
        buttonGrid2 = new GridPane();

        viewGrid.setPadding(new Insets(15,15,15,25));


        viewGrid.add(viewScreen.eventName,0,0,2,1);
        viewGrid.add(viewScreen.date,0,3);
        fromDate = new TextField();
        toDate = new TextField();
        viewDateGrid.add(fromDate,0,0);
        viewDateGrid.add(toDate,1,0);
        viewGrid.add(viewDateGrid,1,3);

        viewGrid.add(viewScreen.time,0,4);
        viewGrid.add(viewTimeGrid,1,4);
        fromTime = new TextField();
        toTime = new TextField();
        viewTimeGrid.add(fromTime,0,0);
        viewTimeGrid.add(toTime,1,0);

        viewGrid.add(viewScreen.alarmLabel,0,5);
        viewGrid.add(viewScreen.alarmCombo,1,5);

        viewGrid.add(viewScreen.location,0,7);
        viewGrid.add(viewScreen.locationText,1,7);

        viewGrid.add(viewScreen.roomLabel,0,8);
        room = new TextField();
        viewGrid.add(room,1,8);


        int x = 172;
        int y = 100;

        viewGrid.add(viewScreen.descriptionLabel,0,11);
        viewScreen.descriptionText.setMaxSize(x,y);
        viewGrid.add(viewScreen.descriptionText,1,11);

        attendingUsers = new TextArea();
        waitingUsers = new TextArea();
        declinedUsers = new TextArea();
        waiting = new Label("Waiting:");
        declined = new Label("Declined:");
        accepted = new Label("Attending:");

        attendingUsers.setMaxSize(x,y);
        waitingUsers.setMaxSize(x,y);
        declinedUsers.setMaxSize(x,y);
        viewGrid.add(accepted, 0, 14);
        viewGrid.add(waiting,0,15);
        viewGrid.add(declined,0,16);
        viewGrid.add(attendingUsers,1,14);
        viewGrid.add(waitingUsers,1,15);
        viewGrid.add(declinedUsers,1,16);

        accept = new Button("Accept");
        accept.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {acceptButtonLogic();}});
        decline = new Button("Decline");
        decline.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {declineButtonLogic();}});
        hide = new Button("Hide");
        hide.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {hideButtonLogic();}});
        viewScreen.cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {viewScreen.closeButtonLogic(viewStage);}});
        viewGrid.add(buttonGrid,0,19,2,1);

        buttonGrid.add(accept,0,0);
        buttonGrid.add(hide,2,0);
        buttonGrid.add(decline,1,0);
        buttonGrid.add(viewScreen.cancelButton,3,0);


        setWidth(172);
        fillSpaces();


        setModel(model);
        viewScene = new Scene(viewGrid,305,670);
        viewStage.setScene(viewScene);

        //setIrrelevantElementsFromEditScreenInvisble();
        setObjectsNonEditable();

        viewStage.setTitle("View");
    }

/*
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String property = evt.getPropertyName();
        if (property == "title")
            viewScreen.eventName.setText((String) evt.getNewValue());
        else if (property == "timeframe"){
            fromDate.setText(fromTimeFrameToFromDateString());
            toDate.setText(fromTimeFrameToToDateString());
            fromTime.setText(fromTimeFrameToFromTimeString());
            toTime.setText(fromTimeFrameToToTimeString());
        }
        else if (property == "location")
            viewScreen.locationText.setText((String) evt.getNewValue());
        else if (property == "room"){
            MeetingRoom meetingRoom = (MeetingRoom) evt.getNewValue();
            room.setText(meetingRoom.getRoom());
        }
        else if (property == "participants")
            fillUserStatusFields((ArrayList<Participant>)evt.getNewValue());
    }*/
}
