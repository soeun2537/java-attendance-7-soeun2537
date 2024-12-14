package attendance.model;

public class Time {

    private int hour;
    private int minute;

    private Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static Time from(int hour, int minute) {
        return new Time(hour, minute);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}