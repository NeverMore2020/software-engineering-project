package gymSystem.controller.afterLogin;/*2021/4/3*/

import com.alibaba.fastjson.JSONObject;
import gymSystem.controller.fileControl.FileController;
import gymSystem.controller.pageControl.PageController;
import gymSystem.entity.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;

/**
 * This class controls live page. Users can select trainers and book available live sessions. They can cancel the booked sessions if they want.
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class LiveController extends Controller implements Initializable {
    public ListView<String> timeList;
    public Polygon nextButton;
    public AnchorPane bookingPane;
    public Text pageSign;
    public int currentPageNum = 1;
    public int currentTableNum = 0;
    public Text realNameText;
    public Text introductionText;
    public Text dateText;
    public Text nextDayText;
    public Text lastDayText;
    public AnchorPane myBookingTable;
    public Button bookButton;
    public Text bookingInfo;
    @FXML
    public TableColumn<TimeSlot, Boolean> tableFlag;
    @FXML
    public TableColumn<TimeSlot, String> tableDuration;
    @FXML
    public TableView<TimeSlot> timeTable;

    public TableView<BookInfo> myBookedTime;
    public TableColumn<BookInfo, String> bookedDate;
    public TableColumn<BookInfo, String> bookedTime;
    public TableColumn<BookInfo, String> bookedTrainer;
    public Button deleteButton;
    private int trainerSeq = 1;
    private String trainerID;

    public int totalPageNum = 3;
    public Polygon formerButton;
    public AnchorPane trainerInfoBoard;
    public Polygon nextDayButton;
    public Polygon formerDayButton;

    Map<String, Object> trainerMap;
    TimeSlot selectedTimeSlot;

    List<TimeTable> timeTableList;//todo
    TimeTable timeTable1;
    List<TimeSlot> timeSlotList;
    BookInfo bookInfo;


    Trainer trainer;

    static volatile boolean submitFlag;
    static volatile boolean handleFlag;
    @FXML
    static String userRequirement;

    @Override
    public void setStage(Stage stage) {
        currentStage = stage;

    }
    /**
     * Load the timetable of trainers and the booking table of this user.
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (currentPageNum == 1) {
            nextButton.setVisible(true);
            formerButton.setVisible(false);
        }
        if (currentTableNum == 0) {
            nextDayButton.setVisible(true);
            nextDayText.setVisible(true);
            formerDayButton.setVisible(false);
            lastDayText.setVisible(false);

        }
        loadTrainerInfo(trainerSeq);
        changeTimetable(currentTableNum);
        timeTable.setFixedCellSize(30);
        timeTable.setPrefHeight(240);
        loadBookInfoTable();
        myBookedTime.setFixedCellSize(30);
        myBookedTime.setPrefHeight(240);


        //listen book event
        timeTable.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldItem, newItem) -> {
                    if (newItem != null) {
                        selectedTimeSlot = newItem;
                        if (newItem.isBookingFlag()) {
                            bookButton.setDisable(false);
                        } else {
                            bookButton.setDisable(true);
                        }
                    }
                }
        );
        //listen delete event
        myBookedTime.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldItem, newItem) -> {
                    if (newItem != null) {
                        bookInfo = newItem;
                        deleteButton.setDisable(false);
                    }
                }
        );


        setDateForTable();
    }

    public void setDateForTable() {
        dateText.setText(getDate());
    }

    public String getDate() {
        Date d = new Date(new Date().getTime() + (long) currentTableNum * 24 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    /**
     * load the information of the trainer
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void loadTrainerInfo(int trainerSeq) {
        trainerMap = FileController.parseFile(getClass().getResource("../../files/trainer.json").getPath());
        for (Map.Entry<String, Object> entry : trainerMap.entrySet()) {
            trainer = JSONObject.parseObject(entry.getValue().toString(), Trainer.class);
            if (trainer.getSeqNum() == trainerSeq) {
                realNameText.setText(trainer.getRealName());
                introductionText.setText(trainer.getDescription());
                trainerID = trainer.getUserID();
                break;
            }
        }

    }

    /**
     * Load the book info table of this user
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void loadBookInfoTable() {
        deleteButton.setDisable(true);
        ObservableList<BookInfo> myBookList = FXCollections.observableArrayList();
        bookedDate.setCellValueFactory(
                new PropertyValueFactory<BookInfo, String>("date"));
        bookedTime.setCellValueFactory(
                new PropertyValueFactory<>("time"));
        bookedTrainer.setCellValueFactory(
                new PropertyValueFactory<>("trainer"));
        myBookList.addAll(user.getBookInfoList());
        myBookedTime.setItems(myBookList);

    }
    /**
     * change the timetable of trainer if user choose different trainers or select different dates
     * @param tableNum
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void changeTimetable(int tableNum) {
        loadTrainerInfo(trainerSeq);
        bookButton.setDisable(true);
        timeList.getItems().clear();
        timeTable.getItems().clear();
        timeTableList = trainer.getTimeTableList();
        timeTable1 = timeTableList.get(tableNum);
        timeSlotList = timeTable1.getTimeSlotList();
        ObservableList<TimeSlot> obList = FXCollections.observableArrayList();
        tableFlag.setCellValueFactory(
                new PropertyValueFactory<TimeSlot, Boolean>("bookingFlag"));
        tableDuration.setCellValueFactory(
                new PropertyValueFactory<>("duration"));
        for (TimeSlot timeSlot : timeSlotList) {
            String duration = timeSlot.getDuration();
            String flag;
            if (timeSlot.isBookingFlag()) {
                flag = "available";
            } else {
                flag = "Not available";
            }
            timeList.getItems().add(duration + flag);
            obList.add(timeSlot);
        }
        timeTable.setItems(obList);
        timeTable.setEditable(true);


    }


    public void NextButtonClicked(MouseEvent mouseEvent) {
        trainerSeq++;
        currentTableNum = 0;
        dateText.setText(getDate());
        formerDayButton.setVisible(false);
        lastDayText.setVisible(false);
        nextDayButton.setVisible(true);
        nextDayText.setVisible(true);
        loadTrainerInfo(trainerSeq);
        changeTimetable(currentTableNum);
        switch (currentPageNum) {
            case 1 -> {
                pageSign.setText("2/3");
                formerButton.setVisible(true);
                currentPageNum++;
            }
            case 2 -> {
                pageSign.setText("3/3");
                currentPageNum++;
                nextButton.setVisible(false);
            }
        }


    }

    public void formerButtonClicked(MouseEvent mouseEvent) {
        trainerSeq--;
        currentTableNum = 0;
        dateText.setText(getDate());
        formerDayButton.setVisible(false);
        lastDayText.setVisible(false);
        nextDayButton.setVisible(true);
        nextDayText.setVisible(true);
        loadTrainerInfo(trainerSeq);
        changeTimetable(currentTableNum);
        switch (currentPageNum) {
            case 2 -> {
                pageSign.setText("1/3");
                formerButton.setVisible(false);
                currentPageNum--;
            }
            case 3 -> {
                pageSign.setText("2/3");
                currentPageNum--;
                nextButton.setVisible(true);
            }
        }
    }

    public void goToNextDay(MouseEvent mouseEvent) {
        switch (currentTableNum) {
            case 0 -> {
                currentTableNum++;
                changeTimetable(currentTableNum);
                formerDayButton.setVisible(true);
                lastDayText.setVisible(true);
            }
            case 1 -> {
                currentTableNum++;
                changeTimetable(currentTableNum);
                nextDayButton.setVisible(false);
                nextDayText.setVisible(false);
            }
        }
        dateText.setText(getDate());

    }

    public void goToFormerDay(MouseEvent mouseEvent) {
        switch (currentTableNum) {
            case 1 -> {
                currentTableNum--;
                changeTimetable(currentTableNum);
                formerDayButton.setVisible(false);
                lastDayText.setVisible(false);
            }
            case 2 -> {
                currentTableNum--;
                changeTimetable(currentTableNum);
                nextDayButton.setVisible(true);
                nextDayText.setVisible(true);
            }
        }
        dateText.setText(getDate());
    }


    /**
     * Deal with the click event of book button. It offers a dialog window to let users input their requirement.
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onBookButtonClicked(MouseEvent mouseEvent) throws Exception {
        rootAnchor.setDisable(true);
        List<Object> list;
        list = pageController.replaceStage("../../userInterface/bookRequest.fxml", newStage);
        Controller bookRequestController = (BookRequestController) list.get(1);
        newStage = (Stage) list.get(0);
        handleFlag = true;
        bookRequestController.setStage(newStage);
        new Thread(() -> {
            while (true) {
                if (!handleFlag) {
                    if (submitFlag) {
                        Platform.runLater(this::handleUpdate);
                    }
                    rootAnchor.setDisable(false);
                    break;
                }
            }
        }).start();

    }

    /**
     * This method is used to update tables in this page after booking operations are set.
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void handleUpdate() {
        selectedTimeSlot.setBookingFlag(false);
        for (TimeSlot timeSlot : timeSlotList) {
            if (timeSlot.getDuration().equals(selectedTimeSlot.getDuration())) {
                timeSlot.setBookingFlag(selectedTimeSlot.isBookingFlag());
            }
        }
        trainerMap.put(trainerID, trainer);
        FileController.updateFile(getClass().getResource("../../files/trainer.json").getPath(), trainerMap);
        changeTimetable(currentTableNum);
        updateMyBookingInfo();
        updateTrainerBookingInfo();
    }

    public void updateTrainerBookingInfo() {
        trainer.getBookInfoForTrainerList().add(new BookInfoForTrainer(dateText.getText(), selectedTimeSlot.getDuration(), user.getUserName(), user.getTel(), userRequirement));
        trainerMap.put(trainerID, trainer);
        FileController.updateFile(getClass().getResource("../../files/trainer.json").getPath(), trainerMap);
    }

    public void updateMyBookingInfo() {
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        user.getBookInfoList().add(new BookInfo(trainer.getRealName(), dateText.getText(), selectedTimeSlot.getDuration()));
        form.put(user.getUserID(), user);
        FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(), form);
        loadBookInfoTable();
    }

    /**
     * Users can delete their booked sessions by clicking the delete button
     * @author Yitai Cheng BUPT&QMUL year3 student
     * @since 5/31/2021
     */
    public void onDeleteButtonClicked(MouseEvent mouseEvent) throws ParseException {
        Trainer selectedTrainer = null;
        String trainerName = bookInfo.getTrainer();
        String tobeDeletedTime = bookInfo.getTime();
        String tobeDeletedDate = bookInfo.getDate();
        BookInfoForTrainer tobeDeletedBookInfoForTrainer = null;
        for (Map.Entry<String, Object> entry : trainerMap.entrySet()) {
            if (entry.getValue() instanceof Trainer) {
                selectedTrainer = (Trainer) entry.getValue();
            } else {
                selectedTrainer = JSONObject.parseObject(entry.getValue().toString(), Trainer.class);
            }
            if (selectedTrainer.getRealName().equals(trainerName)) {
                break;
            }
        }
        assert selectedTrainer != null;
        List<TimeTable> selectedTimeTableList = selectedTrainer.getTimeTableList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bookedDate = sdf.parse(tobeDeletedDate);
        Date curDate = sdf.parse(sdf.format(new Date().getTime()));
        long days = (bookedDate.getTime() - curDate.getTime()) / (1000 * 60 * 60 * 24);
        TimeTable selectedTimeTable = selectedTimeTableList.get((int) days);
        List<TimeSlot> selectedTimeSlotList = selectedTimeTable.getTimeSlotList();
        for (TimeSlot timeSlot : selectedTimeSlotList) {
            String duration = timeSlot.getDuration();

            if (duration.equals(tobeDeletedTime)) {
                timeSlot.setBookingFlag(true);
            }

        }
        List<BookInfoForTrainer> bookInfoForTrainerList = selectedTrainer.getBookInfoForTrainerList();
        for (BookInfoForTrainer bookInfoForTrainer : bookInfoForTrainerList) {
            String duration = bookInfoForTrainer.getTime();
            if (duration.equals(tobeDeletedTime)) {
                tobeDeletedBookInfoForTrainer = bookInfoForTrainer;
                break;
            }
        }
        bookInfoForTrainerList.remove(tobeDeletedBookInfoForTrainer);
        trainerMap.put(selectedTrainer.getUserID(), selectedTrainer);
        FileController.updateFile(getClass().getResource("../../files/trainer.json").getPath(), trainerMap);
        //update right table
        Map<String, Object> form = FileController.parseFile(getClass().getResource("../../files/userInformation.json").getPath());
        user.getBookInfoList().remove(bookInfo);
        form.put(user.getUserID(), user);
        FileController.updateFile(getClass().getResource("../../files/userInformation.json").getPath(), form);
        loadBookInfoTable();
        //update left table
        changeTimetable(currentTableNum);


    }
}
