package gymSystem.controller.beforeLogin;/*2021/4/9*/

import com.alibaba.fastjson.JSONObject;
import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.Manager;
import gymSystem.entity.Person;
import gymSystem.entity.Trainer;
import gymSystem.entity.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * Used by users to change their password
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 1.0.0
 */
public class ChangePasswordController extends MainController implements Initializable {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    private Person user = null;
    @FXML
    public TextField changePassword;
    @FXML
    public Button confirmChangePassword;
    @FXML
    public TextField changePasswordConfirm;
    @FXML
    public TextField changePasswordEmail;

    public TextField changePasswordID;
    public ImageView idCheck;
    public ImageView emailCheck;
    public ImageView pswCheck;
    public ImageView psw2Check;
    JSONObject userJson;
    private String userCategory;
    Image rightImage = new Image("gymSystem/images/right.jpeg",50,50,false,false);
    Image wrongImage = new Image("gymSystem/images/wrong.jpeg",50,50,false,false);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    /**
     *
     * This method is used to check if the person exist in the json file according to the ID
     * @param userId id of the person and
     * @param path the json file where we want to find the user
     * @return if the person exist
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public boolean isUserExist(String userId, String path) {
        Map<String, Object> userInfo = FileController.parseFile(path);
        userJson = (JSONObject) userInfo.get(userId);
        if (userInfo.get(userId) != null) {

            return true;
        }
        return false;
    }


    /**
     * This method is called when confirm button is clicked to call gotoSuccessfulPage function
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onChangePasswordConfirmClicked() {
        confirmChangePassword.setDisable(true);
        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        actionEvent -> {
                            confirmChangePassword.setDisable(false);
                            logger.log(Level.INFO, "输入用户名为：" + changePasswordID.getText());
                            logger.log(Level.INFO, "输入用户名为：" + changePasswordEmail.getText());
                            logger.log(Level.INFO, "输入密    码为：" + changePassword.getText());
                            logger.log(Level.INFO, "again输入密    码为：" + changePasswordConfirm.getText());
                            changePassword(changePasswordID.getText(),changePassword.getText());
                            gotoSuccessfulPage(changePasswordID.getText(),changePassword.getText());
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    /**
     * this method is used to turn to the successful page after changing the password
     * @param id the ID of the account having changed its password
     * @param psw the new password of the account having changed its password
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void gotoSuccessfulPage(String id, String psw) {
        try {
            ChangePswSucceedController changePswSucceedController = (ChangePswSucceedController) replaceSceneContent("../../userInterface/changePswSucceed.fxml");
            changePswSucceedController.setStage(currentStage);
            changePswSucceedController.showNameID(id,psw);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }


    /**
     * This method is used to change password of matched account
     * @param userId the Id of the account
     * @param password the password of the account
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    private void changePassword(String userId, String password) {
        Map<String, Object> form;
        if (userCategory.equals("user")) {
            form = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
            JSONObject userJson = (JSONObject) form.get(userId);

            User user = JSONObject.toJavaObject(userJson, User.class);
            user.setPassword(password);
            form.put(userId,user);
            FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(),form);
        }
        else if (userCategory.equals("trainer")){
            form = FileController.parseFile(getClass().getResource("../../files/trainer.json").getPath());
            JSONObject userJson = (JSONObject) form.get(userId);

            User user = JSONObject.toJavaObject(userJson, Trainer.class);
            user.setPassword(password);
            form.put(userId,user);
            FileController.updateFile(getClass().getResource("../../files/trainer.json").getPath(),form);
        }
        else{
            form = FileController.parseFile(getClass().getResource("../../files/managerInfo.json").getPath());
            JSONObject userJson = (JSONObject) form.get(userId);

            Manager user = JSONObject.toJavaObject(userJson, Manager.class);
            user.setPassword(password);
            form.put(userId,user);
            FileController.updateFile(getClass().getResource("../../files/managerInfo.json").getPath(),form);
        }
    }

    /**
     * This method is used to turn to login page
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onCancelClicked(MouseEvent mouseEvent) {
        try {
            LoginController loginController = (LoginController) replaceSceneContent("../../userInterface/login.fxml");
            loginController.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method listens to the key input event of users and judge whether the Id exists
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onIdInput(KeyEvent keyEvent) {
        String userId = changePasswordID.getText();

        if(isUserExist(userId,getClass().getResource("../../files/userInformation.json").getPath())){
            userCategory = "user";
            idCheck.setImage(rightImage);
            changePasswordEmail.setDisable(false);
            user = JSONObject.toJavaObject(userJson,User.class);

        }
        else if (isUserExist(userId,getClass().getResource("../../files/managerInfo.json").getPath())){
            userCategory = "manager";
            idCheck.setImage(rightImage);
            changePasswordEmail.setDisable(false);
            user = JSONObject.toJavaObject(userJson, Manager.class);
        }
        else if (isUserExist(userId,getClass().getResource("../../files/trainer.json").getPath())){
            userCategory = "trainer";
            idCheck.setImage(rightImage);
            changePasswordEmail.setDisable(false);
            user = JSONObject.toJavaObject(userJson, Trainer.class);
        }
        else {
            idCheck.setImage(wrongImage);
        }
    }


    /**
     * This method listens to the key input event of users and judge whether the password matches the regex rule
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onPswInput(KeyEvent keyEvent) {
        String password = changePassword.getText();
        if(password.matches("\\w{6,20}")){
            pswCheck.setImage(rightImage);
            changePasswordConfirm.setDisable(false);

        }
        else{
            pswCheck.setImage(wrongImage);

        }
    }


    /**
     * This method listens to the key input event of users and judge whether input of two passwords equal
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onPsw2Input(KeyEvent keyEvent) {
        String password = changePassword.getText();
        String passwordAgain = changePasswordConfirm.getText();
        if(password.equals(passwordAgain)){
            psw2Check.setImage(rightImage);
            confirmChangePassword.setDisable(false);
        }
        else {
            psw2Check.setImage(wrongImage);
        }
    }

    public void onEmailInput(KeyEvent keyEvent) {
        if (user.getEmail().equals(changePasswordEmail.getText())){
            emailCheck.setImage(rightImage);
            changePassword.setDisable(false);
        }
        else{
            emailCheck.setImage(wrongImage);
        }
    }
}
