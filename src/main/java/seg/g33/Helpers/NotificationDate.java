package seg.g33.Helpers;

import java.time.LocalDate;

public class NotificationDate {
    private int year;
    private int month;
    private int day;

    public NotificationDate(){}

    public NotificationDate(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalDate getLocalDate() {
        return LocalDate.of(this.year,this.month,this.day);
    }
}
