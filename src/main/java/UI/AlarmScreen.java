package UI;

import helperclasses.Appointment;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Wien on 11.03.14.
 */
public class AlarmScreen {

    private Button okButton;

    private Text description;
    private Text date;
    private Text time;
    private Text location;

    private Label descriptionLabel;
    private Label dateLabel;
    private Label timeLabel;
    private Label locationLabel;

    public AlarmScreen(Appointment appointment){
        Stage alarmStage = new Stage();
        GridPane editGrid = new GridPane();
        editGrid.setVgap(20);
        editGrid.setHgap(20);

        description = new Text();
        descriptionLabel = new Label("Description");
        description.setText(appointment.getDescription());

        editGrid.add(descriptionLabel, 0, 0);
        editGrid.add(description, 1,0);

        dateLabel = new Label("Date:");
        date = new Text();
        String ddmm =   String.valueOf(appointment.getTimeFrame().getStartDate().getDayOfMonth())+"/"+
                        String.valueOf(appointment.getTimeFrame().getStartDate().getMonthOfYear())+"-"+
                        String.valueOf(appointment.getTimeFrame().getStartDate().getYear());
        date.setText(ddmm);

        editGrid.add(dateLabel, 0, 2);
        editGrid.add(date, 1,2);

        timeLabel = new Label("Time:");
        time = new Text();
        String hour =   String.valueOf(appointment.getTimeFrame().getStartDate().getHourOfDay())+":"+
                        String.valueOf(appointment.getTimeFrame().getStartDate().getMinuteOfHour());
        time.setText(hour);

        editGrid.add(timeLabel, 0, 3);
        editGrid.add(time, 1,3);

        locationLabel = new Label("Location:");
        location = new Text();
        location.setText(appointment.getLocation());

        editGrid.add(locationLabel,0,1);
        editGrid.add(location,1,1);

        okButton = new Button("OK");
        okButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                System.out.println("okButton clicked");
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }
        });
        okButton.setMinSize(25,25);
        GridPane okButtonPane = new GridPane();
        okButtonPane.setVgap(25);
        okButtonPane.setHgap(50);
        okButtonPane.add(okButton, 1,0);
        editGrid.add(okButtonPane, 1,5);

        setFont(Font.font("Helvetica-Ultra-Light", 13));

        alarmStage.setTitle("Alarm! Appointment: "+appointment.getTitle());
        alarmStage.setScene(new Scene(editGrid, 300, 200));
        alarmStage.show();
    }

    private void setFont(Font font){
        date.setFont(font);
        time.setFont(font);
        timeLabel.setFont(font);
        description.setFont(font);
        okButton.setFont(font);
        descriptionLabel.setFont(font);
    }
}
