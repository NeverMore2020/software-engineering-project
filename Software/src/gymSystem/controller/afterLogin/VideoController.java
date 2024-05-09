package gymSystem.controller.afterLogin;/*2021/4/3*/

import gymSystem.controller.fileControl.FileController;
import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.geometry.Insets;

import java.util.logging.Level;

/**
 * This class is used to control the information displayed on Workout Video interface
 * @version 1.0.0
 * @author Yifan Zhang, Linfei Huang BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class VideoController extends Controller implements Initializable {
    public SplitPane pane1;
    public Label image1;
    public SplitPane pane2;
    public Label image2;
    public Label image3;
    @FXML
    private Button strength;
    @FXML
    private Button yoga;
    @FXML
    private Button hiit;

    @FXML
    private AnchorPane addVideo;


    BackgroundFill backgroundFill2 = new BackgroundFill(Paint.valueOf("#ffffff"), new CornerRadii(10), Insets.EMPTY);
    Background background2 = new Background(backgroundFill2);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onStrengthButtonClick(MouseEvent mouseEvent) throws Exception {

            StrengthController sc = (StrengthController) replaceSceneContent("../../userInterface/strengthV.fxml");

            sc.setStage(currentStage);

    }

    public void onHIITButtonClick(MouseEvent mouseEvent) throws Exception {

            HIITController hc = (HIITController) replaceSceneContent("../../userInterface/hiit.fxml");

            hc.setStage(currentStage);

    }

    public void onyogaButtonClick(MouseEvent mouseEvent) throws Exception {

            YogaController yc = (YogaController) replaceSceneContent("../../userInterface/yoga.fxml");

            yc.setStage(currentStage);
    }

    /**
     * This method is used to show all-level videos on the Workout Video interface according to given type.
     * @param type
     * @author Linfei Huang,Yifan Zhang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void showVideos(String type) {

        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        String[] IDinfo;
        String[] path = new String[10];
        String[] description = new String[10];
        Media[] Ms = new Media[10];
        MediaPlayer[] MPs = new MediaPlayer[10];
        MediaView[] MVs = new MediaView[10];
        Button[] Lbs = new Button[10];
        int count = 0;

        for (int i = 0; i < form.keySet().size(); i++) {
            IDinfo = form.keySet().toArray(new String[i]);

            if (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getStatus().equals("added") &&
                    FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getType().equals(type)) {
                path[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoLink());
                description[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoDescription());
                count++;
            }
        }

        for (int i = 0; i < count; i++) {

            path[i] = getClass().getResource("../../"+path[i]).getPath();

            String p = new File(path[i]).getAbsoluteFile().toURI().toString();
            Ms[i] = new Media(p);
            MPs[i] = new MediaPlayer(Ms[i]);
            MPs[i].setAutoPlay(false);
            MVs[i] = new MediaView(MPs[i]);
            if(count <= 4){
                MVs[i].setFitWidth(300);
                MVs[i].setFitHeight(250);
            }
            if(count > 4) {
                MVs[i].setFitWidth(200);
                MVs[i].setFitHeight(160);
            }

            Lbs[i] = new Button(description[i]);
            //Lbs[i].setAlignment(Pos.CENTER);
            Lbs[i].setBackground(background2);
            Lbs[i].setOnAction(e -> {
                PlayController pc = new PlayController(type);
//                currentStage.setWidth(1000);
//                currentStage.setHeight(800);
                pc.setStage(currentStage);
                try {
                    pc.start(currentStage, p);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        GridPane gridPane = new GridPane();

        if (count == 1) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(Lbs[0], 0, 1);
        }
        if (count == 2) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
        }
        if (count == 3) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
        }
        if (count == 4) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(MVs[3], 1, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
            gridPane.add(Lbs[3], 1, 3);
        }
        if(count == 5){
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 2, 0);
            gridPane.add(MVs[3], 0, 2);
            gridPane.add(MVs[4], 1, 2);


            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 2, 1);
            gridPane.add(Lbs[3], 0, 3);
            gridPane.add(Lbs[4], 1, 3);

        }
        if(count == 6){
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 2, 0);
            gridPane.add(MVs[3], 0, 2);
            gridPane.add(MVs[4], 1, 2);
            gridPane.add(MVs[5], 2, 2);


            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 2, 1);
            gridPane.add(Lbs[3], 0, 3);
            gridPane.add(Lbs[4], 1, 3);
            gridPane.add(Lbs[5], 2, 3);

        }
        if(count == 7){
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 2, 0);
            gridPane.add(MVs[3], 0, 2);
            gridPane.add(MVs[4], 1, 2);
            gridPane.add(MVs[5], 2, 2);
            gridPane.add(MVs[6], 0, 4);

            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 2, 1);
            gridPane.add(Lbs[3], 0, 3);
            gridPane.add(Lbs[4], 1, 3);
            gridPane.add(Lbs[5], 2, 3);
            gridPane.add(Lbs[6], 0, 5);
        }
        if(count == 8){
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 2, 0);
            gridPane.add(MVs[3], 0, 2);
            gridPane.add(MVs[4], 1, 2);
            gridPane.add(MVs[5], 2, 2);
            gridPane.add(MVs[6], 0, 4);
            gridPane.add(MVs[7], 1, 4);

            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 2, 1);
            gridPane.add(Lbs[3], 0, 3);
            gridPane.add(Lbs[4], 1, 3);
            gridPane.add(Lbs[5], 2, 3);
            gridPane.add(Lbs[6], 0, 5);
            gridPane.add(Lbs[7], 1, 5);
        }

        if(count == 9){
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 2, 0);
            gridPane.add(MVs[3], 0, 2);
            gridPane.add(MVs[4], 1, 2);
            gridPane.add(MVs[5], 2, 2);
            gridPane.add(MVs[6], 0, 4);
            gridPane.add(MVs[7], 1, 4);
            gridPane.add(MVs[8], 2, 4);

            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 2, 1);
            gridPane.add(Lbs[3], 0, 3);
            gridPane.add(Lbs[4], 1, 3);
            gridPane.add(Lbs[5], 2, 3);
            gridPane.add(Lbs[6], 0, 5);
            gridPane.add(Lbs[7], 1, 5);
            gridPane.add(Lbs[8], 2, 5);
        }

        addVideo.getChildren().clear();
        addVideo.getChildren().add(gridPane);
        gridPane.setLayoutX(75);
    }

    /**
     * This method is used to display videos of level 1 according to given type.
     * @param type
     * @author Yifan Zhang, Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void selectLevel1(String type) {
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        String[] IDinfo;
        String[] path = new String[10];
        String[] description = new String[10];
        Media[] Ms = new Media[10];
        MediaPlayer[] MPs = new MediaPlayer[10];
        MediaView[] MVs = new MediaView[10];
        Button[] Lbs = new Button[10];
        int count = 0;

        for (int i = 0; i < form.keySet().size(); i++) {
            IDinfo = form.keySet().toArray(new String[i]);

            if (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getStatus().equals("added") &&
                    FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getType().equals(type) &&
                    FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getLevel().equals("1")) {
                path[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoLink());
                description[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoDescription());
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            path[i] = getClass().getResource("../../"+path[i]).getPath();

            String p = new File(path[i]).getAbsoluteFile().toURI().toString();
            Ms[i] = new Media(p);
            MPs[i] = new MediaPlayer(Ms[i]);
            MPs[i].setAutoPlay(false);
            MVs[i] = new MediaView(MPs[i]);

                MVs[i].setFitWidth(300);
                MVs[i].setFitHeight(250);


            Lbs[i] = new Button(description[i]);
            Lbs[i].setBackground(background2);
            Lbs[i].setOnAction(e -> {
                PlayController pc = new PlayController(type);
//                currentStage.setWidth(1000);
//                currentStage.setHeight(800);
                pc.setStage(currentStage);
                try {
                    pc.start(currentStage, p);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        GridPane gridPane = new GridPane();
        if (count == 1) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(Lbs[0], 0, 1);
        }
        if (count == 2) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
        }
        if (count == 3) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
        }
        if (count == 4) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(MVs[3], 1, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
            gridPane.add(Lbs[3], 1, 3);
        }

        addVideo.getChildren().clear();
        addVideo.getChildren().add(gridPane);
        gridPane.setLayoutX(75);
    }

    /**
     * This method is used to display videos of level 2 according to given type.
     * @param type
     * @author Yifan Zhang, Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void selectLevel2(String type) {
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        String[] IDinfo;
        String[] path = new String[10];
        String[] description = new String[10];
        Media[] Ms = new Media[10];
        MediaPlayer[] MPs = new MediaPlayer[10];
        MediaView[] MVs = new MediaView[10];
        Button[] Lbs = new Button[10];
        int count = 0;

        for (int i = 0; i < form.keySet().size(); i++) {
            IDinfo = form.keySet().toArray(new String[i]);

            if (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getStatus().equals("added") &&
                    FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getType().equals(type) &&
                    FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getLevel().equals("2")) {
                path[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoLink());
                description[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoDescription());
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            path[i] = getClass().getResource("../../"+path[i]).getPath();

            String p = new File(path[i]).getAbsoluteFile().toURI().toString();
            Ms[i] = new Media(p);
            MPs[i] = new MediaPlayer(Ms[i]);
            MPs[i].setAutoPlay(false);
            MVs[i] = new MediaView(MPs[i]);
            MVs[i].setFitWidth(300);
            MVs[i].setFitHeight(250);
            Lbs[i] = new Button(description[i]);
            Lbs[i].setBackground(background2);
            Lbs[i].setOnAction(e -> {
                PlayController pc = new PlayController(type);
//                currentStage.setWidth(1000);
//                currentStage.setHeight(800);
                pc.setStage(currentStage);
                try {
                    pc.start(currentStage, p);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        GridPane gridPane = new GridPane();
        if (count == 1) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(Lbs[0], 0, 1);
        }
        if (count == 2) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
        }
        if (count == 3) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
        }
        if (count == 4) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(MVs[3], 1, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
            gridPane.add(Lbs[3], 1, 3);
        }
        addVideo.getChildren().clear();
        addVideo.getChildren().add(gridPane);
        gridPane.setLayoutX(75);
    }

    /**
     * This method is used to display videos of level 3 according to given type.
     * @param type
     * @author Yifan Zhang, Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void selectLevel3(String type) {
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        String[] IDinfo;
        String[] path = new String[10];
        String[] description = new String[10];
        Media[] Ms = new Media[10];
        MediaPlayer[] MPs = new MediaPlayer[10];
        MediaView[] MVs = new MediaView[10];
        Button[] Lbs = new Button[10];
        int count = 0;

        if (form != null) {
            for (int i = 0; i < form.keySet().size(); i++) {
                IDinfo = form.keySet().toArray(new String[i]);

                if (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getStatus().equals("added") &&
                        FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getType().equals(type) &&
                        FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getLevel().equals("3")) {
                    path[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoLink());
                    description[count] = (FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getVideoDescription());
                    count++;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            path[i] = getClass().getResource("../../"+path[i]).getPath();

            String p = new File(path[i]).getAbsoluteFile().toURI().toString();
            Ms[i] = new Media(p);
            MPs[i] = new MediaPlayer(Ms[i]);
            MPs[i].setAutoPlay(false);
            MVs[i] = new MediaView(MPs[i]);
            MVs[i].setFitWidth(300);
            MVs[i].setFitHeight(250);
            Lbs[i] = new Button(description[i]);

            Lbs[i].setBackground(background2);
            Lbs[i].setOnAction(e -> {
                PlayController pc = new PlayController(type);
//                currentStage.setWidth(1000);
//                currentStage.setHeight(800);
                pc.setStage(currentStage);
                try {
                    pc.start(currentStage, p);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        GridPane gridPane = new GridPane();
        if (count == 1) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(Lbs[0], 0, 1);
        }
        if (count == 2) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
        }
        if (count == 3) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
        }
        if (count == 4) {
            gridPane.add(MVs[0], 0, 0);
            gridPane.add(MVs[1], 1, 0);
            gridPane.add(MVs[2], 0, 2);
            gridPane.add(MVs[3], 1, 2);
            gridPane.add(Lbs[0], 0, 1);
            gridPane.add(Lbs[1], 1, 1);
            gridPane.add(Lbs[2], 0, 3);
            gridPane.add(Lbs[3], 1, 3);
        }
        addVideo.getChildren().clear();
        addVideo.getChildren().add(gridPane);
        gridPane.setLayoutX(75);
    }


}
