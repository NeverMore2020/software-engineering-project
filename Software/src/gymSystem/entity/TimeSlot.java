package gymSystem.entity;/*2021/4/21*/

/**
 * This class serves as the items in the timetable
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class TimeSlot {
    private boolean bookingFlag;
    private String duration;

    public TimeSlot(boolean bookingFlag, String duration) {
        this.bookingFlag = bookingFlag;
        this.duration = duration;
    }

    public TimeSlot() {
    }

    public TimeSlot(String duration, boolean bookingFlag) {
        this.bookingFlag = bookingFlag;
        this.duration = duration;
    }

    public boolean isBookingFlag() {
        return bookingFlag;
    }

    public String getDuration() {
        return duration;
    }

    public void setBookingFlag(boolean bookingFlag) {
        this.bookingFlag = bookingFlag;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "bookingFlag=" + bookingFlag +
                ", duration='" + duration + '\'' +
                '}';
    }
}
