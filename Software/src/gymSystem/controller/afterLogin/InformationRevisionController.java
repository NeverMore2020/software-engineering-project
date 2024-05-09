package gymSystem.controller.afterLogin;

import gymSystem.entity.Person;
import gymSystem.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import gymSystem.controller.fileControl.FileController;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
/**
 * This class is used to see the information of users and let them modify their information like birthday except password and userID
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class InformationRevisionController extends Controller implements Initializable {
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField passWordField;
    @FXML
    private TextField userNameField;


    private Stage dialogStage;
    private boolean okClicked = false;
    private UserController personController;
    private Alert warning = new Alert(Alert.AlertType.WARNING);

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
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
        userNameField.setText(user.getUserName());
        phoneNumberField.setText(user.getTel());
        emailField.setText(user.getEmail());
        birthdayField.setText(user.getBirthday());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            user.setUserName(userNameField.getText());
            user.setTel(phoneNumberField.getText());
            user.setEmail(emailField.getText());
            user.setBirthday(birthdayField.getText());

            okClicked = true;

            String path = getClass().getResource("../../files/userInformation.json").getPath();
            Map<String,Object> object = FileController.parseFile(path);
            object.put(user.getUserID(),user);
            FileController.updateFile(path,object);
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (userNameField.getText() == null || userNameField.getText().length() == 0) {
            errorMessage = "No valid nickname!\n" ;
            warning.setTitle("warning!");
            warning.setHeaderText("Invalid Fields");
            warning.setContentText(errorMessage);
            warning.showAndWait();
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage = "No valid email!\n" ;
            warning.setTitle("warning!");
            warning.setHeaderText("Invalid Fields");
            warning.setContentText(errorMessage);
            warning.showAndWait();
        }

        if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) {
            errorMessage = "No phone number!\n" ;
            warning.setTitle("warning!");
            warning.setHeaderText("Invalid Fields");
            warning.setContentText(errorMessage);
            warning.showAndWait();
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage = "No valid birthday!\n" ;
            warning.setTitle("warning!");
            warning.setHeaderText("Invalid Fields");
            warning.setContentText(errorMessage);
            warning.showAndWait();
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            warning.setTitle("warning!");
            warning.setHeaderText("Invalid Fields");
            warning.setContentText("Please correct invalid fields");
            warning.showAndWait();
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
