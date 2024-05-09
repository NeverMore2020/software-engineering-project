package gymSystem.controller.afterLogin;/*2021/4/3*/


import gymSystem.controller.beforeLogin.LoginController;
import gymSystem.controller.pageControl.PageController;
import gymSystem.entity.Manager;
import gymSystem.entity.Person;
import gymSystem.entity.Trainer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This controller is the parent class of main controllers. It defines the response of navigator.
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class Controller {
    PageController pageController = new PageController();
    public static Stage newStage;
    @FXML
    public Text title;

    @FXML
    public AnchorPane rootAnchor;
    @FXML
    public AnchorPane firstChildAnchor;
    @FXML
    public AnchorPane secondChildAnchor;
    @FXML
    public AnchorPane thirdChildAnchor;
    @FXML
    public AnchorPane fourthChildAnchor;
    @FXML
    public AnchorPane fifthChildAnchor;
    @FXML
    public AnchorPane sixthChildAnchor;
    @FXML
    public AnchorPane lastChildAnchor;

    double initialValue;
    boolean firstTime;
    @FXML
    public Text videoButtonText;
    @FXML
    public Text liveButtonText;
    @FXML
    public Text homeButtonText;
    @FXML
    public Text memberButtonText;

    @FXML
    public Text text1;
    @FXML
    public Text text2;
    @FXML
    public Text text3;


    @FXML
    public Text welcome;

    public static final Logger logger = Logger.getLogger(LoginController.class.getName());


    public static Stage currentStage;
    @FXML
    public AnchorPane videoButton;

    @FXML
    public AnchorPane liveButton;

    @FXML
    public AnchorPane memberButton;

    @FXML
    public AnchorPane homeButton;

    @FXML
    public ImageView users;

    @FXML
    public ImageView welcomeImage1;

    @FXML
    public ImageView welcomeImage2;

    @FXML
    public ImageView welcomeImage3;

    public String loginID;
    public String loginClassifier;

    public static Person user;
    public static Manager manager;


    ColorAdjust[] colorAdjust1 = new ColorAdjust[]{new ColorAdjust(), new ColorAdjust()};
    ColorAdjust[] colorAdjust2 = new ColorAdjust[]{new ColorAdjust(), new ColorAdjust()};
    ColorAdjust[] colorAdjust3 = new ColorAdjust[]{new ColorAdjust(), new ColorAdjust()};
    ColorAdjust[] colorAdjust4 = new ColorAdjust[]{new ColorAdjust(), new ColorAdjust()};

    public void closeApp(MouseEvent mouseEvent) {
        currentStage.close();
    }

    public void setUser(Person user) {
        Controller.user = user;
    }

    public void setStage(Stage stage) {
        currentStage = stage;
        Scene scene = stage.getScene();
    }

    public void onVideoButtonClick(MouseEvent mouseEvent) throws Exception {
        Controller videoController = (VideoController) replaceSceneContent("../../userInterface/video.fxml");
        Controller.currentStage.setWidth(900);
        videoController.setStage(currentStage);
        Controller.currentStage.setWidth(900);

    }

    public void onLiveButtonClick(MouseEvent event) throws IOException {
        try {
            Controller liveController;
            if (user instanceof Trainer) {
                liveController = (TrainerLiveController) replaceSceneContent("../../userInterface/liveForTrainer.fxml");
            } else if (user instanceof Manager) {
                liveController = (ManagerLiveController) replaceSceneContent("../../userInterface/liveForManager.fxml");
            } else {
                if (user.getMembership().equals("premier")) {
                    liveController = (LiveController) replaceSceneContent("../../userInterface/live.fxml");
                } else {
                    liveController = (LiveBlockController) replaceSceneContent("../../userInterface/liveBlock.fxml");
                }

            }

            liveController.setStage(currentStage);
            LiveController.currentStage.setWidth(1200);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void onMemberButtonClick(MouseEvent event) throws IOException {
        try {
            Controller membershipController;
            if (user instanceof Manager) {

                membershipController = (MembershipController) replaceSceneContent("../../userInterface/membership.fxml");
            } else {
                membershipController = (MembershipBlockController) replaceSceneContent("../../userInterface/membershiptest.fxml");
            }
            membershipController.setStage(currentStage);
            LiveController.currentStage.setWidth(900);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void onHomeButtonClick(MouseEvent event) throws IOException {
        try {
            WelcomeController welcomeController = (WelcomeController) replaceSceneContent("../../userInterface/welcome.fxml");
            welcomeController.setStage(currentStage);
            LiveController.currentStage.setWidth(900);

        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void onUserClick(MouseEvent event) throws Exception {
        List<Object> list;
        list = pageController.replaceStage("../../userInterface/PersonInformation.fxml", newStage);
        UserController userController = (UserController) list.get(1);
        newStage = (Stage) list.get(0);
        userController.setStage(newStage);
    }

    public void onVideoMouseEntered(MouseEvent mouseEvent) {
        onMouse1Entered(videoButton, videoButtonText, colorAdjust1);
    }

    public void onVideoMouseExited(MouseEvent mouseEvent) {
        onMouse1Exited(videoButton, colorAdjust1);
    }

    public void onLiveMouseEntered(MouseEvent mouseEvent) {
        onMouse1Entered(liveButton, liveButtonText, colorAdjust2);
    }

    public void onLiveMouseExited(MouseEvent mouseEvent) {
        onMouse1Exited(liveButton, colorAdjust2);
    }

    public void onHomeMouseEntered(MouseEvent mouseEvent) {
        onMouse1Entered(homeButton, homeButtonText, colorAdjust3);
    }

    public void onHomeMouseExited(MouseEvent mouseEvent) {
        onMouse1Exited(homeButton, colorAdjust3);
    }

    public void onMembershipMouseEntered(MouseEvent mouseEvent) {
        onMouse1Entered(memberButton, memberButtonText, colorAdjust4);
    }

    public void onMembershipMouseExited(MouseEvent mouseEvent) {
        onMouse1Exited(memberButton, colorAdjust4);
    }


    public void onMouseEntered(AnchorPane pane, Text text, ColorAdjust colorAdjust) {
        pane.setEffect(colorAdjust);
        text.setEffect(colorAdjust);
        Timeline fadeInTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), 1, Interpolator.LINEAR)
                ));
        fadeInTimeline.setCycleCount(1);
        fadeInTimeline.setAutoReverse(false);
        fadeInTimeline.play();
    }

    public void onMouseExited(AnchorPane text, ColorAdjust colorAdjust) {
        Timeline fadeOutTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
                ));
        fadeOutTimeline.setCycleCount(1);
        fadeOutTimeline.setAutoReverse(false);
        fadeOutTimeline.play();
    }


    //text and back change together
    public void onMouse1Entered(AnchorPane pane, Text text, ColorAdjust[] colorAdjust) {
        pane.setEffect(colorAdjust[0]);
        text.setEffect(colorAdjust[1]);
        if (!firstTime) {
            initialValue = colorAdjust[0].brightnessProperty().getValue();
        }
        firstTime = true;
        Timeline fadeInTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust[0].brightnessProperty(), colorAdjust[0].brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust[0].brightnessProperty(), -1, Interpolator.LINEAR)
                ));

        Timeline textInTimeline =
                new Timeline(
                        new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust[1].brightnessProperty(), colorAdjust[1].brightnessProperty().getValue(), Interpolator.LINEAR)),
                        new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust[1].brightnessProperty(), 1, Interpolator.LINEAR)));

        textInTimeline.setCycleCount(1);
        textInTimeline.setAutoReverse(false);
        textInTimeline.play();

        fadeInTimeline.setCycleCount(1);
        fadeInTimeline.setAutoReverse(false);
        fadeInTimeline.play();
    }

    public void onMouse1Exited(AnchorPane text, ColorAdjust[] colorAdjust) {
        Timeline fadeOutTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust[0].brightnessProperty(), colorAdjust[0].brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust[0].brightnessProperty(), initialValue, Interpolator.LINEAR)
                ));

        Timeline textOutTimeline =
                new Timeline(
                        new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust[1].brightnessProperty(), colorAdjust[1].brightnessProperty().getValue(), Interpolator.LINEAR)),
                        new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust[1].brightnessProperty(), 0, Interpolator.LINEAR)));

        textOutTimeline.setCycleCount(1);
        textOutTimeline.setAutoReverse(false);
        textOutTimeline.play();

        fadeOutTimeline.setCycleCount(1);
        fadeOutTimeline.setAutoReverse(false);
        fadeOutTimeline.play();
    }


    public void gotoVideo() {

    }

    Initializable replaceStage(String fxml) throws Exception {
        //  private ViewAlter viewAlter;
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        try {
            newStage = new Stage();
            Parent page = loader.load(inputStream);
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("My information");
            newStage.showAndWait();
        } catch (Exception ignored) {
;
        } finally {
            inputStream.close();
        }
        return loader.getController();
    }

    //Jump to another page

    public Initializable replaceSceneContent(String fxml) throws Exception {
        //  private ViewAlter viewAlter;
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
            Parent page = loader.load(inputStream);
            Scene scene = new Scene(page, 900, 600);
            currentStage.setScene(scene);
            currentStage.sizeToScene();
            inputStream.close();

        return loader.getController();
    }


}
