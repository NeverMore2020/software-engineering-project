package gymSystem.entity;/*2021/5/4*/
/**
 * This class define the book information of users and used to present in trainers live sessions
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class BookInfoForTrainer {
    private String date;
    private String time;
    private String userName;
    private String userTel;
    private String userRequest;

    public BookInfoForTrainer(String date, String time, String userName, String userTel, String userRequest) {
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.userTel = userTel;
        this.userRequest = userRequest;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public BookInfoForTrainer() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "BookInfoForTrainer{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userRequest='" + userRequest + '\'' +
                '}';
    }
}
