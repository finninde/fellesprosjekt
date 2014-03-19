package UI;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by jonasandredalseth on 13.03.14.
 */
public class MainUI extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        new EditScreen(stage);
        //new ViewScreen(stage);
    }

    public static void main (String[] args){
        launch(args);

    }
}
