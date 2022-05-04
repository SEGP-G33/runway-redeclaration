package seg.g33.DataHolders;

import seg.g33.Entitites.*;
import seg.g33.Helpers.NotificationDate;
import seg.g33.Helpers.NotificationTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import java.util.ArrayList;

public class Notify {

    private String message;
    private NotificationDate date;
    private NotificationTime time;
    private Type type;


    public Notify(){}

    /**
     * Constructor for the class.
     * @param message The notification message.
     * @param time The time the notification was generated.
     */
    public Notify(String message, LocalDateTime time, Type type){
        this.message = message;
        this.date = new NotificationDate(time.getYear(),time.getMonth().getValue(),time.getDayOfMonth());
        this.time = new NotificationTime(time.getHour(),time.getMinute(),time.getSecond());
        this.type = type;
    }


    public String toString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.of(date.getLocalDate(),time.getLocalTime()).format(formatter) + "      " +  this.getMessage() ;
    }

    /**
     * Getters and setters.
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public NotificationDate getDate() {
        return date;
    }

    public void setDate(NotificationDate date) {
        this.date = date;
    }

    public NotificationTime getTime() {
        return time;
    }

    public void setTime(NotificationTime time) {
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    /**
     * Enum to represent the type of notification.
     */
    public enum Type{
        SELECT("#06962f"), UPDATE("#fa8a02");

        private String color;

        private Type(String color){
            this.color = color;
        }

        /**
         * Converts the type to it's color
         * @return String equivalent of the flight method..
         */
        public String getColor(){
            return this.color;
        }
    }
}
