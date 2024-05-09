package gymSystem.entity;/*2021/5/2*/
/**
 * This class define the book information of users and used to present in users live sessions
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class BookInfo {
    private String trainer;
    private String date;
    private String time;

    public BookInfo(String trainer, String date, String time) {
        this.trainer = trainer;
        this.date = date;
        this.time = time;
    }

    public BookInfo() {
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
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

    @Override
    public String toString() {
        return "BookInfo{" +
                "trainer='" + trainer + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public void remove(BookInfo bookInfo) {
    }

    public void add(BookInfo bookInfo) {
    }
}
