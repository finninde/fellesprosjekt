package UI;

import client.OwnerOfClientConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import org.joda.time.DateTime;

/**
 * Created by Wien on 12.03.14.
 */
public class MainTest extends Application implements OwnerOfClientConnection {
    @Override
    public void start(Stage stage) throws Exception {
        new LoginScreen(stage);
        Stage newStage = new Stage();
        new NotificationsScreen(newStage);
    }

    public static void main(String[]args){
        //launch(args);
        DateTime startDate = new DateTime();
        System.out.println(startDate.toDate());
    }

    @Override
    public void messageFromServer(String notification) {
        //TODO this is messages from the server. E.g. errors when trying to update the time for an appointment
        System.out.println(notification);

    }
}
