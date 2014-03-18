package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


/**
 * Created by jonasandredalseth on 10.03.14.
 */
public class LogInErrorScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Log In Error");
        Button okButton = new Button();
        okButton.setText("OK");




        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }



}
