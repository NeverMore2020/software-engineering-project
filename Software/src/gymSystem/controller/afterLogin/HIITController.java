package gymSystem.controller.afterLogin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * This class is used to control the display of HIIT video information
 * @version 1.0.0
 * @author Linfei Huang BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class HIITController extends VideoController implements Initializable{

    public Pane buttonPane;
    @FXML
    private AnchorPane addVideo;

    @FXML
    public Button level1;
    @FXML
    public Button level2;
    @FXML
    public Button level3;


    private GridPane gridPane;

    BackgroundFill backgroundFill2 = new BackgroundFill(Paint.valueOf("#ffffff"), new CornerRadii(10), Insets.EMPTY);
    Background background2 = new Background(backgroundFill2);

    public void setStage(Stage stage){
        currentStage = stage;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        showVideos("HIIT");
    }

    public void select1() {
        selectLevel1("HIIT");
    }


    public void select2() {
        selectLevel2("HIIT");
    }

    public void select3() {
        selectLevel3("HIIT");
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        try {
            VideoController vc = (VideoController) replaceSceneContent("../../userInterface/video.fxml");

            vc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        try {
            HIITController hc = (HIITController) replaceSceneContent("../../userInterface/hiit.fxml");

            hc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
