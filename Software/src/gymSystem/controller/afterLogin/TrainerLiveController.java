package gymSystem.controller.afterLogin;/*2021/5/4*/

import gymSystem.entity.BookInfo;
import gymSystem.entity.BookInfoForTrainer;
import gymSystem.entity.TimeSlot;
import gymSystem.entity.Trainer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrainerLiveController extends Controller implements Initializable {
    public AnchorPane bookingPane;
    public TableView<BookInfoForTrainer> myBookedTime;
    public TableColumn<BookInfoForTrainer, String> bookedDate;
    public TableColumn<BookInfoForTrainer, String> bookedTime;
    public TableColumn bookedTrainee;
    public TableColumn<BookInfoForTrainer, String> bookedUserName;
    public TableColumn<BookInfoForTrainer, String> bookedUserTel;
    public TableColumn<BookInfoForTrainer, String> bookedRequirement;
    static volatile boolean okFlag;
    static volatile boolean handleFlag;
    CheckRequirementController checkRequirementController;
    BookInfoForTrainer rowData;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @Override
    public void setStage(Stage stage){
        currentStage = stage;
        loadBookInfoTable();
    }

    public void checkRequest() throws Exception {
        rootAnchor.setDisable(true);
        List<Object> list;
        list =  pageController.replaceStage("../../userInterface/checkRequirement.fxml", newStage);
        checkRequirementController = (CheckRequirementController)list.get(1);
        newStage = (Stage) list.get(0);
        checkRequirementController.userRequire = rowData.getUserRequest();
        checkRequirementController.setStage(newStage);
        new Thread(() -> {
            while (true){

                if (okFlag){
                    rootAnchor.setDisable(false);
                    break;

                }
            }}).start();
    }

    public void loadBookInfoTable(){
        List<BookInfoForTrainer> bookInfoForTrainerList = user.getBookInfoForTrainerList();
        ObservableList<BookInfoForTrainer> myBookList = FXCollections.observableArrayList();
        bookedDate.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        bookedTime.setCellValueFactory(
                new PropertyValueFactory<>("time"));
        bookedUserName.setCellValueFactory(
                new PropertyValueFactory<>("userName"));
        bookedUserTel.setCellValueFactory(
                new PropertyValueFactory<>("userTel"));
        bookedRequirement.setCellValueFactory(
                new PropertyValueFactory<>("userRequest"));
        myBookList.addAll(user.getBookInfoForTrainerList());
        myBookedTime.setItems(myBookList);
        myBookedTime.setFixedCellSize(30);
        myBookedTime.setPrefHeight(240);
        myBookedTime.setRowFactory( tv -> {
            TableRow<BookInfoForTrainer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    rowData = row.getItem();
                    try {

                        checkRequest();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row ;
        });


    }
}
