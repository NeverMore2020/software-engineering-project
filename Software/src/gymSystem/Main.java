package gymSystem;

import gymSystem.controller.beforeLogin.LoginController;
import gymSystem.controller.fileControl.FileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used as start point of the whole program
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class Main extends Application {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    //    public StringBuilder stringBuilder = new StringBuilder();
    public Stage stage;
    //    public int num;
    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileController.updateTimeTable();
        stage = primaryStage;
        LoginController loginController = (LoginController) replaceSceneContent("userInterface/login.fxml");
        loginController.setStage(this.stage);
        stage.setTitle("Welcome to London Fitness Online GYM");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        try {
            Parent root =  loader.load(in);
            stage.setScene(new Scene(root));
            stage.sizeToScene();//Set the width and height of this Window to match the size of the content of this Window's Scene.
        } catch (Exception ignored) {

        } finally {
            in.close();
        }
        return loader.getController();
    }
}