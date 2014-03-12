package calendar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.Scene;

import java.net.CookieManager;

/**
 * Created by jonasandredalseth on 11.03.14.
 */
public class EditScreen extends Application {

    public void start (Stage editStage){

        ObservableList<String> timeOptions = FXCollections.observableArrayList(
                "00:00",
                "01:00",
                "02:00",
                "03:00",
                "04:00",
                "05:00",
                "06:00",
                "07:00",
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
                "21:00",
                "22:00",
                "23:00"
        );

        GridPane editGrid = new GridPane();
        editGrid.setVgap(20);
        editGrid.setHgap(20);


        TextField eventName = new TextField("Name of event");
        editGrid.add(eventName, 0, 0);

        Label date = new Label("Date");
        SimpleCalendar fromDate = new SimpleCalendar();
        SimpleCalendar toDate = new SimpleCalendar();
        editGrid.add(date,0,1);
        editGrid.add(fromDate,1,1);
        editGrid.add(toDate,2,1);

        Label time = new Label("Time");
        ComboBox fromTime = new ComboBox();
        ComboBox toTime = new ComboBox();
        editGrid.add(time, 0,2);
        editGrid.add(fromTime, 1,2);
        editGrid.add(toTime,2,2);

        Label location = new Label("Location");
        TextField locationText = new TextField();
        editGrid.add(location,0,3);
        editGrid.add(locationText,1,3);

        Label roomLabel = new Label("Room");
        ComboBox roomCombo = new ComboBox();
        editGrid.add(roomLabel,0,4);
        editGrid.add(roomCombo,1,4);

        Label alarmLabel = new Label("Alarm");
        ComboBox alarmCombo = new ComboBox();
        editGrid.add(alarmLabel,0,5);
        editGrid.add(alarmCombo,1,5);

        Label descriptionLabel = new Label("Description");
        TextField descriptionText = new TextField();
        editGrid.add(descriptionLabel,0,6);
        editGrid.add(descriptionText,1,6);

        Label addRemoveUsers = new Label("Add/remove users");
        ComboBox editUsers = new ComboBox();
        editGrid.add(addRemoveUsers, 0, 7);
        editGrid.add(editUsers, 1, 7);

        Label addRemoveGroup = new Label("Add/remove group");
        ComboBox editGroups = new ComboBox();
        editGrid.add(addRemoveGroup, 0, 8);
        editGrid.add(editGroups, 1, 8);

        Button commitButton = new Button("Commit");
        Button cancelButton = new Button("Cancel");
        editGrid.add(commitButton,0,9);
        editGrid.add(cancelButton,1,9);

        editStage.setTitle("Edit");
        editStage.setScene(new Scene(editGrid, 300, 700));
        editStage.show();

        //
    }

    public static void main(String[] args){
       launch(args);
    }

}
