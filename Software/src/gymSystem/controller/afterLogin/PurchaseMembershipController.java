package gymSystem.controller.afterLogin;


import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.Person;
import gymSystem.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * This controller class is used to control the process of purchasing membership
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class PurchaseMembershipController extends Controller implements Initializable {
    private int currentBalance;
    private int currentExpireDate;
    private Stage dialogStage;
    private boolean buttonClicked = false;
    private Alert warning = new Alert(Alert.AlertType.WARNING);
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the user to be edited in the dialog.
     *
     * @param user
     */
    public void setPerson(Person user) {
        Controller.user = user;
    }

    @FXML
    void handlePurchase1() {
        buttonClicked = true;
        currentBalance = Integer.parseInt(user.getBalance());
        currentExpireDate = Integer.parseInt(user.getExpireDate());
        if (currentBalance < 500){
            warning.setTitle("Sorry!");
            warning.setHeaderText("The balance is not sufficient!");
            warning.setContentText("Please recharge!");
            warning.showAndWait();
        }
        else {
            currentBalance -= 500;
            user.setBalance(Integer.toString(currentBalance));
            currentExpireDate += 30;
            user.setExpireDate(Integer.toString(currentExpireDate));
            user.setMembership("premier");

            Map<String, Object> object = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
            object.put(user.getUserID(),user);
            FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(), object);
            warning.setTitle("Congratulation!");
            warning.setHeaderText("Purchase membership Successfully!");
            warning.setContentText("Now you can enjoy the membership service!");
            warning.showAndWait();
        }

    }

    @FXML
    void handlePurchase2() {
        buttonClicked = true;
        currentBalance = Integer.parseInt(user.getBalance());
        currentExpireDate = Integer.parseInt(user.getExpireDate());
        if (currentBalance < 1200){
            warning.setTitle("Sorry!");
            warning.setHeaderText("The balance is not sufficient!");
            warning.setContentText("Please recharge!");
            warning.showAndWait();
        }
        else {
            currentBalance -= 1200;
            user.setBalance(Integer.toString(currentBalance));
            currentExpireDate += 90;
            user.setExpireDate(Integer.toString(currentExpireDate));
            user.setMembership("premier");
            Map<String, Object> object = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
            object.put(user.getUserID(),user);
            FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(), object);
            warning.setTitle("Congratulation!");
            warning.setHeaderText("Purchase membership Successfully!");
            warning.setContentText("Now you can enjoy the membership service!");
            warning.showAndWait();
        }

    }

    @FXML
    void handlePurchase3() {
        buttonClicked = true;
        currentBalance = Integer.parseInt(user.getBalance());
        currentExpireDate = Integer.parseInt(user.getExpireDate());
        if (currentBalance < 3000){
            warning.setTitle("Sorry!");
            warning.setHeaderText("The balance is not sufficient!");
            warning.setContentText("Please recharge!");
            warning.showAndWait();
        }
        else {
            currentBalance -= 3000;
            user.setBalance(Integer.toString(currentBalance));
            currentExpireDate += 365;
            user.setExpireDate(Integer.toString(currentExpireDate));
            user.setMembership("premier");
            Map<String, Object> object = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
            object.put(user.getUserID(),user);
            FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(), object);
            warning.setTitle("Congratulation!");
            warning.setHeaderText("Purchase membership Successfully!");
            warning.setContentText("Now you can enjoy the membership service!");
            warning.showAndWait();
        }

    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
