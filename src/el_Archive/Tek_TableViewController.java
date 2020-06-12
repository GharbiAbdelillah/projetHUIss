/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Archive;

import el_Mahadir.Tek_hodor.*;
import BDConnection.ConnectionSQLITE;
import static Login.loginController.accueilStage;
import el_Archive.archiveList.viewArchiveList;
import el_Mahadir.tablighe.Mahder_tabligh_7okm_7dori.Mahder_tabligh_7okm_7doriController_1;
import el_Mahadir.tablighe.Mahder_tabligh_7okm_ghiyabi.Mahder_tabligh_7okm_ghiyabiController_1;
import static huissier_de_justice.StackPaneController.stackContent;
import static huissier_de_justice.main.logStage;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
public class Tek_TableViewController implements Initializable {

    Connection conn;
    PreparedStatement ps = null;
    ResultSet rs = null;
    ObservableList<viewArchiveList> listArchive;

    Stage stage = new Stage();
    @FXML
    private TableView<viewArchiveList> table_التكليف;
    @FXML
    private TableColumn<viewArchiveList, String> column_الملف;
    @FXML
    private TableColumn<viewArchiveList, String> column_المخاطب;
    @FXML
    private TableColumn<viewArchiveList, String> column_المطلوب;
    @FXML
    private TableColumn<viewArchiveList, String> column_الجلسة;
    @FXML
    private TableColumn<viewArchiveList, String> column_نوع_المحضر;
    @FXML
    private TableColumn<viewArchiveList, String> column_رقم_العام;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField tf_cherchPerson;
    @FXML
    private ComboBox<String> combo_المحاضر;

    @FXML
    private void back(ActionEvent event) throws IOException {
        accueilStage.setTitle("الإستقبال");
        stackContent.getChildren().clear();
        stackContent.getChildren().add(FXMLLoader.load(getClass().getResource("/dashboard/accueil.fxml")));
    }

    private void initTable() {
        ///////////////////////////////        المــحـــاضر//////////////////////////////////
        column_الملف.setCellValueFactory(new PropertyValueFactory<viewArchiveList, String>("column_الملف"));
        column_المخاطب.setCellValueFactory(new PropertyValueFactory<viewArchiveList, String>("column_المخاطب"));
        column_المطلوب.setCellValueFactory(new PropertyValueFactory<viewArchiveList, String>("column_المطلوب"));
        column_الجلسة.setCellValueFactory(new PropertyValueFactory<viewArchiveList, String>("column_الجلسة"));
        column_نوع_المحضر.setCellValueFactory(new PropertyValueFactory<viewArchiveList, String>("column_نوع_المحضر"));
        column_رقم_العام.setCellValueFactory(new PropertyValueFactory<viewArchiveList, String>("column_رقم_العام"));
    }

    @FXML
    private void showMahder(ActionEvent event) throws IOException, SQLException {

        if (combo_المحاضر.getValue().equals("التكليف بالحضور")) {
            showTaklif();
        } else if (combo_المحاضر.getValue().equals("تبليغات")) {
            switch (table_التكليف.getSelectionModel().getSelectedItem().getColumn_نوع_المحضر()) {

                case "محضـر تبليــغ حكم حضـوري":
                    showTabligh_7okm_7odori("محضـر تبليــغ حكم حضـوري");

                    break;
                case "محضـر تبليــغ حكم غيابي":
                    showTabligh_7okm_ghiyabi("محضـر تبليــغ حكم غيابي");
                    
                    break;
                case "محضـر تبليـغ حكم حضـوري إبتدائي نهائـي":
                    showTabligh_7okm_7odori("محضـر تبليـغ حكم حضـوري إبتدائي نهائـي");
                    
                    break;
                case "محضـر تبليـغ حكم غيابـي إبتـدائـي نهائـي":
                    showTabligh_7okm_ghiyabi("محضـر تبليـغ حكم غيابـي إبتـدائـي نهائـي");
                    
                    break;
                case "محضـر تبليـغ حكم غيابـي نهائـي":
                    showTabligh_7okm_ghiyabi("محضـر تبليـغ حكم غيابـي نهائـي");
                    break;
            }
        }
    }

    ///////////////////////////////////////////////////////  modifie tablighat ///////////////////////////////
    //////////////////////////////////// tabligh 01 ///////////////////////////////////////////////
    private void showTabligh_7okm_7odori(String choose) throws SQLException, IOException {
        anchorPane.setOpacity(0.4);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_7dori/mahder_tabligh_7okm_7dori_1.fxml"));
        fxmlLoader.load();
        String sql = "select * from tabligh where num_file ='" + table_التكليف.getSelectionModel().getSelectedItem().getColumn_الملف() + "' and num_Annee ='" + table_التكليف.getSelectionModel().getSelectedItem().getColumn_رقم_العام() + "' ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        Mahder_tabligh_7okm_7doriController_1 tablighController = fxmlLoader.getController();
        while (rs.next()) {

            tablighController.tf_رقم_الملف.setText(rs.getString(1));
            tablighController.tf_رقم_العام.setText(rs.getString(2));
            tablighController.tf_المحكمة.setText(rs.getString(4));
            tablighController.tf_قسم.setText(rs.getString(5));
            tablighController.dp_تاريخ.setValue(LocalDate.parse(rs.getString(6)));
            tablighController.tf_رقم_الفهرس.setText(rs.getString(7));
            tablighController.tf_رقم_الجدول.setText(rs.getString(8));
            tablighController.tf_السيد.setText(rs.getString(9));
            tablighController.tf_المحامي.setText(rs.getString(10));
            tablighController.tf_سكن_الطالب.setText(rs.getString(11));
            tablighController.tf_المطلوب.setText(rs.getString(12));
            tablighController.tf_سكن_المطلوب.setText(rs.getString(13));
            tablighController.choose_تبليغ.setText(choose);
            if (rs.getString(14).equals("محكمة")) {
                tablighController.lbl_قسم.setText("قسم :");
                tablighController.lbl_الجدول.setText("رقم الجدول:");
            } else {
                tablighController.lbl_قسم.setText("غرفة:");
                tablighController.lbl_الجدول.setText("رقم القضية:");
            }
        }

        ps.close();
        rs.close();
        Parent root = fxmlLoader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        stage.setScene(scene);
        tablighController.btn_modifier.setVisible(true);
        tablighController.btnPrint.setVisible(true);
        stage.showAndWait();
        anchorPane.setOpacity(1);
    }

    //////////////////////////////////// tabligh 02 ///////////////////////////////////////////////
    private void showTabligh_7okm_ghiyabi(String choose) throws SQLException, IOException {
        anchorPane.setOpacity(0.4);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_ghiyabi/Mahder_tabligh_7okm_ghiyabi_1.fxml"));
        fxmlLoader.load();
        String sql = "select * from tabligh where num_file ='" + table_التكليف.getSelectionModel().getSelectedItem().getColumn_الملف() + "' and num_Annee ='" + table_التكليف.getSelectionModel().getSelectedItem().getColumn_رقم_العام() + "' ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        Mahder_tabligh_7okm_ghiyabiController_1 tablighController = fxmlLoader.getController();
        while (rs.next()) {

            tablighController.tf_رقم_الملف.setText(rs.getString(1));
            tablighController.tf_رقم_العام.setText(rs.getString(2));
            tablighController.tf_المحكمة.setText(rs.getString(4));
            tablighController.tf_قسم.setText(rs.getString(5));
            tablighController.dp_تاريخ.setValue(LocalDate.parse(rs.getString(6)));
            tablighController.tf_رقم_الفهرس.setText(rs.getString(7));
            tablighController.tf_رقم_الجدول.setText(rs.getString(8));
            tablighController.tf_السيد.setText(rs.getString(9));
            tablighController.tf_المحامي.setText(rs.getString(10));
            tablighController.tf_سكن_الطالب.setText(rs.getString(11));
            tablighController.tf_المطلوب.setText(rs.getString(12));
            tablighController.choose_تبليغ.setText(choose);
            if (rs.getString(14).equals("محكمة")) {
                tablighController.lbl_قسم.setText("قسم :");
                tablighController.lbl_الجدول.setText("رقم الجدول:");
            } else {
                tablighController.lbl_قسم.setText("غرفة:");
                tablighController.lbl_الجدول.setText("رقم القضية:");
            }
        }

        ps.close();
        rs.close();
        Parent root = fxmlLoader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        stage.setScene(scene);
        tablighController.btn_modifier.setVisible(true);
        tablighController.btnPrint.setVisible(true);
        stage.showAndWait();
        anchorPane.setOpacity(1);
    }

    ///////////////////////////////////////////////////////  modifie taklifat   ///////////////////////////////
    private void showTaklif() throws SQLException, IOException {
        anchorPane.setOpacity(0.4);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/Tek_hodor/tek_Hodor&Taslim.fxml"));
        fxmlLoader.load();
        String sql = "select * from mahadir where num_file ='" + table_التكليف.getSelectionModel().getSelectedItem().getColumn_الملف() + "' and num_Annee ='" + table_التكليف.getSelectionModel().getSelectedItem().getColumn_رقم_العام() + "' ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        Tek_HodorTaslimController hodorController = fxmlLoader.getController();
        while (rs.next()) {

            hodorController.tf_رقم_الملف.setText(rs.getString(1));
            hodorController.tf_رقم_العام.setText(rs.getString(2));
            hodorController.tf_المحكمة.setText(rs.getString(4));
            hodorController.tf_قسم.setText(rs.getString(5));
            hodorController.dp_الجلسة.setValue(LocalDate.parse(rs.getString(6)));
            hodorController.tf_الجلسة_الساعة.setText(rs.getString(7));
            hodorController.tf_السيد.setText(rs.getString(8));
            hodorController.tf_المحامي.setText(rs.getString(9));
            hodorController.tf_سكن_الطالب.setText(rs.getString(10));
            hodorController.tf_المطلوب.setText(rs.getString(11));
            hodorController.tf_سكن_المطلوب.setText(rs.getString(12));
            if (rs.getString(13).equals("محكمة")) {
                hodorController.lbl_قسم.setText("قسم :");
            } else {
                hodorController.lbl_قسم.setText("غرفة:");
            }
        }

        ps.close();
        rs.close();
        Parent root = fxmlLoader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0, 0, 0, 0));
        stage.setScene(scene);
        hodorController.btn_modifier.setVisible(true);
        hodorController.btnPrint.setVisible(true);
        stage.showAndWait();
        anchorPane.setOpacity(1);
    }

    @FXML
    private void deleteTaklif(ActionEvent event) throws SQLException {
        String sql = "";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("حوار للتأكيد");
        alert.setHeaderText("هل أنت متأكد من قرار الحذف");
        viewArchiveList archive = (viewArchiveList) table_التكليف.getSelectionModel().getSelectedItem();

        if (archive != null) {

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                if (combo_المحاضر.getValue().equals("التكليف بالحضور")) {
                    sql = "delete from mahadir where num_file = ? and num_Annee = ?";
                } else if (combo_المحاضر.getValue().equals("تبليغات")) {
                    sql = "delete from tabligh where num_file = ? and num_Annee = ?";
                }
                ps = conn.prepareStatement(sql);
                ps.setString(1, archive.getColumn_الملف());
                ps.setString(2, archive.getColumn_رقم_العام());
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

    private void uploadTableMilafat(String sql) throws SQLException {
        table_التكليف.getItems().clear();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            viewArchiveList Archive = new viewArchiveList();
            Archive.setColumn_الملف(rs.getString(1));
            Archive.setColumn_رقم_العام(rs.getString(2));
            Archive.setColumn_نوع_المحضر(rs.getString(3));
            Archive.setColumn_الجلسة(rs.getString(6));
            Archive.setColumn_المخاطب(rs.getString(8));
            Archive.setColumn_المطلوب(rs.getString(11));
            listArchive.add(Archive);
            table_التكليف.setItems(listArchive);
        }
        ps.close();
        rs.close();

    }

    @FXML
    private void reload() throws SQLException {
        String sql = "";
        if (combo_المحاضر.getValue().equals("التكليف بالحضور")) {
            sql = "select * from mahadir order by num_file Asc";
        } else if (combo_المحاضر.getValue().equals("تبليغات")) {
            sql = "select * from tabligh order by num_file Asc";
        }
        uploadTableMilafat(sql);
    }

    @FXML
    private void search() throws SQLException {
        String sql = "select * from mahadir where person like '%" + tf_cherchPerson.getText() + "%' order by num_file";
        uploadTableMilafat(sql);
    }

    @FXML
    private void getComboMahder(ActionEvent event) throws SQLException {
        reload();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        combo_المحاضر.getItems().setAll("التكليف بالحضور", "تبليغات");
        combo_المحاضر.setValue("التكليف بالحضور");
        try {
            conn = ConnectionSQLITE.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Tek_TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tek_TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listArchive = FXCollections.observableArrayList();
        initTable();
        try {
            reload();
        } catch (SQLException ex) {
            Logger.getLogger(Tek_TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
