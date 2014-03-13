package UI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Wien on 12.03.14.
 */
public class NotificationsScreen /*extends Application */{

    Button button;
    //public void start(Stage notificationsStage){
    public NotificationsScreen(final Stage notificationsStage){
        GridPane editGrid = new GridPane();
        editGrid.setHgap(5);
        editGrid.setVgap(5);


        for(int i = 1; i < 5/*notifications.length()*/; i++){
            button = new Button();
            //TEXT SHOULD BE DESCRIPTION FROM EVENT?
            button.setText("Notification "+i);
            button.setMinWidth(300);
            editGrid.add(button, 0,i);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    /*if (userIsOwner()){
                        new EditScreen(new Stage());
                    }
                    else{
                        new ViewScreen(new Stage());
                    }*/
                    new AlarmScreen(new Stage());
                    Stage stage = (Stage) button.getScene().getWindow();
                    stage.close();
                }
            });
        }

        notificationsStage.setTitle("Notifications");
        notificationsStage.setScene(new Scene(editGrid, 300, 200));
        notificationsStage.show();
    }
/*
    public static void main (String[] args){
        launch(args);
    }
    */
}
