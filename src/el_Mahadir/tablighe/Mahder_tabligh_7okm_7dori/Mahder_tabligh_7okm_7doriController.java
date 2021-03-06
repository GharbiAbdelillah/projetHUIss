/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Mahadir.tablighe.Mahder_tabligh_7okm_7dori;

import el_Mahadir.Tek_hodor.*;
import BDConnection.ConnectionSQLITE;
import static Login.loginController.accueilStage;
import el_Mahadir.List.SearchList;
import static huissier_de_justice.StackPaneController.stackContent;
import java.io.IOException;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
public class Mahder_tabligh_7okm_7doriController implements Initializable {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Stage stage = new Stage();
    ObservableList<SearchList> listSearch;

    String str1;
    String[] str2;
    Integer annee;
    Integer sousAnnee;
    @FXML
    private TableView<SearchList> table_البحث;
    @FXML
    private TableColumn<SearchList, String> column_رقم_الملف;
    @FXML
    private TableColumn<SearchList, String> column_نوع_الملف;
    @FXML
    private TableColumn<SearchList, String> column_طبيعة_الملف;
    @FXML
    private TableColumn<SearchList, String> column_الطالب;
    @FXML
    private TableColumn<SearchList, String> column_المطلوب;
    @FXML
    private ComboBox<String> comboTypeMahder;
    @FXML
    private Label lbl_قسم;
    @FXML
    private Button btn_ajoute;
    @FXML
    private TextField tf_رقم_الملف;
    @FXML
    private TextField tf_السيد;
    @FXML
    private TextField tf_سكن_الطالب;

    @FXML
    private TextField tf_المطلوب;
    @FXML
    private TextField tf_سكن_المطلوب;
    @FXML
    private TextField tf_المحكمة;
    @FXML
    private TextField tf_قسم;
    @FXML
    private TextField tf_رقم_الفهرس;
    @FXML
    private Label isEmpty;
    @FXML
    private TextField tf_رقم_الجدول;
    @FXML
    private Label lbl_الجدول;
    @FXML
    private DatePicker dp_تاريخ;
    @FXML
    private TextField tf_المحامي;

    public Label choose_تبليغ ;

    @FXML
    private void back(ActionEvent event) throws IOException {
        accueilStage.setTitle("الإستقبال");
        stackContent.getChildren().clear();
        stackContent.getChildren().add(FXMLLoader.load(getClass().getResource("/dashboard/accueil.fxml")));
    }

    private void initTable() {
        ///////////////////////////////        الملفـــــــــــــــــــات//////////////////////////////////
        column_رقم_الملف.setCellValueFactory(new PropertyValueFactory<SearchList, String>("column_رقم_الملف"));
        column_نوع_الملف.setCellValueFactory(new PropertyValueFactory<SearchList, String>("column_نوع_الملف"));
        column_طبيعة_الملف.setCellValueFactory(new PropertyValueFactory<SearchList, String>("column_طبيعة_الملف"));
        column_الطالب.setCellValueFactory(new PropertyValueFactory<SearchList, String>("column_الطالب"));
        column_المطلوب.setCellValueFactory(new PropertyValueFactory<SearchList, String>("column_المطلوب"));

    }

    @FXML
    private void add_mahderTaklif(ActionEvent event) throws SQLException, JRException {

        String sql = "insert into tabligh values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        if (isValidConditionModification()) {
            if (isNewMahder()) {

                ps = conn.prepareStatement(sql);
                ps.setString(1, tf_رقم_الملف.getText());
                ps.setString(2, "" + sousAnnee);
//                ps.setString(3, "محضـر تبليــغ حكم حضـوري");
                ps.setString(3, choose_تبليغ.getText());
                ps.setString(4, tf_المحكمة.getText());
                ps.setString(5, tf_قسم.getText());
                ps.setString(6, dp_تاريخ.getValue().toString());
                ps.setString(7, tf_رقم_الفهرس.getText());
                ps.setString(8, tf_رقم_الجدول.getText());
                ps.setString(9, tf_السيد.getText());
                ps.setString(10, tf_المحامي.getText());
                ps.setString(11, tf_سكن_الطالب.getText());
                ps.setString(12, tf_المطلوب.getText());
                ps.setString(13, tf_سكن_المطلوب.getText());
                ps.setString(14, comboTypeMahder.getValue().toString());
                ps.executeUpdate();
                ps.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setContentText("تم الحفظ بنجاح :   ");
                alert.showAndWait();
                printTekHodor(choose_تبليغ.getText());
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

    private boolean isNewMahder() throws SQLException {

        String sql = "select * from tabligh where num_file='" + tf_رقم_الملف.getText() + "' and num_Annee ='" + sousAnnee + "'";
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
                || tf_رقم_الجدول.getText().trim().isEmpty()
                || tf_رقم_الفهرس.getText().trim().isEmpty()
                || dp_تاريخ.getValue() == null
                || tf_السيد.getText().trim().isEmpty()
                || tf_سكن_الطالب.getText().trim().isEmpty()
                || tf_المطلوب.getText().trim().isEmpty()
                || tf_سكن_المطلوب.getText().trim().isEmpty()) {

            isEmpty = false;
        } else {

            isEmpty = true;
        }
        return isEmpty;
    }

    private void printTekHodor(String choose_تبليغ) throws JRException {
        InputStream is = null;
        switch (choose_تبليغ) {
            case "محضـر تبليــغ حكم حضـوري":
                is = this.getClass().getResourceAsStream("/el_Mahadir/tablighe/Mahder_tabligh_7okm_7dori/report_tabligh_7okm_7dori.jrxml");
                break;
            case "محضـر تبليـغ حكم حضـوري إبتدائي نهائـي":
                is = this.getClass().getResourceAsStream("/el_Mahadir/tablighe/Mahder_tabligh_7okm_7odori_ibtidai_nihai/report_tabligh_7okm_7dori_ibtidai_nihai.jrxml");
                break;
          
        }

         
        JasperReport jr = JasperCompileManager.compileReport(is);
        HashMap<String, Object> parametre = new HashMap<>();

        parametre.put("السيد", tf_السيد.getText());
        parametre.put("avocat", tf_المحامي.getText());
        parametre.put("lieuPerson", tf_سكن_الطالب.getText());
        parametre.put("suspect", tf_المطلوب.getText());
        parametre.put("lieuSuspect", tf_سكن_المطلوب.getText());
        parametre.put("محكمة", tf_المحكمة.getText());
        parametre.put("قسم", tf_قسم.getText());
        parametre.put("تاريخ1", dp_تاريخ.getValue().toString());
        parametre.put("رقم_الملف", tf_رقم_الملف.getText());
        parametre.put("رقم_العام", "" + sousAnnee);
        parametre.put("type_قسم", "" + lbl_قسم.getText());
        parametre.put("رقم_الفهرس", tf_رقم_الفهرس.getText());
        parametre.put("type_جدول", lbl_الجدول.getText());
        parametre.put("جدول", tf_رقم_الجدول.getText());

        JasperPrint jp = JasperFillManager.fillReport(jr, parametre);
        JasperViewer.viewReport(jp, false);
    }

    private void uploadTableSearch() throws SQLException {
        table_البحث.getItems().clear();
        String sql = "select * from files";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            SearchList search = new SearchList();
            search.setColumn_رقم_الملف(rs.getString(1));
            search.setColumn_نوع_الملف(rs.getString(4));
            search.setColumn_طبيعة_الملف(rs.getString(5));
            search.setColumn_الطالب(rs.getString(8));
            search.setColumn_المطلوب(rs.getString(9));
            listSearch.add(search);
            table_البحث.setItems(listSearch);
        }
        ps.close();
        rs.close();

    }

    @FXML
    private void getComboType(ActionEvent event) {
        if (comboTypeMahder.getValue().equals("محكمة")) {
            lbl_الجدول.setText("رقم الجدول:");
            lbl_قسم.setText("قسم :");
        } else {
            lbl_الجدول.setText("رقم القضية:");
            lbl_قسم.setText("غرفة :");
        }
    }

    @FXML
    private void getClick(MouseEvent event) {
        tf_رقم_الملف.setText(table_البحث.getSelectionModel().getSelectedItem().getColumn_رقم_الملف());
        tf_السيد.setText(table_البحث.getSelectionModel().getSelectedItem().getColumn_الطالب());
        tf_المطلوب.setText(table_البحث.getSelectionModel().getSelectedItem().getColumn_المطلوب());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTypeMahder.setValue("محكمة");
        str1 = LocalDate.now().toString();
        str2 = str1.split("-");
        annee = Integer.parseInt(str2[0]);
        sousAnnee = annee % 100;
        comboTypeMahder.getItems().addAll("محكمة", "مجلس");
        try {
            conn = ConnectionSQLITE.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Tek_HodorTaslimController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mahder_tabligh_7okm_7doriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listSearch = FXCollections.observableArrayList();
        initTable();

        try {
            uploadTableSearch();
        } catch (SQLException ex) {
            Logger.getLogger(Mahder_tabligh_7okm_7doriController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
