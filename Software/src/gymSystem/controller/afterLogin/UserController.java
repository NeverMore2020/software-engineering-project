package gymSystem.controller.afterLogin;/*2021/4/3*/

import com.alibaba.fastjson.JSONObject;
import gymSystem.controller.beforeLogin.LoginController;
import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.Manager;
import gymSystem.entity.Trainer;
import gymSystem.entity.Person;
import gymSystem.entity.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//import org.junit.jupiter.params.ParameterizedTest;
/**
 * This class is the control class of user information page.
 * @version 1.0.0
 * @author Yitai Cheng, Yuanyuan Gao BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class UserController extends Controller implements Initializable {
    public Button rechargeButton;
    public Button membershipButton;
    //    public FileController file;
    @FXML
    private Label nickNameLabel;

    @FXML
    private Label passWordLabel;

    @FXML
    private Label telePhoneLabel;

    @FXML
    private Label membershipLabel;

    @FXML
    private Label expireDateLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label balanceLabel;

    public Map<String, Object> object;

    // Reference to the main application.
//    private Main mainApp;
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
    @Override
    public void setStage(Stage stage) {
        newStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (user instanceof Trainer|| user instanceof Manager){
            rechargeButton.setVisible(false);
            membershipButton.setVisible(false);
        }
        showPersonDetails(user);
    }

    /**
     * Show the information of the account
     * @param user the corresponding account
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void showPersonDetails(Person user) {
        nickNameLabel.setText(user.getUserName());
        telePhoneLabel.setText(user.getTel());
        emailLabel.setText(user.getEmail());
        expireDateLabel.setText(user.getExpireDate());
        membershipLabel.setText(user.getMembership());
        birthdayLabel.setText(user.getBirthday());
        balanceLabel.setText(user.getBalance());
    }


    /**
     * Called when the user clicks the edit button. Opens a dialog to edit details for the selected user.
     * @author Yuanyuan Gao BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    @FXML
    private void handleEditPerson() {
        boolean okClicked = showPersonEditDialog(user);
        if (okClicked) {
            showPersonDetails(user);
        }
    }

    public boolean showPersonEditDialog(Person user) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../userInterface/InformationRevision.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Modify Details");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(newStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the user into the controller.
            InformationRevisionController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void handleRecharge(){
        boolean buttonClicked = showRechargePage(user);
        if (buttonClicked) {
            showPersonDetails(user);
        }
    }

    @FXML
    private void handleMembership(){
        boolean purchaseClicked = showMembershipPage(user);
        if (purchaseClicked) {
            showPersonDetails(user);
        }
    }

    public boolean showRechargePage(Person user){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../userInterface/Recharge.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Recharge");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(newStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RechargeController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isButtonClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean showMembershipPage(Person user){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../userInterface/becomeMembership.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Purchase Membership");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(newStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PurchaseMembershipController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(user);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isButtonClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * By click the log out button, users can log out and be able to log in again or switch accounts
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void logOut(MouseEvent mouseEvent) throws Exception {
        currentStage.close();
        newStage.close();
        List<Object> list;
        list =  pageController.replaceStage("../../userInterface/login.fxml", currentStage);
        LoginController loginController = (LoginController)list.get(1);
        currentStage = (Stage) list.get(0);
        loginController.setStage(currentStage);
        currentStage.setTitle("Welcome to LONDON FITNESS Online GYM");
    }
}
