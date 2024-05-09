package gymSystem.controller.beforeLogin;/*2021/4/10*/

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The father class of other classes under beforeLogin directory
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class MainController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    public static Stage currentStage;
    public void setStage(Stage stage){
        currentStage = stage;
    }
    Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = LoginController.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(LoginController.class.getResource(fxml));
        try {
            Parent root =  loader.load(in);
            currentStage.setScene(new Scene(root));
            currentStage.sizeToScene();//Set the width and height of this Window to match the size of the content of this Window's Scene.
        } catch (Exception e) {
            logger.log(Level.SEVERE, "页面加载异常！");
        } finally {
            in.close();
        }
        return loader.getController();
    }

}
