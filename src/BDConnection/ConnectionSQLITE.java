/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author abdelillah gharbi
 */
public class ConnectionSQLITE {
   
   public static Connection getConnection()throws SQLException, ClassNotFoundException{
      String db_string = "huissierkeeper.sqlite";
      
       try {
           Class.forName("org.sqlite.JDBC");
           SQLiteConfig config = new SQLiteConfig(); //I add this configuration 
           config.enforceForeignKeys(true);
           System.out.println("++++++++++data base is runing+++++++++++++");
           
           return DriverManager.getConnection("jdbc:sqlite:"+db_string,config.toProperties());
       } catch (ClassNotFoundException e) {

       }
       
       
       return null;
   }
   
   
}
