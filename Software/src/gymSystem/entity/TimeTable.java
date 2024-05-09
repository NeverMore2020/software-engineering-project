package gymSystem.entity;/*2021/4/21*/

import java.util.List;
/**
 * This class serves as the timetable hold by trainers
 * @version 1.0.0
 * @author Yitai Cheng BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class TimeTable {
    private List<TimeSlot> timeSlotList;


    @Override
    public String toString() {
        return "TimeTable{" +
                "timeSlotList=" + timeSlotList +
                '}';
    }

    public TimeTable(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public List<TimeSlot> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlot> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public TimeTable() {
    }

}

