/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard;

import Login.loginController;
import static Login.loginController.accueilStage;
import static huissier_de_justice.StackPaneController.stackContent;
import huissier_de_justice.main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gharbi abdelillah
 */
public class AccueilController implements Initializable {
    public static Stage stage2 = new Stage();
    public Stage stage = new Stage();
    @FXML
    private AnchorPane anchorPane;
    
    private void logOut(){
         loginController.accueilStage.close();
         main.logStage.show();
    }
    
    @FXML
    private void getFiles(ActionEvent event) throws IOException {
         accueilStage.setTitle("الملفات");
         stackContent.getChildren().clear();
         stackContent.getChildren().add( FXMLLoader.load(getClass().getResource("/el_Milafat_View/el_milafat.fxml"))); 
    }

    @FXML
    private void getMahadir(ActionEvent event) throws IOException {
//        accueilStage.setTitle("المحاضر");
//         stackContent.getChildren().clear();
//         stackContent.getChildren().add( FXMLLoader.load(getClass().getResource("/el_Mahadir/Elmahadir.fxml"))); 
stage2 = stage;
             anchorPane.setOpacity(0.4);
       Parent root = FXMLLoader.load(getClass().getResource("/el_Mahadir/Elmahadir.fxml"));
       Scene scene = new Scene(root);
       scene.setFill(new Color(0, 0, 0, 0));
       stage.setTitle("المحاضر");
       stage.setScene(scene);
       stage.getIcons().add(new Image(main.class.getResourceAsStream("logoCompany.jpg")));
        stage.setMaximized(false);
          stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.centerOnScreen();
       stage.show();
      
       anchorPane.setOpacity(1);
    }
    
     @FXML
    private void getArchive(ActionEvent event) throws IOException {
                accueilStage.setTitle("الأرشبف");
         stackContent.getChildren().clear();
         stackContent.getChildren().add( FXMLLoader.load(getClass().getResource("/el_Archive/tek_TableView.fxml"))); 
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

   

}
