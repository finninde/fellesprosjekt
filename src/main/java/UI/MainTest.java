package UI;

import helperclasses.Appointment;
import helperclasses.TimeFrame;
import javafx.application.Application;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Created by Wien on 12.03.14.
 */
public class MainTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new LoginScreen(stage);
        Appointment a = new Appointment("Supertitle!!!");
        a.setTimeFrame(new TimeFrame(new DateTime(),(new DateTime())));
        a.setLocation("not here but there");
        a.setDescription("julekakesukkerlaketroikabolleballe");
        new AlarmScreen(a);
    }

    public static void main(String[]args){
        launch(args);
    }
}
