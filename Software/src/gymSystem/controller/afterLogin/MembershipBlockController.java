package gymSystem.controller.afterLogin;/*2021/4/20*/

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * This class is used to prevent users who are not managers to see the management page
 * @version 1.0.0
 * @author Linfei Huang, Xiangrong Xing BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class MembershipBlockController  extends Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    @Override
    public void setStage(Stage stage){
        currentStage = stage;
    }
}
