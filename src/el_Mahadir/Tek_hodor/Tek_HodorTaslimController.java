/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Mahadir.Tek_hodor;

import BDConnection.ConnectionSQLITE;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author gharbi abdelillah
 */
public class Tek_HodorTaslimController implements Initializable {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Stage stage = new Stage();

    @FXML
    public TextField tf_رقم_الملف;

    @FXML
    public TextField tf_المحكمة;
    @FXML
    public TextField tf_قسم;
    @FXML
    public TextField tf_الجلسة_الساعة;

    @FXML
    public DatePicker dp_الجلسة;
    @FXML
    public TextField tf_السيد;
    @FXML
    public TextField tf_المحامي;
    @FXML
    public TextField tf_سكن_الطالب;
    @FXML
    public TextField tf_المطلوب;
    @FXML
    public TextField tf_سكن_المطلوب;
    @FXML
    public Button btn_modifier;
 
    @FXML
    private Label isEmpty;
    @FXML
    public Button btnPrint;
    @FXML
    private Button btnClose;
    @FXML
    public Label lbl_قسم;
    @FXML
    public TextField tf_رقم_العام;

    String str1;
    String[] str2;
    Integer annee;

    @FXML
    private void quit(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void update_mahderTaklif(ActionEvent event) throws SQLException, JRException {

        String sql = "update mahadir set mahkama=? , classe=? , date_jalsa=? ,time_jalsa=? ,person=? ,avocat=? ,lieu_person=? ,suspect=?,lieu_suspect=?  where num_file='" + tf_رقم_الملف.getText() + "' and num_Annee='" + tf_رقم_العام.getText() + "'";
        if (isValidConditionModification()) {
            
            ps = conn.prepareStatement(sql);
           
            ps.setString(1, tf_المحكمة.getText());
            ps.setString(2, tf_قسم.getText());
            ps.setString(3, dp_الجلسة.getValue().toString());
            ps.setString(4, tf_الجلسة_الساعة.getText());
            ps.setString(5, tf_السيد.getText());
            ps.setString(6, tf_المحامي.getText());
            ps.setString(7, tf_سكن_الطالب.getText());
            ps.setString(8, tf_المطلوب.getText());
            ps.setString(9, tf_سكن_المطلوب.getText());
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
                || tf_المحكمة.getText().trim().isEmpty()
                || tf_قسم.getText().trim().isEmpty()
                || tf_الجلسة_الساعة.getText().trim().isEmpty()
                || dp_الجلسة.getValue() == null
                || tf_السيد.getText().trim().isEmpty()
                || tf_المحامي.getText().trim().isEmpty()
                || tf_سكن_الطالب.getText().trim().isEmpty()
                || tf_المطلوب.getText().trim().isEmpty()
                || tf_سكن_المطلوب.getText().trim().isEmpty()) {

            isEmpty = false;
        } else {

            isEmpty = true;
        }
        return isEmpty;
    }

    private void printTekHodor() throws JRException {
        InputStream is = this.getClass().getResourceAsStream("/el_Mahadir/jasper_report/elTaklif_bilhodor.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(is);
        HashMap<String, Object> parametre = new HashMap<>();

        parametre.put("السيد", tf_السيد.getText());
        parametre.put("avocat", tf_المحامي.getText());
        parametre.put("lieuPerson", tf_سكن_الطالب.getText());
        parametre.put("suspect", tf_المطلوب.getText());
        parametre.put("lieuSuspect", tf_سكن_المطلوب.getText());
        parametre.put("محكمة", tf_المحكمة.getText());
        parametre.put("قسم", tf_قسم.getText());
        parametre.put("يوم_الجلسة", dp_الجلسة.getValue().toString());
        parametre.put("ساعة_الجلسة", tf_الجلسة_الساعة.getText());
        parametre.put("رقم_الملف", tf_رقم_الملف.getText());
        parametre.put("رقم_العام", "" + tf_رقم_العام.getText());
        parametre.put("type_قسم", "" + lbl_قسم.getText());

        JasperPrint jp = JasperFillManager.fillReport(jr, parametre);
        JasperViewer.viewReport(jp, false);
    }
    private void printTaslimTekHodor() throws JRException{
        InputStream is = this.getClass().getResourceAsStream("/el_Mahadir/jasper_report/taslimTAKLIF.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(is);
        HashMap<String, Object> parametre = new HashMap<>();

        parametre.put("السيد", tf_السيد.getText());
        parametre.put("avocat", tf_المحامي.getText());
        parametre.put("lieuPerson", tf_سكن_الطالب.getText());
        parametre.put("suspect", tf_المطلوب.getText());
        parametre.put("lieuSuspect", tf_سكن_المطلوب.getText());
        parametre.put("رقم_الملف", tf_رقم_الملف.getText());
        parametre.put("رقم_العام", "" + tf_رقم_العام.getText());

        JasperPrint jp = JasperFillManager.fillReport(jr, parametre);
        JasperViewer.viewReport(jp, false);
    }

    @FXML
    private void print(ActionEvent event) throws JRException {
        printTekHodor();
        printTaslimTekHodor();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            conn = ConnectionSQLITE.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Tek_HodorTaslimController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tek_HodorTaslimController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
