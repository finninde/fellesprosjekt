package UI;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Wien on 12.03.14.
 */
public class MainTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new LoginScreen(stage);
        Stage newStage = new Stage();
        new NotificationsScreen(newStage);
    }

    public static void main(String[]args){
        launch(args);
    }
}
