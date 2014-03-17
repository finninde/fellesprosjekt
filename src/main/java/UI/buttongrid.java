package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;



public class buttongrid extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("GridPane example");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(25, 25, 25, 25));


        Button[][] btn = new Button[5][5];

        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn.length;j++){

                btn[i][j] = new Button(""+i+","+""+j);
                btn[i][j].setPrefSize(50, 50);
                gridPane.add(btn[i][j], i, j);
            }
        }
        Scene scene = new Scene(gridPane, 300, 275 );

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}