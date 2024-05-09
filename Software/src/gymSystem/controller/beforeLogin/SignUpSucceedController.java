package gymSystem.controller.beforeLogin;/*2021/4/5*/

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is just to give user a feedback that his or her account has been signed up successfully and they can choose to login or sign another one account.
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class SignUpSucceedController extends MainController implements Initializable {

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    public Text username;
    public Text id;

    public void showNameID(String name, String userID){
        username.setText(name);
        id.setText(userID);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //turn to reset page
    public void onResetClicked(MouseEvent mouseEvent) throws Exception {
        ChangePasswordController changePasswordController = (ChangePasswordController) replaceSceneContent("../../userInterface/changePassword.fxml");
        changePasswordController.setStage(currentStage);
    }
    //Return to login page
    public void loginButtonClick(MouseEvent mouseEvent) {
        try {
            LoginController loginController = (LoginController) replaceSceneContent("../../userInterface/login.fxml");
            loginController.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void onCreateMoreAccountCLicked(MouseEvent mouseEvent) {
        try {
            SignUpController signUpController = (SignUpController) replaceSceneContent("../../userInterface/signUp.fxml");
            signUpController.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
