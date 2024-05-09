package gymSystem.controller.afterLogin;/*2021/5/13*/

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * This class is used to help trainers see the requirement of live sessions written by users who booked this session
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class CheckRequirementController extends TrainerLiveController implements Initializable {
    public Text userRequirement;

    public String userRequire;

    @Override
    public void setStage(Stage stage) {
        newStage = stage;
        userRequirement.setText(userRequire);
    }
    public void onOkClicked(MouseEvent mouseEvent) {
        okFlag = true;
        newStage.close();
    }

}
