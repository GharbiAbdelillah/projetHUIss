/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package el_Mahadir;

import el_Mahadir.tablighe.Mahder_tabligh_7okm_7dori.*;
import static Login.loginController.accueilStage;
import static dashboard.AccueilController.stage2;
import el_Mahadir.tablighe.Mahder_tabligh_7okm_Idari_7odori.Mahder_tabligh_7okm_Idari_7odoriController;
import el_Mahadir.tablighe.Mahder_tabligh_7okm_ghiyabi.Mahder_tabligh_7okm_ghiyabiController;
import el_Mahadir.tablighe.Mahder_tabligh_9arar_ghiyabi.Mahder_tabligh_9arar_ghiyabiController;
import el_Mahadir.tablighe.Mahder_tabligh_Amre_Isti3jali.Mahder_tabligh_Amre_Isti3jaliController;
import static huissier_de_justice.StackPaneController.stackContent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gharbi abdelillah
 */
public class ElmahadirController implements Initializable {

    @FXML
    private ComboBox<String> combo_المحضر;
    @FXML
    private Button btnClose;

    @FXML
    private void quit(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void chooseMahder(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Mahder_tabligh_7okm_7doriController tabligh_Hodori;
        Mahder_tabligh_7okm_ghiyabiController tabligh_Ghiyabi;
        Mahder_tabligh_Amre_Isti3jaliController tabligh_Isti3jali;
        Mahder_tabligh_9arar_ghiyabiController tabligh_9arar;
        Mahder_tabligh_7okm_Idari_7odoriController tabligh_Idari;

        if (!combo_المحضر.getValue().equals(null)) {
            switch (combo_المحضر.getValue()) {

                case "التكليف بالحضور مع التسليم":
                    accueilStage.setTitle("التكليف بالحضور");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(FXMLLoader.load(getClass().getResource("/el_Mahadir/Tek_hodor/tek_Hodor&Taslim_1.fxml")));

                    break;

                case "محضـر تبليــغ حكم حضـوري":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_7dori/mahder_tabligh_7okm_7dori.fxml"));

                    accueilStage.setTitle("محضـر تبليــغ حكم حضـوري");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Hodori = fxmlLoader.getController();
                    tabligh_Hodori.choose_تبليغ.setText("محضـر تبليــغ حكم حضـوري");

                    break;

                case "محضـر تبليــغ حكم غيابي":
                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_ghiyabi/Mahder_tabligh_7okm_ghiyabi.fxml"));

                    accueilStage.setTitle("محضـر تبليــغ حكم غيابي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Ghiyabi = fxmlLoader.getController();
                    tabligh_Ghiyabi.choose_تبليغ.setText("محضـر تبليــغ حكم غيابي");

                    break;

                case "محضـر تبليـغ حكم حضـوري إبتدائي نهائـي":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_7dori/mahder_tabligh_7okm_7dori.fxml"));

                    accueilStage.setTitle("محضـر تبليـغ حكم حضـوري إبتدائي نهائـي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Hodori = fxmlLoader.getController();
                    tabligh_Hodori.choose_تبليغ.setText("محضـر تبليـغ حكم حضـوري إبتدائي نهائـي");

                    break;

                case "محضـر تبليـغ حكم غيابـي إبتـدائـي نهائـي":
                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_ghiyabi/Mahder_tabligh_7okm_ghiyabi.fxml"));

                    accueilStage.setTitle("محضـر تبليـغ حكم غيابـي إبتـدائـي نهائـي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Ghiyabi = fxmlLoader.getController();
                    tabligh_Ghiyabi.choose_تبليغ.setText("محضـر تبليـغ حكم غيابـي إبتـدائـي نهائـي");
                    break;

                case "محضـر تبليـغ حكم غيابـي نهائـي":
                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_ghiyabi/Mahder_tabligh_7okm_ghiyabi.fxml"));

                    accueilStage.setTitle("محضـر تبليـغ حكم غيابـي نهائـي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Ghiyabi = fxmlLoader.getController();
                    tabligh_Ghiyabi.choose_تبليغ.setText("محضـر تبليـغ حكم غيابـي نهائـي");
                    break;
                case "محضـر تبليــغ أمــر  استعجالي":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_Amre_Isti3jali/Mahder_tabligh_Amre_Isti3jali.fxml"));

                    accueilStage.setTitle("محضـر تبليــغ أمــر  استعجالي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Isti3jali = fxmlLoader.getController();
                    tabligh_Isti3jali.choose_تبليغ.setText("محضـر تبليــغ أمــر  استعجالي");

                    break;
                    case "محضـر تبليــغ أمــر  استعجالي غيابي":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_Amre_Isti3jali/Mahder_tabligh_Amre_Isti3jali.fxml"));

                    accueilStage.setTitle("محضـر تبليــغ أمــر  استعجالي غيابي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Isti3jali = fxmlLoader.getController();
                    tabligh_Isti3jali.choose_تبليغ.setText("محضـر تبليــغ أمــر  استعجالي غيابي");

                    break;
                    case "محضر تبليــغ قـــرار غيـابـي":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_9arar_ghiyabi/Mahder_tabligh_9arar_ghiyabi.fxml"));

                    accueilStage.setTitle("محضر تبليــغ قـــرار غيـابـي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_9arar = fxmlLoader.getController();
                    tabligh_9arar.choose_تبليغ.setText("محضر تبليــغ قـــرار غيـابـي");

                    break;
                    case "محضـر تبليــغ قـــرار حضـوري":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_9arar_ghiyabi/Mahder_tabligh_9arar_ghiyabi.fxml"));

                    accueilStage.setTitle("محضـر تبليــغ قـــرار حضـوري");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_9arar = fxmlLoader.getController();
                    tabligh_9arar.choose_تبليغ.setText("محضـر تبليــغ قـــرار حضـوري");

                    break;
                    case "محضر تبليغ حكــم إداري حضــوري":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_Idari_7odori/Mahder_tabligh_7okm_Idari_7odori.fxml"));

                    accueilStage.setTitle("محضر تبليغ حكــم إداري حضــوري");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Idari = fxmlLoader.getController();
                    tabligh_Idari.choose_تبليغ.setText("محضر تبليغ حكــم إداري حضــوري");

                    break;
                    case "تبليــغ حكــم إداري غيابي":

                    fxmlLoader.setLocation(getClass().getResource("/el_Mahadir/tablighe/Mahder_tabligh_7okm_Idari_7odori/Mahder_tabligh_7okm_Idari_7odori.fxml"));

                    accueilStage.setTitle("تبليــغ حكــم إداري غيابي");
                    stackContent.getChildren().clear();
                    stackContent.getChildren().add(fxmlLoader.load());
                    tabligh_Idari = fxmlLoader.getController();
                    tabligh_Idari.choose_تبليغ.setText("تبليــغ حكــم إداري غيابي");

                    break;
            }

            stage2.close();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_المحضر.getItems().addAll("التكليف بالحضور مع التسليم", "محضـر تبليــغ حكم حضـوري", "محضـر تبليــغ حكم غيابي", "محضـر تبليـغ حكم حضـوري إبتدائي نهائـي", "محضـر تبليـغ حكم غيابـي إبتـدائـي نهائـي", "محضـر تبليـغ حكم غيابـي نهائـي","محضـر تبليــغ أمــر  استعجالي","محضـر تبليــغ أمــر  استعجالي غيابي","محضر تبليــغ قـــرار غيـابـي","محضـر تبليــغ قـــرار حضـوري","محضر تبليغ حكــم إداري حضــوري","تبليــغ حكــم إداري غيابي");
    }

}
