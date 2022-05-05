package seg.g33.DataHolders;

import java.text.SimpleDateFormat;

import java.util.Date;

public class Notify {

    private String message;
    private Type type;
    private String timestamp;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Notify(){}

    /**
     * Constructor for the class.
     * @param message The notification message.
     * @param timestamp The time the notification was generated.
     */
    public Notify(String message, Type type, Date timestamp){
        this.message = message;
        this.timestamp = sdf.format(timestamp);
        this.type = type;
    }


    public String toString () {
        return timestamp + "      " + message;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Enum to represent the type of notification.
     */
    public enum Type{
        SELECT("#06962f"), UPDATE("#fa8a02"), CALCULATE ("#ff0000");

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
