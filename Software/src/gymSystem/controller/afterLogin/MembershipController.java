package gymSystem.controller.afterLogin;/*2021/4/5*/

import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * This class is used to control the information displayed on MembershipManagement interface
 * @version 1.0.0
 * @author Linfei Huang, Xiangrong Xing BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class MembershipController extends Controller implements Initializable {

    @FXML
    private TableView<User> ord_mem;
    @FXML
    private TableColumn<User, String> o_userID;
    @FXML
    private TableColumn<User, String> o_nickname;
    @FXML
    private TableColumn<User, String> o_password;
    @FXML
    private TableColumn<User, String> o_tel;
    @FXML
    private TableColumn<User, String> o_email;
    @FXML
    private TableColumn<User, String> o_birthday;
    @FXML
    private TableColumn<User, String> o_balance;

    @FXML
    private TableView<User> premi_mem;
    @FXML
    private TableColumn<User, String> p_userID;
    @FXML
    private TableColumn<User, String> p_nickname;
    @FXML
    private TableColumn<User, String> p_password;
    @FXML
    private TableColumn<User, String> p_tel;
    @FXML
    private TableColumn<User, String> p_email;
    @FXML
    private TableColumn<User, String> p_expiration;
    @FXML
    private TableColumn<User, String> p_birthday;
    @FXML
    private TableColumn<User, String> p_balance;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        showList();
    }

    @Override
    public void setStage(Stage stage){
        currentStage = stage;
    }
    /**
     * This method is used to show member information on MembershipManagement interface
     * @author Linfei Huang, Xiangrong Xing BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void showList() {
        ObservableList<User> list = FXCollections.observableArrayList();
        ObservableList<User> p_list = FXCollections.observableArrayList();
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        String[] IDinfo;
        for(int i = 0; i<form.keySet().size() ;i++){
            IDinfo = form.keySet().toArray(new String[i]);

            if(FileController.getInfo(getClass().getResource("../../files/userInformation.json").getPath(),IDinfo[i]).getMembership().equals("premier") ){
                p_list.add(FileController.getInfo(getClass().getResource("../../files/userInformation.json").getPath(), IDinfo[i]));
            }
            if(FileController.getInfo(getClass().getResource("../../files/userInformation.json").getPath(),IDinfo[i]).getMembership().equals("ordinary") ){
                list.add(FileController.getInfo(getClass().getResource("../../files/userInformation.json").getPath(), IDinfo[i]));
            }
        }
        o_userID.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));//映射
        o_nickname.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        o_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        o_tel.setCellValueFactory(new PropertyValueFactory<User, String>("tel"));
        o_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        o_birthday.setCellValueFactory(new PropertyValueFactory<User, String>("birthday"));
        o_balance.setCellValueFactory(new PropertyValueFactory<User, String>("balance"));
        ord_mem.setItems(list); //tableview add list
        p_userID.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));//映射
        p_nickname.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        p_password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        p_tel.setCellValueFactory(new PropertyValueFactory<User, String>("tel"));
        p_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        p_expiration.setCellValueFactory(new PropertyValueFactory<User, String>("expireDate"));
        p_birthday.setCellValueFactory(new PropertyValueFactory<User, String>("birthday"));
        p_balance.setCellValueFactory(new PropertyValueFactory<User, String>("balance"));
        premi_mem.setItems(p_list); //tableview add list
    }


    public void VideoManageLinkClick() throws IOException {
        try {
            VideoManageController vmc = (VideoManageController) replaceSceneContent("../../userInterface/videoManagement.fxml");
            vmc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void ManagerLinkClick() throws IOException {
        try {
            MembershipController mc = (MembershipController) replaceSceneContent("../../userInterface/membership.fxml");
            mc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }


}
