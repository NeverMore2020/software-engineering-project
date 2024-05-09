package gymSystem.entity;/*2021/4/23*/

import java.util.ArrayList;
import java.util.List;

/**
 * This class defined the fields and methods of trainers
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class Trainer extends User{
    private int seqNum;
    private List<TimeTable> timeTableList;
    private String description;

    public Trainer(String s, String s1, String s2, String s3, String s4, String s5, String s6, ArrayList<TimeTable> timeTables, int i) {

    }

    public List<TimeTable> getTimeTableList() {
        return timeTableList;
    }
    private List<BookInfoForTrainer> bookInfoForTrainerList;


    public List<BookInfoForTrainer> getBookInfoForTrainerList() {
        return bookInfoForTrainerList;
    }

    public void setBookInfoForTrainerList(List<BookInfoForTrainer> bookInfoForTrainerList) {
        this.bookInfoForTrainerList = bookInfoForTrainerList;
    }

    public void setTimeTableList(List<TimeTable> timeTableList) {
        this.timeTableList = timeTableList;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public Trainer(List<TimeTable> timeTableList, int seqNum) {
        this.timeTableList = timeTableList;
        this.seqNum = seqNum;
    }

    public Trainer() {
    }

    public Trainer(String userName, String password, String realName, String tel, String email, String userID, String membership, String expireDate, String balance, String firstTime, List<BookInfo> bookInfoList, int seqNum, List<TimeTable> timeTableList, String description, List<BookInfoForTrainer> bookInfoForTrainerList) {
        super(userName, password, realName, tel, email, userID, membership, expireDate, balance, firstTime, bookInfoList);
        this.seqNum = seqNum;
        this.timeTableList = timeTableList;
        this.description = description;
        this.bookInfoForTrainerList = bookInfoForTrainerList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Trainer(int seqNum, List<TimeTable> timeTableList, String description, List<BookInfoForTrainer> bookInfoForTrainerList) {
        this.seqNum = seqNum;
        this.timeTableList = timeTableList;
        this.description = description;
        this.bookInfoForTrainerList = bookInfoForTrainerList;
    }

    public Trainer(int seqNum, List<TimeTable> timeTableList, String description) {
        this.seqNum = seqNum;
        this.timeTableList = timeTableList;
        this.description = description;
    }




}
