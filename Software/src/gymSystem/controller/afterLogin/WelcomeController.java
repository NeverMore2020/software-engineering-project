package gymSystem.controller.afterLogin;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the control class of home page 
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class WelcomeController extends Controller implements Initializable {


    private DoubleProperty fontSize1 = new SimpleDoubleProperty(12);
    private DoubleProperty fontSize2 = new SimpleDoubleProperty(12);
    private DoubleProperty fontSize3 = new SimpleDoubleProperty(12);

    @Override
    public void setStage(Stage stage) {
        currentStage = stage;

        Scene scene = stage.getScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
