package gymSystem.controller.beforeLogin;/*2021/6/1*/

import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
/**
 * This class is used to show the successful page after changing the password of an account
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class ChangePswSucceedController extends MainController implements Initializable {
    public Text id;
    public Text psw;
    public Hyperlink turnToLogin;

    public void showNameID(String userId, String userPsw){
        id.setText(userId);
        psw.setText(userPsw);
    }

    //Return to login page
    public void loginButtonClick(MouseEvent mouseEvent) throws Exception {
            LoginController loginController = (LoginController) replaceSceneContent("../../userInterface/login.fxml");
            loginController.setStage(currentStage);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    // return to reset page
    public void onResetClicked(MouseEvent mouseEvent) throws Exception {
        ChangePasswordController changePasswordController = (ChangePasswordController) replaceSceneContent("../../userInterface/changePassword.fxml");
        changePasswordController.setStage(currentStage);
    }
}
