package gymSystem.controller.afterLogin;/*2021/5/12*/

import gymSystem.controller.pageControl.PageController;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is to deal with the book request of users
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class BookRequestController extends LiveController implements Initializable {
    public TextArea userRequest;
    PageController pageController = new PageController();

    @Override
    public void setStage(Stage stage) {
        newStage = stage;
    }

    public void returnToBookingPage(MouseEvent mouseEvent) throws Exception {
        userRequirement = "";
        newStage.close();

        submitFlag = false;
        handleFlag = false;
    }

    public void confirmBooking(MouseEvent mouseEvent) {

        userRequirement = userRequest.getText();
        submitFlag = true;
        handleFlag = false;
        newStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
