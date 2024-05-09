package gymSystem.controller.beforeLogin;/*2021/4/5*/

import com.google.gson.Gson;
import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Class is used to let user sign up an account
 *
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @version 1.0.0
 * @since 5/31/2021
 */
public class SignUpController extends MainController implements Initializable {

    public ImageView userNameCheck;
    public ImageView passwordCheck;
    public ImageView realNameCheck;
    public ImageView emailCheck;
    public ImageView telCheck;
    static String thisUserId;
    static String thisUserName;

    StringBuilder stringBuilder = new StringBuilder();
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    @FXML
    private Button signUpButton;
    @FXML
    private Hyperlink cancelLink;
    @FXML
    private TextField signUpUserName;
    @FXML
    private TextField signUpPassword;
    @FXML
    private TextField signUpTel;
    @FXML
    private TextField signUpEmail;
    @FXML
    private TextField signUpRealName;
    Image rightImage = new Image("gymSystem/images/right.jpeg", 30, 30, false, false);
    Image wrongImage = new Image("gymSystem/images/wrong.jpeg", 30, 30, false, false);


    private final boolean[] submitSwitch = {false, false, false, false, false};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStage(Stage stage) {
        currentStage = stage;
    }

    /**
     * Turn to login page
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onCancelClicked() {
        try {
            LoginController loginController = (LoginController) replaceSceneContent("../../userInterface/login.fxml");
            loginController.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sign up an account after clicking the sign up button
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onSignUpButtonClicked(MouseEvent mouseEvent) {
        //读文件
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        Gson gson = new Gson();
        String userName = signUpUserName.getText();
        String password = signUpPassword.getText();
        String tel = signUpTel.getText();
        String email = signUpEmail.getText();
        String realName = signUpRealName.getText();
        thisUserName = userName;
        //give the user an random user ID from 0 - 99.
        String userId;
        do {
            userId = String.valueOf((int) (100 * Math.random()));
        } while (form.get(userId) != null);
        thisUserId = userId;
        User user = new User(userName, password, realName, tel, email, userId, "ordinary");
        form.put(thisUserId, user);
        FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(), form);
        signUpButton.setDisable(true);
        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        actionEvent -> {
                            signUpButton.setDisable(false);
                            logger.log(Level.INFO, "输入用户名为：" + userName);
                            logger.log(Level.INFO, "输入密    码为：" + password);
                            gotoSignUpSucceed(thisUserName, thisUserId);
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    /**
     * Go to page after signing up successfully
     * @param username the user name of the new account
     * @param id the id of the new account
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void gotoSignUpSucceed(String username, String id) {
        try {
            SignUpSucceedController signUpSucceedController = (SignUpSucceedController) replaceSceneContent("../../userInterface/signUpSucceed.fxml");
            signUpSucceedController.setStage(currentStage);
            signUpSucceedController.showNameID(username, id);


        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkInput(String name, String rule) {
        if (name.matches(rule)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method helps to set the sign up button. If one of the text fields are filled in with wrong format of words, then set the sign up button unable to be clicked
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void setSubmitSwitch() {
        for (boolean flag : submitSwitch) {
            if (!flag) {
                return;
            }
        }
        signUpButton.setDisable(false);
    }


    //check if input is illegal
    public void onUsernameInput(KeyEvent keyEvent) throws Exception {
        String userName = signUpUserName.getText();
        if (checkInput(userName, "\\w{1,20}")) {
            userNameCheck.setImage(rightImage);
            submitSwitch[0] = true;
            setSubmitSwitch();
        } else {
            userNameCheck.setImage(wrongImage);
            submitSwitch[0] = false;
        }

    }

    public void onPasswordInput(KeyEvent keyEvent) {
        String password = signUpPassword.getText();
        if (checkInput(password, "\\w{6,20}")) {
            passwordCheck.setImage(rightImage);
            submitSwitch[1] = true;
            setSubmitSwitch();
        } else {
            passwordCheck.setImage(wrongImage);
            submitSwitch[1] = false;
        }
    }

    public void onTelInput(KeyEvent keyEvent) {
        String tel = signUpTel.getText();
        if (checkInput(tel, "^1\\d{10}")) {
            telCheck.setImage(rightImage);
            submitSwitch[2] = true;
            setSubmitSwitch();
        } else {
            telCheck.setImage(wrongImage);
            submitSwitch[2] = false;
        }
    }

    public void onEmailInput(KeyEvent keyEvent) {
        String email = signUpEmail.getText();
        if (checkInput(email, "[0-9a-zA-Z]{1,20}@[0-9a-zA-Z.]{6,20}")) {
            emailCheck.setImage(rightImage);
            submitSwitch[3] = true;
            setSubmitSwitch();
        } else {
            emailCheck.setImage(wrongImage);
            submitSwitch[3] = false;
        }
    }

    public void onRealNameInput(KeyEvent keyEvent) {
        String realName = signUpRealName.getText();
        if (checkInput(realName, "[A-Z][a-z]{1,20}\\s[A-Z][a-z]{1,20}")) {
            realNameCheck.setImage(rightImage);
            submitSwitch[4] = true;
            setSubmitSwitch();
        } else {
            realNameCheck.setImage(wrongImage);
            submitSwitch[4] = false;
        }
    }
}

