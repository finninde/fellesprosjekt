package UI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.joda.time.DateTime;

/**
 * Created by Wien on 11.03.14.
 */
public class AlarmScreen extends Application{

    private Button okButton;

    private Text description;
    private Text date;
    private Text time;

    private Label descriptionLabel;
    private Label dateLabel;
    private Label timeLabel;

    public void start(Stage alarmStage){
        GridPane editGrid = new GridPane();
        editGrid.setVgap(20);
        editGrid.setHgap(20);

        description = new Text();
        /* DESCRIPTION SHOULD COME FROM APPOINTMENT*/
        descriptionLabel = new Label("Description");
        description.setText("troll");
        editGrid.add(descriptionLabel, 0,0);
        editGrid.add(description, 1,0);

        dateLabel = new Label("Date:");
        /*DATE SHOULD COME FROM APPOINTMENT*/
        date = new Text();
        date.setText("insert DateTime");

        editGrid.add(dateLabel,0,2);
        editGrid.add(date, 1,2);

        timeLabel = new Label("Time:");
        /*TIME SHOULD COME FROM APPOINTMENT*/
        time = new Text();
        time.setText("insert DateTime");

        editGrid.add(timeLabel, 0,3);
        editGrid.add(time, 1,3);
        

        okButton = new Button("OK");
        okButton.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                System.out.println("button clicked");
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


        alarmStage.setTitle("Alarm!");
        alarmStage.setScene(new Scene(editGrid, 300, 200));
        alarmStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
