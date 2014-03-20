package UI;
import client.ClientConnection;
import helperclasses.Appointment;
import helperclasses.TimeFrame;
import helperclasses.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import org.joda.time.DateTime;

import java.util.ArrayList;


public class LoginScreen {
    private TextField userTextField;
    private PasswordField pwBox;

    public LoginScreen(final Stage primaryStage) { //TODO
    primaryStage.setTitle("LoginScreen");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    Scene scene = new Scene(grid, 1024, 683 );
    primaryStage.setScene(scene);
    scene.getStylesheets().add(LoginScreen.class.getResource(("Login.css")).toExternalForm());


    Text scenetitle = new Text("Welcome");
    scenetitle.setId("welcome-text");
    grid.add(scenetitle, 0, 0, 2, 1);

///working here!
    Label userName = new Label("User Name:");
    userName.setFont(Font.font("Helvetica-Light", 15));
    grid.add(userName, 0, 1);

    userTextField = new TextField();
    grid.add(userTextField, 1, 1);

    Label pw = new Label("Password:");
    pw.setFont(Font.font("Helvetica-Light", 15));

    grid.add(pw, 0, 2);


    pwBox = new PasswordField();
    grid.add(pwBox, 1, 2);

    Button btn = new Button("Sign in");
    HBox hbBtn = new HBox(10);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtn.getChildren().add(btn);
    grid.add(hbBtn, 1, 4);

    final Text actiontarget = new Text();
    actiontarget.setId("actiontarget");
    grid.add(actiontarget, 1, 6);

    btn.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent e) {
            ClientConnection clientConnection;
            if (userTextField.getText() != null || pwBox.getText() != null){
                clientConnection = ClientConnection.getInstance();
                clientConnection.start();
                try{
                    boolean test = clientConnection.login(userTextField.getText(),pwBox.getText());
                    System.out.println(test);
                    new CalendarScreen(primaryStage, clientConnection.getUsers(), clientConnection.getLoggedInUser(), true, clientConnection);
                }
                catch(Exception e1){
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Wrong username or password");
                }
            }
        }
    });
    primaryStage.show();
    }
}
