/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huissier_de_justice;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class main extends Application {
    
    public static Stage logStage;
    @Override
    public void start(Stage stage)  {
        try {
//            Parent root = FXMLLoader.load(getClass().getResource("/el_Mahadir/Tek_hodor/tek_TableView.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
            Scene scene = new Scene(root);
            logStage = stage;
           // stage.getIcons().add(new Image(main.class.getResourceAsStream("logoCompany.png")));
            stage.setMinHeight(500.0);
            stage.setMinWidth(850.0);
            stage.setScene(scene);
          //  stage.initStyle(StageStyle.UNDECORATED);
            
            stage.show();
            
//             PauseTransition delay = new PauseTransition(Duration.seconds(5));
//            delay.setOnFinished( event -> stage.close() );
//             delay.play();
//            
         
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }

    
//    private Stage switchStage;
//final double opacity=1;
//@Override
//public void start(Stage primaryStage) {
//try {
//
//        switchStage = primaryStage;
//        logStage = primaryStage;
//        
//
//
//       Parent root = FXMLLoader.load(getClass().getResource("/BeginningApp/beginningApp.fxml"));
//            Scene scene = new Scene(root);
//            scene.setFill(new Color(0,0,0,0));
//        primaryStage.setScene(scene);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//         primaryStage.getIcons().add(new Image(main.class.getResourceAsStream("logoCompany.jpg")));
//         primaryStage.centerOnScreen();
//        primaryStage.show();
//        Timeline tick0 = new Timeline();
//        tick0.setCycleCount(Timeline.INDEFINITE);
//        tick0.getKeyFrames().add(
//                new KeyFrame(new Duration(240), new EventHandler<ActionEvent>() {
//                    public void handle(ActionEvent t) {
//                        root.setOpacity(root.getOpacity()-0.004);
//                        if(root.getOpacity()<0.8){try {
//                            //30 divided by 0.01 equals 3000 so you take the duration and divide it be the opacity to get your transition time in milliseconds
//                            loggedIn();
//                            } catch (IOException ex) {
//                                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                            tick0.stop();
//                        }
//                    }}));
//        tick0.play();
//
//} catch(Exception e) {
//    e.printStackTrace();
//}
//}
//
//public void loggedIn() throws IOException 
//{
//switchStage.setTitle("Try");
//
//
//Parent root = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
//Scene scene = new Scene(root);
//switchStage.centerOnScreen();
//switchStage.setScene(scene);
//switchStage.show();
//}

    public static void main(String[] args) {
        launch(args);
    }
    
}
