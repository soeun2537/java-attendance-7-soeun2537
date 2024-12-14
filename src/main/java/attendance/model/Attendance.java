package attendance.model;

import java.time.LocalDateTime;

public class Attendance {

    private String nickname;
    private LocalDateTime dateTime;

    private Attendance(String nickname, LocalDateTime dateTime) {
        this.nickname = nickname;
        this.dateTime = dateTime;
    }

    public static Attendance from(String nickname, LocalDateTime localDateTime) {
        return new Attendance(nickname, localDateTime);
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void modifyDateTime(LocalDateTime localDateTime) {
        dateTime = localDateTime;
    }
}
