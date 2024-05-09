package gymSystem.controller.afterLogin;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.geometry.Pos;

/**
 * This class is used to control media player
 * @version 1.0.0
 * @author Yifan Zhang,Linfei Huang BUPT&QMUL year3 student
 * @since 5/31/2021
 */

public class PlayController extends Controller {
    int n;
    public PlayController(String type) {
        if(type.equals("strength")){
            n = 1;
        }
        if(type.equals("HIIT")){
            n = 2;
        }
        if(type.equals("yoga")){
            n = 3;
        }

    }

    public void setStage(Stage stage){
        currentStage = stage;
    }

    public String DurationToString(Duration duration){
        int time = (int)duration.toSeconds();
        int hour = time /3600;
        int minute = (time-hour*3600)/60;
        int second = time %60;
        return minute + ":" + second;
    }

    /**
     * This method determines the layout of media player and play video according to given path.
     * @param primaryStage, path
     * @author Yifan Zhang,Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void start(Stage primaryStage, String path) throws Exception{

        Media media1 = new Media(path);
        MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
        mediaPlayer1.setAutoPlay(false);
        MediaView mediaView1 = new MediaView(mediaPlayer1);
        if(n == 1){
            mediaView1.setFitWidth(600);
            mediaView1.setFitHeight(400);
        }
        if(n == 2){
            mediaView1.setFitWidth(600);
            mediaView1.setFitHeight(400);
        }
        if(n == 3){
            mediaView1.setFitWidth(600);
            mediaView1.setFitHeight(400);
        }
        Slider processSlider=new Slider();
        Label processLabel=new Label();
        Button playButton=new Button("play");
        Button rePlayButton=new Button("replay");
        Label volume = new Label("      volume:");
        Slider volumeSlider=new Slider();
        Button goBack = new Button("return to video list");
        goBack.setOnAction(e->{
            mediaPlayer1.pause();
            VideoController vc = null;
            if (n == 1) {
                try {
                    StrengthController sc = null;
                    sc  = (StrengthController) replaceSceneContent("../../userInterface/strengthV.fxml");
                    currentStage.setWidth(900);
                    currentStage.setHeight(600);
                    sc.setStage(currentStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (n == 2) {
                try {
                    HIITController hc  = (HIITController) replaceSceneContent("../../userInterface/hiit.fxml");
                    currentStage.setWidth(900);
                    currentStage.setHeight(600);
                    hc.setStage(currentStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (n == 3) {
                try {
                    YogaController yc  = (YogaController) replaceSceneContent("../../userInterface/yoga.fxml");
                    currentStage.setWidth(900);
                    currentStage.setHeight(600);
                    yc.setStage(currentStage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });
        playButton.setOnAction(e->{
            String text=playButton.getText();
            if(text=="play"){
                playButton.setText("pause");
                mediaPlayer1.play();
            }
            else {
                playButton.setText("play");
                mediaPlayer1.pause();
            }
        });
        rePlayButton.setOnAction(e->{
            mediaPlayer1.seek(Duration.ZERO);
        });
        volumeSlider.setValue(30);
        mediaPlayer1.volumeProperty().bind(volumeSlider.valueProperty().divide(100));

        Duration totalDuration = mediaPlayer1.getTotalDuration();
        String totalString=DurationToString(totalDuration);
        double maxProcessSlider= processSlider.getMax();
        mediaPlayer1.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            processSlider.setValue(newValue.toSeconds() / totalDuration.toSeconds() * 100);
            processLabel.setText(DurationToString(newValue) + " " + DurationToString(mediaPlayer1.getTotalDuration()));
        });
        processSlider.valueProperty().addListener(observable -> {
            double totalTime=media1.getDuration().toMillis();
            double newTime=processSlider.valueProperty().getValue()/100*totalTime;
            mediaPlayer1.seek(Duration.millis(newTime));
        });
        BorderPane borderPane=new BorderPane();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(processSlider,processLabel,playButton,rePlayButton,volume,volumeSlider);
        HBox hbox1 = new HBox();
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(goBack);
        borderPane.setBottom(hbox);
        borderPane.setTop(hbox1);
        borderPane.setCenter(mediaView1);
        Scene scene = new Scene(borderPane,media1.getHeight(),media1.getWidth());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Workout video");
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        primaryStage.show();

    }


}
