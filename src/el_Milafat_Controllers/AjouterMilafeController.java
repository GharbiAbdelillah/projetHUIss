/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Milafat_Controllers;

import BDConnection.ConnectionSQLITE;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gharbi abdelillah
 */
public class AjouterMilafeController implements Initializable {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @FXML
    public TextField tf_رقم_الملف;
    
    @FXML
    public TextField tf_إسم_الطالب;
    @FXML
    public TextField tf_إسم_المطلوب;
    @FXML
    public TextField tf_دفع;
    @FXML
    public TextField tf_تسبيق;
    @FXML
    public TextField tf_تسوية;
    @FXML
    public TextField tf_المجموع;
    @FXML
    public DatePicker dp_تاريخ_ايداع_الملف;
    @FXML
    public DatePicker dp_تاريخ_الإستدعاء;
    @FXML
    public DatePicker dp_تاريخ_الإنجاز;
    @FXML
    public Button btn_modifier;
    @FXML
    public Button btn_ajoute;
    @FXML
    public ComboBox<String> combo_نوع_الملف;
    @FXML
    public ComboBox<String> combo_طبيعة_الملف;

    @FXML
    private Label isEmpty;
    @FXML
    private Button btnClose;
        String str1 ;
    String[] str2 ;
    Integer annee ;
    Integer sousAnnee ;
    /**
     * Initializes the controller class.
     */
    
     @FXML
   private void quit(){
       Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
   }
    @FXML
    private void addFile(ActionEvent event) throws SQLException {
         
        String sql = "insert into files values(?,?,?,?,?,?,?,?,?)";
        String sql2 = "insert into prix values(?,?,?,?,?,?)";
        if (isValidConditionModification()) {
            if (isNewFile()) {
                isEmptyPay();
                ps = conn.prepareStatement(sql);
                ps.setString(1, tf_رقم_الملف.getText());
                ps.setString(2, ""+sousAnnee);
                ps.setString(3, dp_تاريخ_ايداع_الملف.getValue().toString());
                ps.setString(4, combo_نوع_الملف.getValue().toString());
                ps.setString(5, combo_طبيعة_الملف.getValue().toString());
                ps.setString(6, dp_تاريخ_الإستدعاء.getValue().toString());
                ps.setString(7, dp_تاريخ_الإنجاز.getValue().toString());
                ps.setString(8, tf_إسم_الطالب.getText());
                ps.setString(9, tf_إسم_المطلوب.getText());
                ps.executeUpdate();
                ps.close();
                addPrix(sql2);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setContentText("تم الحفظ بنجاح :   ");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("خــطــأ :   ");
                alert.setContentText("هــذا الملــف موجود حاليا!!!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("خـطــأ :   ");
            alert.setContentText("إفحص المعلومات التي أدخلتها");
            alert.showAndWait();
        }
    }

    private void addPrix(String sql2) throws SQLException {
        ps = conn.prepareStatement(sql2);
        ps.setString(1, tf_رقم_الملف.getText());
        ps.setString(2, ""+sousAnnee);
        ps.setString(3, tf_دفع.getText());
        ps.setString(4, tf_تسبيق.getText());
        ps.setString(5, tf_تسوية.getText());
        ps.setString(6, tf_المجموع.getText());
        ps.executeUpdate();
        ps.close();
    }

    private boolean isNewFile() throws SQLException {

        String sql = "select * from files where num_file='" + tf_رقم_الملف.getText() + "' and num_Annee ='" + sousAnnee + "'";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            ps.close();
            rs.close();
            return false;
        }
        ps.close();
        rs.close();
        return true;
    }

    @FXML
    private void updateChanges(ActionEvent event) throws SQLException {
        String sql = "update files set date_file_posting=? , file_type=? , File_nature=? ,date_of_recall=? ,date_completion=? ,name_person=? ,name_suspect=?  where num_file='" + tf_رقم_الملف.getText() + "' and num_Annee='" + sousAnnee + "'";
        if (isValidConditionModification()) {
            isEmptyPay();
            ps = conn.prepareStatement(sql);
            ps.setString(1, dp_تاريخ_ايداع_الملف.getValue().toString());
            ps.setString(2, combo_نوع_الملف.getValue());
            ps.setString(3, combo_طبيعة_الملف.getValue());
            ps.setString(4, dp_تاريخ_الإستدعاء.getValue().toString());
            ps.setString(5, dp_تاريخ_الإنجاز.getValue().toString());
            ps.setString(6, tf_إسم_الطالب.getText());
            ps.setString(7, tf_إسم_المطلوب.getText());
            ps.executeUpdate();
            ps.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setContentText("تم الحفظ بنجاح :   ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("خـطــأ :   ");
            alert.setContentText("إفحص المعلومات التي أدخلتها");
            alert.showAndWait();

        }
    }

    private boolean isValidConditionModification() {
        isEmpty.setText("");
        boolean registration = false;
        if (isEmptyModification()) {
            registration = true;
            isEmpty.setText("جميع المعلومات قد تم حفظها");
            isEmpty.setStyle("-fx-text-fill:green;");

        } else {

            isEmpty.setText("أدخل جميع المعلومات من فضلك ");
            isEmpty.setStyle("-fx-text-fill:red;");

            registration = false;
        }

        return registration;

    }

    private boolean isEmptyModification() {
        boolean isEmpty = false;
        if (tf_رقم_الملف.getText().trim().isEmpty()
                || tf_إسم_الطالب.getText().trim().isEmpty()
                || tf_إسم_المطلوب.getText().trim().isEmpty()
                || dp_تاريخ_الإستدعاء.getValue() == null
                || dp_تاريخ_الإنجاز.getValue() == null
                || dp_تاريخ_ايداع_الملف.getValue() == null
                || (combo_نوع_الملف.getSelectionModel().isEmpty() && combo_نوع_الملف.getPromptText().isEmpty())
                || (combo_طبيعة_الملف.getSelectionModel().isEmpty() && combo_طبيعة_الملف.getPromptText().isEmpty())) {

            System.out.println("il y a un ou plusieur champs vide");
            isEmpty = false;
        } else {
            System.out.println("donne");
            isEmpty = true;
        }
        return isEmpty;
    }

    private void isEmptyPay() {
        if (tf_دفع.getText().trim().isEmpty()) {
            tf_دفع.setText("0");
        }
        if (tf_تسبيق.getText().trim().isEmpty()) {
            tf_تسبيق.setText("0");
        }
        if (tf_تسوية.getText().trim().isEmpty()) {
            tf_تسوية.setText("0");
        }
        if (tf_المجموع.getText().trim().isEmpty()) {
            tf_المجموع.setText("0");
        }

    }
         @FXML
    private void totalCost(KeyEvent event) {
        isEmptyPay();
        double nbr1,nbr2,nbr3;
        nbr1 = Double.parseDouble(tf_دفع.getText());
        nbr2 = Double.parseDouble(tf_تسبيق.getText());
        nbr3 = Double.parseDouble(tf_تسوية.getText());
        tf_المجموع.setText(""+(nbr1+nbr2+nbr3));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         str1 = LocalDate.now().toString();
        str2 = str1.split("-");
        annee = Integer.parseInt(str2[0]);
        sousAnnee = annee % 100;
        tf_دفع.setText("0");
        tf_تسبيق.setText("0");
        tf_تسوية.setText("0");
        tf_المجموع.setText("0");
        combo_نوع_الملف.getItems().addAll("تكليف بالحضور","تبليغ","معاينة","تنفيذ","القوة العمومية","الحجز والبيع العقاري");
        combo_طبيعة_الملف.getItems().addAll("تبليغ أحكام","إداري إستعجالي","إداري عادي","عقاري","تجاري","بحري","تجاري/بحري","شؤون الأسرة","إجتماعي","جزائي","استعجالي","شيك بدون رصيد","إعتراف بالدين");
        try {
            conn = ConnectionSQLITE.getConnection();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AjouterMilafeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AjouterMilafeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   

}
