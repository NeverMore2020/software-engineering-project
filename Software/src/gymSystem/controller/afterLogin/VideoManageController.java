package gymSystem.controller.afterLogin;

import gymSystem.controller.fileControl.FileController;
import gymSystem.entity.User;
import gymSystem.entity.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
/**
 * This class is used to control the information displayed on VideoManagement interface
 * @version 1.0.0
 * @author Linfei Huang, Xiangrong Xing BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class VideoManageController extends Controller implements Initializable {
    @FXML
    private TableView<Video> a_videos;
    @FXML
    private TableColumn<Video, String> a_vID;
    @FXML
    private TableColumn<Video, String> a_type;
    @FXML
    private TableColumn<Video, String> a_level;
    @FXML
    private TableColumn<Video, String> a_description;
    @FXML
    private TableColumn<Video, String> a_status;


    @FXML
    private TableView<Video> w_videos;
    @FXML
    private TableColumn<Video, String> w_vID;
    @FXML
    private TableColumn<Video, String> w_type;
    @FXML
    private TableColumn<Video, String> w_level;
    @FXML
    private TableColumn<Video, String> w_description;
    @FXML
    private TableColumn<Video, String> w_status;

    @FXML
    private TextField content;
    @FXML
    private Button add;
    @FXML
    private Button remove;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        showList();
    }

    @Override
    public void setStage(Stage stage){
        currentStage = stage;
    }

    /**
     * This method is used to show information of videos on VideoManagement interface
     * @author Linfei Huang, Xiangrong Xing BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void showList() {
        ObservableList<Video> a_list = FXCollections.observableArrayList();
        ObservableList<Video> w_list = FXCollections.observableArrayList();


        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        String[] IDinfo;
        for(int i = 0; i<form.keySet().size() ;i++){
            IDinfo = form.keySet().toArray(new String[i]);

            if(FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]).getStatus().equals("added") ){
                a_list.add(FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]));
            }
            if(FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(),IDinfo[i]).getStatus().equals("await") ){
                w_list.add(FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(), IDinfo[i]));
            }
        }

        a_vID.setCellValueFactory(new PropertyValueFactory<Video, String>("videoID"));
        a_type.setCellValueFactory(new PropertyValueFactory<Video, String>("type"));
        a_level.setCellValueFactory(new PropertyValueFactory<Video, String>("level"));
        a_description.setCellValueFactory(new PropertyValueFactory<Video, String>("videoDescription"));
        a_status.setCellValueFactory(new PropertyValueFactory<Video, String>("status"));//映射

        a_videos.setItems(a_list); //tableview添加list

        w_vID.setCellValueFactory(new PropertyValueFactory<Video, String>("videoID"));
        w_type.setCellValueFactory(new PropertyValueFactory<Video, String>("type"));
        w_level.setCellValueFactory(new PropertyValueFactory<Video, String>("level"));
        w_description.setCellValueFactory(new PropertyValueFactory<Video, String>("videoDescription"));
        w_status.setCellValueFactory(new PropertyValueFactory<Video, String>("status"));//映射

        w_videos.setItems(w_list); //tableview添加list
    }


    public void VideoManageLinkClick(MouseEvent mouseEvent) throws IOException {
        try {
            VideoManageController vmc = (VideoManageController) replaceSceneContent("../../userInterface/videoManagement.fxml");
            vmc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void ManagerLinkClick(MouseEvent mouseEvent) throws IOException {
        try {
            MembershipController mc = (MembershipController) replaceSceneContent("../../userInterface/membership.fxml");
            mc.setStage(currentStage);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is used to add videos courses
     * @author Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void addButtonClick(){
        int check = 0;
        String ID = content.getText();


        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        for(int i = 0; i<form.keySet().size() ;i++){
            String[] IDinfo = form.keySet().toArray(new String[i]);
            if(ID.equals(IDinfo[i])){
                check = 1;
            }
        }

        if(check == 0) {
            System.out.println("Wrong input ID! Please Enter again.");
        }
        else{
            Video v1 = new Video();
            v1 = FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(),ID);
            if(v1.getStatus().equals("added")) {
            }
            else{
                v1.setStatus("added");
                FileController.writeVideoFile(getClass().getResource("../../files/video_information.json").getPath(), v1);

            }
            a_videos.getItems().clear();
            w_videos.getItems().clear();
            showList();
        }

    }

    /**
     * This method is used to remove added videos courses
     * @author Linfei Huang BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void removeButtonClick(){
        int check = 0;
        String ID = content.getText();


        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/video_information.json").getPath());
        for(int i = 0; i<form.keySet().size() ;i++){
            String[] IDinfo = form.keySet().toArray(new String[i]);
            if(ID.equals(IDinfo[i])){
                check = 1;
            }
        }

        if(check == 0) {
        }
        else{
            Video v1 = new Video();
            v1 = FileController.getVideoInfo(getClass().getResource("../../files/video_information.json").getPath(),ID);
            if(v1.getStatus().equals("added")) {
                v1.setStatus("await");
                FileController.writeVideoFile(getClass().getResource("../../files/video_information.json").getPath(), v1);
            }
            else{
            }
            a_videos.getItems().clear();
            w_videos.getItems().clear();
            showList();
        }
    }




}

