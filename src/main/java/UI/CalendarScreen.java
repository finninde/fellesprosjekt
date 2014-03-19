package UI;

import helperclasses.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

    private Label otherUsersLabel;

    private ArrayList<User> allUsers;

    public CalendarScreen(Stage calendarStage, ArrayList<User> users, User user, boolean owner){

        allUsers = users;

        GridPane calendarGrid = new GridPane();
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

            calendarGrid.add(logOutButton, 70,0);
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
            calendarGrid.add(closeButton, 70,0);
        }

        previousWeekButton = new Button("Previous week");
        previousWeekButton.setFont(Font.font("Helvetica-Light", 11));

        nextWeekButton = new Button("Next week");
        nextWeekButton.setFont(Font.font("Helvetica-Light", 11));

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
                new CalendarScreen(new Stage(), allUsers, o2, false);
            }
        });
        otherUsersLabel = new Label();
        otherUsersLabel.setText("Other users:");
        otherUsersLabel.setFont(Font.font("Helvetica-Light", 11));

        calendarGrid.add(otherUsersLabel,69,1);
        calendarGrid.add(otherUsersChoiceBox,70,1);

        String calendarTitle = user + "'s Calendar";
        calendarStage.setTitle(calendarTitle);
        calendarStage.setScene(new Scene(calendarGrid, 1024, 683));
        calendarStage.show();


    }
}
