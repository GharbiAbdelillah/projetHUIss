/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huissier_de_justice;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author abdelillah gharbi
 */
public class StackPaneController implements Initializable {

   public static StackPane stackContent ;
   @FXML
    private StackPane stack = new StackPane();
     
     
   private  void starGame() throws IOException{
       stackContent = stack;
          stack.getChildren().clear();
         stack.getChildren().add( FXMLLoader.load(getClass().getResource("/dashboard/accueil.fxml"))); 
       
   } 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           starGame();
       } catch (IOException ex) {
           Logger.getLogger(StackPaneController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }    
    
}
