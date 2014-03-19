package UI;

import helperclasses.Appointment;
import helperclasses.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Created by Wien on 17.03.14.
 */

public class NotBeingUsedCalendarScreen {
    private Button notificationsButton;
    private JComboBox<User> otherUsersComboBox;
    private Button newAppointmentButton;
    private Button logOutButton;
    private Button previousWeekButton;
    private Button nextWeekButton;

    //private Label newAppointmentLabel; //TODO probably don't need this
    private Label notificationsLabel;
    private Label otherUsersLabel;



    public NotBeingUsedCalendarScreen(Stage calendarStage/*, User user*/){
        GridPane calendarGrid = new GridPane();
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);

        newAppointmentButton = new Button("New Appointment");
        calendarGrid.add(newAppointmentButton, 0,0);
        newAppointmentButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                System.out.println("newAppointmentButton clicked");
                new EditScreen(new Stage());
            }
        });

        previousWeekButton = new Button();

        String calendarTitle = /*user + */"'s Calendar"; //TODO remove quotations when connected with database
        calendarStage.setTitle(calendarTitle);
        calendarStage.setScene(new Scene(calendarGrid, 1024, 683));
        calendarStage.show();


    }
}
