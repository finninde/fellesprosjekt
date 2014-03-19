package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Wien on 12.03.14.
 */
public class NotificationsScreen /*extends Application */{

    Button button;
    //public void start(Stage notificationsStage){
    public NotificationsScreen(final Stage notificationsStage/*, User user, ArrayList<Appointment> notifications TODO EDIT PARAMETERS*/){
        GridPane editGrid = new GridPane();
        editGrid.setHgap(5);
        editGrid.setVgap(5);


        for(int i = 1; i < 5/*TODO notifications.length()*/; i++){
            button = new Button();

            button.setText("Notification "/*TODO TEXT SHOULD BE DESCRIPTION FROM EVENT?*/+i);
            button.setMinWidth(300);
            editGrid.add(button, 0,i);
            setFont(Font.font("Helvetica-Ultra-Light", 13));
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                //Appointment appointment = new Appointment();
                public void handle(ActionEvent actionEvent) {
                    /*TODO FIX LOGIC TO FIT EditScreen AND ViewScreen
                    GET FROM DATABASE
                    appointment = notifications[i];
                    if (appointment.getOwner() == user){
                        new EditScreen(new Stage(), appointment);
                    }
                    else{
                        new ViewScreen(new Stage(), appointment);
                    }*/
                    //new AlarmScreen(new Stage());                         //TODO THIS IS JUST A TEST
                    Stage stage = (Stage) button.getScene().getWindow();  //TODO CLOSE WINDOW OR NOT?
                    stage.close();
                }
            });
        }


        notificationsStage.setTitle("Notifications");
        notificationsStage.setScene(new Scene(editGrid, 300, 200));
        notificationsStage.show();
    }

    private void setFont(Font font){
        button.setFont(font);
    }
/*
    public static void main (String[] args){
        launch(args);
    }
    */
}
