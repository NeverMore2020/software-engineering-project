package gymSystem.controller.afterLogin;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.geometry.Insets;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.stage.Stage;

/**
 * This class is used to control the display of strength video information
 *
 * @author Linfei Huang BUPT&QMUL year3 student
 * @version 1.0.0
 * @since 5/31/2021
 */
public class StrengthController extends VideoController implements Initializable {
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
    public void setStage(Stage stage) {
        currentStage = stage;
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showVideos("strength");
    }
    public void select1() {
        selectLevel1("strength");
    }
    public void select2() {
        selectLevel2("strength");
    }
    public void select3() {
        selectLevel3("strength");
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
            StrengthController sc = (StrengthController) replaceSceneContent("../../userInterface/strengthV.fxml");
            sc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
