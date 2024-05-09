package gymSystem.entity;


import java.util.List;

/**
 * Father class of User and Manager
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class Person
{
    private String userName;// nickname
    private String password;
    private String realName;
    private String tel;
    private String email;
    private String userID;
    private String membership;
    private boolean isManager;
    private String birthday;
    /**
     * Default constructor.
     */
    public Person() {
    }

    public Person(String userName, String password, String realName, String tel, String email, String userID, String birthday, String membership) {
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.tel = tel;
        this.email = email;
        this.userID = userID;
        this.birthday = birthday;
        this.membership = membership;
    }
    public Person(String userName, String password, String realName, String tel, String email, String userID, String membership) {
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.tel = tel;
        this.email = email;
        this.userID = userID;
        this.membership = membership;
    }

    public Person(String s, String s1, String s2, String premier, String s3, String s4, String s5, String s6, String s7) {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassWord() {
        return this.password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getMembership() {
        return this.membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", userID='" + userID + '\'' +
                ", membership='" + membership + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }


    public List<BookInfo> getBookInfoList() {
        return null;
    }

    public String getBalance() {
        return null;
    }

    public String getExpireDate() {
        return null;
    }

    public void setBalance(String toString) {
    }

    public void setExpireDate(String toString) {
    }

    public Object getFirstTime() {
        return null;
    }

    public void setFirstTime(String aFalse) {
    }

    public List<BookInfoForTrainer> getBookInfoForTrainerList() {
        return null;
    }
}
