package gymSystem.entity;/*2021/4/5*/


import java.util.ArrayList;
import java.util.List;

/**
 * This entity class defined the fields and methods of a user
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class User extends Person{
    private String expireDate;
    private String balance;
    private String firstTime;

    private List<BookInfo> bookInfoList;


    public User(String userName, String password, String realName, String tel, String email, String userID, String membership, String expireDate, String balance, String firstTime, List<BookInfo> bookInfoList) {
        super(userName, password, realName, tel, email, userID, membership);
        this.expireDate = expireDate;
        setManager(false);
        this.balance = balance;
        this.firstTime = firstTime;
        this.bookInfoList = bookInfoList;
    }

    public User() {

    }

    public User(String userName, String password, String realName, String tel, String email, String userId, String membership) {
        super(userName, password, realName, tel, email, userId, membership);
        this.expireDate = "0";
        setManager(false);
        this.balance = "0";
        this.firstTime = "true";
        this.bookInfoList = new ArrayList<>();
    }


    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public List<BookInfo> getBookInfoList() {
        return bookInfoList;
    }

    public void setBookInfoList(List<BookInfo> bookInfoList) {
        this.bookInfoList = bookInfoList;
    }

    public List<BookInfoForTrainer> getBookInfoForTrainerList() {
        return null;
    }


}

