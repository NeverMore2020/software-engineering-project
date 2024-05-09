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
 * This controller class is used to control the process of recharge
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class RechargeController extends Controller implements Initializable {
    private int currentBalance;
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
    void handleRecharge1() {
        buttonClicked = true;
        currentBalance = Integer.parseInt(user.getBalance());
        if(user.getFirstTime().equals("true")){
            currentBalance += 150;
            user.setFirstTime("false");
        }
        else {
            currentBalance += 100;
        }
        user.setBalance(Integer.toString(currentBalance));

        Map<String,Object> object = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        object.put(user.getUserID(),user);
        FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(),object);
        warning.setTitle("Congratulation!");
        warning.setHeaderText("Recharge Successfully");
        warning.setContentText("Now you can pay for membership");
        warning.showAndWait();
    }

    @FXML
    void handleRecharge2() {
        buttonClicked = true;
        currentBalance = Integer.parseInt(user.getBalance());
        if(user.getFirstTime().equals("true")){
            currentBalance += 300;
            user.setFirstTime("false");
        }
        else {
            currentBalance += 200;
        }
        user.setBalance(Integer.toString(currentBalance));

        Map<String,Object> object = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        object.put(user.getUserID(),user);
        FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(),object);
        warning.setTitle("Congratulation!");
        warning.setHeaderText("Recharge Successfully");
        warning.setContentText("Now you can pay for membership");
        warning.showAndWait();

    }

    @FXML
    void handleRecharge3() {
        buttonClicked = true;
        currentBalance = Integer.parseInt(user.getBalance());
        if(user.getFirstTime().equals("true")){
            currentBalance += 750;
            user.setFirstTime("false");
        }
        else {
            currentBalance += 500;
        }
        user.setBalance(Integer.toString(currentBalance));

        Map<String,Object> object = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        object.put(user.getUserID(),user);
        FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(),object);
        warning.setTitle("Congratulation!");
        warning.setHeaderText("Recharge Successfully");
        warning.setContentText("Now you can pay for membership");
        warning.showAndWait();
    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
