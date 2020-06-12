/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Milafat_Controllers;

import BDConnection.ConnectionMySQL;
import BDConnection.ConnectionSQLITE;
import static Login.loginController.accueilStage;
import el_Milafat_List.Milafet;
import el_Milafat_List.Prix;
import static huissier_de_justice.StackPaneController.stackContent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class El_milafatController implements Initializable {
//******************************************* الملفات ***********************************

    private Stage stage = new Stage();
    private Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ObservableList<Milafet> listFile;
    private ObservableList<Prix> listPrix;

    @FXML
    private TableView<Milafet> table_الملفات;
    @FXML
    private TableColumn<Milafet, String> column_رقم_الملف;
    @FXML
    private TableColumn<Milafet, String> column_تاريخ_ايداع_الملف;
    @FXML
    private TableColumn<Milafet, String> column_نوع_الملف;
    @FXML
    private TableColumn<Milafet, String> column_طبيعة_الملف;
    @FXML
    private TableColumn<Milafet, String> column_تاريخ_الإستدعاء;
    @FXML
    private TableColumn<Milafet, String> column_تاريخ_الإنجاز;
    @FXML
    private TableColumn<Milafet, String> column_الطالب;
    @FXML
    private TableColumn<Milafet, String> column_المطلوب;
    @FXML
    private TableColumn<Milafet, String> column_رقم_العام;
    @FXML
    private TableView<Prix> table_المبلغ;
    @FXML
    private TableColumn<Prix, String> column_دفع;
    @FXML
    private TableColumn<Prix, String> column_تسبيق;
    @FXML
    private TableColumn<Prix, String> column_تسوية;
    @FXML
    private TableColumn<Prix, String> column_المجموع;
    @FXML
    private AnchorPane anchorPane;
    String str1;
    String[] str2;
    Integer annee;
    Integer sousAnnee;
    @FXML
    private TextField tf_cherchPerson;

    @FXML
    private void back(ActionEvent event) throws IOException {
        accueilStage.setTitle("الإستقبال");
        stackContent.getChildren().clear();
        stackContent.getChildren().add(FXMLLoader.load(getClass().getResource("/dashboard/accueil.fxml")));
    }

    @FXML
    private void reload() throws SQLException {
        String sql = "select * from files order by num_file Asc";
        uploadTableMilafat(sql);
    }

    @FXML
    private void search() throws SQLException {
        String sql = "select * from files where name_person like '%" + tf_cherchPerson.getText() + "%' order by num_file";
        uploadTableMilafat(sql);
    }

    @FXML
    private void deleteFile(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("حوار للتأكيد");
        alert.setHeaderText("هل أنت متأكد من قرار الحذف");
        Milafet milaf = (Milafet) table_الملفات.getSelectionModel().getSelectedItem();
        if (milaf != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                String sql = "delete from files where num_file = ? and num_Annee = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, milaf.getColumn_رقم_الملف());
                ps.setString(2, milaf.getColumn_رقم_العام());
                ps.executeUpdate();
                
                ps.close();
                reload();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("إلغاء عملية الحذف");
                alert2.setHeaderText("تم إلغاء عملية الحذف للملف ");
                alert2.showAndWait();

            }
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("خطــــأ");
            alert2.setHeaderText("إختــــــــر ملف من فضلك");
            alert2.showAndWait();

        }
    }

    @FXML
    private void showFile(ActionEvent event) throws IOException {
        if (!table_المبلغ.getItems().isEmpty()) {

            anchorPane.setOpacity(0.4);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/el_Milafat_View/ajouterMilafe.fxml"));
            fxmlLoader.load();
            AjouterMilafeController milafeController = fxmlLoader.getController();

            Parent root = fxmlLoader.getRoot();
            Scene scene = new Scene(root);
            scene.setFill(new Color(0, 0, 0, 0));
            stage.setScene(scene);
            milafeController.btn_ajoute.setVisible(false);
            milafeController.btn_modifier.setVisible(true);
            milafeController.tf_رقم_الملف.setText(table_الملفات.getSelectionModel().getSelectedItem().getColumn_رقم_الملف());
            milafeController.dp_تاريخ_ايداع_الملف.setValue(LocalDate.parse(table_الملفات.getSelectionModel().getSelectedItem().getColumn_تاريخ_ايداع_الملف()));
            milafeController.combo_نوع_الملف.setPromptText(table_الملفات.getSelectionModel().getSelectedItem().getColumn_نوع_الملف());
            milafeController.combo_طبيعة_الملف.setPromptText(table_الملفات.getSelectionModel().getSelectedItem().getColumn_طبيعة_الملف());
            milafeController.dp_تاريخ_الإستدعاء.setValue(LocalDate.parse(table_الملفات.getSelectionModel().getSelectedItem().getColumn_تاريخ_الإستدعاء()));
            milafeController.dp_تاريخ_الإنجاز.setValue(LocalDate.parse(table_الملفات.getSelectionModel().getSelectedItem().getColumn_تاريخ_الإنجاز()));
            milafeController.tf_إسم_الطالب.setText(table_الملفات.getSelectionModel().getSelectedItem().getColumn_الطالب());
            milafeController.tf_إسم_المطلوب.setText(table_الملفات.getSelectionModel().getSelectedItem().getColumn_المطلوب());
            milafeController.tf_دفع.setText(table_المبلغ.getItems().get(0).getPay());
            milafeController.tf_تسبيق.setText(table_المبلغ.getItems().get(0).getAdvance_payment());
            milafeController.tf_تسوية.setText(table_المبلغ.getItems().get(0).getReglement());
            milafeController.tf_المجموع.setText(table_المبلغ.getItems().get(0).getTotale());
            milafeController.tf_رقم_الملف.setDisable(true);

            stage.showAndWait();
            anchorPane.setOpacity(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("خطـــــأ");
            alert.setHeaderText("يجـــب أن تختار مــــلــف");
            alert.showAndWait();

        }
    }

    @FXML
    private void addFile(ActionEvent event) throws IOException {
        anchorPane.setOpacity(0.4);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/el_Milafat_View/ajouterMilafe.fxml"));

        fxmlLoader.load();
        Parent root = fxmlLoader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        AjouterMilafeController milafeController = fxmlLoader.getController();
        milafeController.btn_ajoute.setVisible(true);
        milafeController.btn_modifier.setVisible(false);
        stage.setScene(scene);

        stage.showAndWait();
        anchorPane.setOpacity(1);
    }

    private void initTable() {
        ///////////////////////////////        الملــفـــــات       //////////////////////////////////
        column_رقم_الملف.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_رقم_الملف"));
        column_تاريخ_ايداع_الملف.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_تاريخ_ايداع_الملف"));
        column_نوع_الملف.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_نوع_الملف"));
        column_طبيعة_الملف.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_طبيعة_الملف"));
        column_تاريخ_الإستدعاء.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_تاريخ_الإستدعاء"));
        column_تاريخ_الإنجاز.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_تاريخ_الإنجاز"));
        column_الطالب.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_الطالب"));
        column_المطلوب.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_المطلوب"));
        column_رقم_العام.setCellValueFactory(new PropertyValueFactory<Milafet, String>("column_رقم_العام"));

        //////////////////////////////////////////////  المبالغ////////////////////////////////////////
        column_دفع.setCellValueFactory(new PropertyValueFactory<Prix, String>("pay"));
        column_تسبيق.setCellValueFactory(new PropertyValueFactory<Prix, String>("advance_payment"));
        column_تسوية.setCellValueFactory(new PropertyValueFactory<Prix, String>("Reglement"));
        column_المجموع.setCellValueFactory(new PropertyValueFactory<Prix, String>("totale"));

    }

    private void uploadTableMilafat(String sql) throws SQLException {
        table_الملفات.getItems().clear();

        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Milafet milaf = new Milafet();
            milaf.setColumn_رقم_الملف(rs.getString(1));
            milaf.setColumn_رقم_العام(rs.getString(2)); 
            milaf.setColumn_تاريخ_ايداع_الملف(rs.getString(3));
            milaf.setColumn_نوع_الملف(rs.getString(4));
            milaf.setColumn_طبيعة_الملف(rs.getString(5));
            milaf.setColumn_تاريخ_الإستدعاء(rs.getString(6));
            milaf.setColumn_تاريخ_الإنجاز(rs.getString(7));
            milaf.setColumn_الطالب(rs.getString(8));
            milaf.setColumn_المطلوب(rs.getString(9));

            listFile.add(milaf);
            table_الملفات.setItems(listFile);
        }
        ps.close();
        rs.close();

    }

    private void uploadTablePrix(String num_file, Integer sousAnnee) throws SQLException {
        table_المبلغ.getItems().clear();
        String sql = "select * from prix where num_file ='" + num_file + "'and num_Annee='" + sousAnnee + "' ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Prix prix = new Prix();
            prix.setPay(rs.getString(3));
            prix.setAdvance_payment(rs.getString(4));
            prix.setReglement(rs.getString(5));
            prix.setTotale(rs.getString(6));

            listPrix.add(prix);
            table_المبلغ.setItems(listPrix);

        }
        ps.close();
        rs.close();
    }

    @FXML
    private void getPrix(MouseEvent event) throws SQLException {
        if (table_الملفات.getSelectionModel().getSelectedItem() != null) {
            uploadTablePrix(table_الملفات.getSelectionModel().getSelectedItem().getColumn_رقم_الملف(), sousAnnee);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        str1 = LocalDate.now().toString();
        str2 = str1.split("-");
        annee = Integer.parseInt(str2[0]);
        sousAnnee = annee % 100;

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        try {
            conn = ConnectionSQLITE.getConnection();

        } catch (SQLException ex) {
            Logger.getLogger(El_milafatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(El_milafatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listFile = FXCollections.observableArrayList();
        listPrix = FXCollections.observableArrayList();
        initTable();

        try {
            reload();
        } catch (SQLException ex) {
            Logger.getLogger(El_milafatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
