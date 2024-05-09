package gymSystem.controller.pageControl;/*2021/5/13*/

import gymSystem.controller.beforeLogin.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * This class is to help change the stage or to start a new stage in the application
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class PageController {
    public static final Logger logger = Logger.getLogger(LoginController.class.getName());
    public List<Object> replaceStage(String fxml, Stage stage) throws Exception {
        List<Object> returnList = new ArrayList<>();
        //  private ViewAlter viewAlter;
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        try {
            stage = new Stage();
            Parent page = loader.load(inputStream);
            Scene scene = new Scene(page);
            if (fxml.equals("../../userInterface/checkRequirement.fxml")||fxml.equals("../../userInterface/bookRequest.fxml")){
                stage.initStyle(StageStyle.UNDECORATED);
            }
            stage.setScene(scene);
            stage.setTitle("My Request");
            stage.show();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "页面加载异常！");
        } finally {
            inputStream.close();

            returnList.add(stage);
            returnList.add(loader.getController());
        }
        return returnList;
    }

    //Jump to another page

    public Initializable replaceSceneContent(String fxml, Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream inputStream = getClass().getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        try {
            Parent page = loader.load(inputStream);
            double height = stage.getScene().getHeight();
            double width = stage.getScene().getWidth();
            Scene scene = new Scene(page, width, height);
            stage.setScene(scene);
            stage.sizeToScene();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "页面加载异常!!!");
        } finally {
            inputStream.close();
        }
        return loader.getController();
    }
}
