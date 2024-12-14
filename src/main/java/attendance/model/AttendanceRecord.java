package attendance.model;

import java.time.LocalDateTime;

public class AttendanceRecord {

    private String nickname;
    private LocalDateTime localDateTime;
    private AttendanceCheck check;

    private AttendanceRecord(String nickname, LocalDateTime localDateTime, AttendanceCheck check) {
        this.nickname = nickname;
        this.localDateTime = localDateTime;
        this.check = check;
    }

    public static AttendanceRecord from(String nickname, LocalDateTime localDateTime, AttendanceCheck check) {
        return new AttendanceRecord(nickname, localDateTime, check);
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public AttendanceCheck getCheck() {
        return check;
    }
}
