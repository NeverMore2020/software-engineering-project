package gymSystem.controller.beforeLogin;

import com.alibaba.fastjson.JSONObject;
import gymSystem.controller.afterLogin.Controller;
import gymSystem.controller.afterLogin.WelcomeController;
import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.Manager;
import gymSystem.entity.Person;
import gymSystem.entity.Trainer;
import gymSystem.entity.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is used to control the process of login including click event of turning to other pages
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class LoginController extends MainController implements Initializable {

    public Hyperlink switchToTrainerLogin;

    public Text LoginFailedHint;
    public Text loginSucceedHint;
    public StackPane userLogin;
    public StackPane trainerLogin;
    public StackPane managerLogin;
    public Text userLoginText;
    public Text trainerLoginText;
    public Text managerLoginText;

    private Stage mainStage;

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button login_button;

    @FXML
    private TextField login_id;

    @FXML
    private TextField login_password;

    private String loginID;
    private String loginPassword;
    private String loginMembership;

    private Person user;


    /**
     * turn to sign up page
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onCreateClicked(MouseEvent mouseEvent) throws Exception {

            SignUpController signUpController = (SignUpController) replaceSceneContent("../../userInterface/signUp.fxml");
            signUpController.setStage(currentStage);

    }

    /**
     * This method checks if the account exists according to the input parameters
     * @param id the id of the account
     * @param password the password of the account
     * @param infoLocation the corresponding file location accourding to the userType
     * @param userType including ordinary user, trainer or manager
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public boolean ifUserExist(String id, String password, String infoLocation, Type userType) {
        Map<String, Object> form = FileController.parseFile(infoLocation);
        JSONObject userJson = (JSONObject) form.get(id);
        if (userJson == null) {
            return false;
        }
        user = JSONObject.parseObject(String.valueOf(userJson), userType);
        if(userType==Trainer.class){
            user.setExpireDate("N/A");
        }
        return user.getPassword().equals(password);
    }



    /**
     * This method deal with the event of login button click. If login succeed, then turn to welcome page. If not, give users hint and let them try again
     * @param loginType whether the account is user, trainer or manager
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void loginButtonClick(String loginType) {
        AtomicBoolean existFlag = new AtomicBoolean(false);
        LoginFailedHint.setVisible(false);
        loginSucceedHint.setVisible(false);
        login_button.setDisable(true);
        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        actionEvent -> {
                            loginID = login_id.getText();
                            loginPassword = login_password.getText();

                            boolean flag;
                            if (loginType.equals("user")){
                                flag = ifUserExist(loginID, loginPassword,getClass().getResource("../../files/userInformation.json").getPath(),User.class);
                            }
                            else if (loginType.equals("trainer"))
                            {
                                flag = ifUserExist(loginID, loginPassword,getClass().getResource("../../files/trainer.json").getPath(),Trainer.class);
                            }
                            else {
                                flag = ifUserExist(loginID, loginPassword,getClass().getResource("../../files/managerInfo.json").getPath(), Manager.class);
                            }
                            existFlag.set(flag);
                            if (existFlag.get()) {

                                loginSucceedHint.setVisible(true);
                                loginSucceedHint.setText("Login succeed! Please wait for a few seconds...");
                            } else {
                                login_button.setDisable(false);

                                LoginFailedHint.setVisible(true);
                                LoginFailedHint.setText("Login failed! Your ID or password is wrong!");

                            }

                        }), new KeyFrame(Duration.seconds(2), actionEvent -> {
            if (existFlag.get()) {
                login_button.setDisable(false);
                gotoWelcome(user);
            }
        }));
        animation.setCycleCount(1);
        animation.play();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_id.setText(SignUpController.thisUserId);
        login_button.setOnMouseClicked((event) -> loginButtonClick("user"));
        userLoginText.setStyle("-fx-fill: white");
        userLogin.setStyle("-fx-background-color: black");
    }

    public void setStage(Stage stage) {
        currentStage = stage;
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
    }

    /**
     * Go to welcome page if login successfully
     * @param user the corresponding account
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void gotoWelcome(Person user) {
        try {
            mainStage = new Stage();
            Controller controller = (WelcomeController) replaceStage("../../userInterface/welcome.fxml");
            currentStage.close();
            controller.setUser(user);
            controller.setStage(mainStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Initializable replaceStage(String fxml) throws Exception {
        //  private ViewAlter viewAlter;
        FXMLLoader loader = new FXMLLoader();
        try (InputStream inputStream = LoginController.class.getResourceAsStream(fxml)) {
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LoginController.class.getResource(fxml));

            Parent page = loader.load(inputStream);
            Scene scene = new Scene(page, 900, 600);
            mainStage.setScene(scene);
            //       mainStage.sizeToScene();
            mainStage.initStyle(StageStyle.TRANSPARENT);

            mainStage.setTitle("NO PAIN NO GAIN");
            mainStage.show();
        }
        return (Initializable) loader.getController();
    }

    /**
     * Go to changePassword to reset the password of the account
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onResetClicked(MouseEvent mouseEvent) throws Exception {
        ChangePasswordController changePasswordController = (ChangePasswordController) replaceSceneContent("../../userInterface/changePassword.fxml");
        changePasswordController.setStage(currentStage);
    }

    public void userLoginClicked(MouseEvent mouseEvent) {
        login_button.setOnMouseClicked((event) -> loginButtonClick("user"));
        userLoginText.setStyle("-fx-fill: white");
        userLogin.setStyle("-fx-background-color: black");
        trainerLoginText.setStyle("-fx-fill: black");
        trainerLogin.setStyle("-fx-background-color: white");
        managerLoginText.setStyle("-fx-fill: black");
        managerLogin.setStyle("-fx-background-color: white");
    }

    public void trainerLoginClicked(MouseEvent mouseEvent) {
        login_button.setOnMouseClicked((event) -> loginButtonClick("trainer"));
        trainerLoginText.setStyle("-fx-fill: white");
        trainerLogin.setStyle("-fx-background-color: black");
        userLoginText.setStyle("-fx-fill: black");
        userLogin.setStyle("-fx-background-color: white");
        managerLoginText.setStyle("-fx-fill: black");
        managerLogin.setStyle("-fx-background-color: white");
    }

    public void ManagerLoginClicked(MouseEvent mouseEvent) {
        login_button.setOnMouseClicked((event) -> loginButtonClick("manager"));
        managerLoginText.setStyle("-fx-fill: white");
        managerLogin.setStyle("-fx-background-color: black");
        trainerLoginText.setStyle("-fx-fill: black");
        trainerLogin.setStyle("-fx-background-color: white");
        userLoginText.setStyle("-fx-fill: black");
        userLogin.setStyle("-fx-background-color: white");
    }
}

