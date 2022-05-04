package seg.g33.Helpers;

import java.time.LocalTime;

public class NotificationTime {
    private int hour;
    private int minute;
    private int second;

    public NotificationTime() { }

    public NotificationTime(int hour,int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }


    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public LocalTime getLocalTime() {
        return LocalTime.of(this.hour,this.minute,this.second);
    }
}
