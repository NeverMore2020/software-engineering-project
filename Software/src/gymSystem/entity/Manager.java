package gymSystem.entity;/*2021/5/29*/
/**
 * This class defines the managers
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class Manager extends Person{
    /**
     * Default constructor.
     */
    public Manager() {
    }

    public Manager(String userName, String password, String realName, String tel, String email, String userID, String birthday, String membership) {
        super(userName, password, realName, tel, email, userID, birthday, membership);
        setManager(true);
    }
}
