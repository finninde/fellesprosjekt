package UI;

import client.ClientConnection;
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
        //ClientConnection clientConnection = ClientConnection.getInstance();
        new LoginScreen(stage/*, clientConnection*/);
        Appointment a = new Appointment("Supertitle!!!");
        //DateTime dt = new DateTime().withDate(year, month, day).withHourOfDay(hour);
        a.setTimeFrame(new TimeFrame(new DateTime(),new DateTime()));
        //System.out.println(dt);
        //dt.withHourOfDay(20);
        //System.out.println(dt);

        a.setLocation("not here but there");
        a.setDescription("julekakesukkerlaketroikabolleballe");
        new AlarmScreen(a);
    }

    public static void main(String[]args){
        launch(args);

    }
}
