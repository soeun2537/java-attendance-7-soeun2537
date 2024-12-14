package attendance.model;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public enum AttendanceCriteria {
    월(13, 18),
    화(10, 18),
    수(10, 18),
    목(10, 18),
    금(10, 18),
    ;

    private final int startTime;
    private final int endTime;

    AttendanceCriteria(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static AttendanceCheck checkAttendance(LocalDateTime localDateTime) {
        for (AttendanceCriteria value : values()) {
            if (value.name().equals(localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA))) {
                if (value.startTime == localDateTime.getHour()) {
                    if (localDateTime.getMinute() <= 5) {
                        return AttendanceCheck.출석;
                    }
                    if (localDateTime.getMinute() <= 30) {
                        return AttendanceCheck.지각;
                    }
                    return AttendanceCheck.결석;
                }
            }
        }
        return AttendanceCheck.결석;
    }
}
