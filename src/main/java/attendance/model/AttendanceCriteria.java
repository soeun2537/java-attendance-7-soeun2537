package attendance.model;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public enum AttendanceCriteria {
    월(13),
    화(10),
    수(10),
    목(10),
    금(10),
    ;

    private final int startTime;

    AttendanceCriteria(int startTime) {
        this.startTime = startTime;
    }

    public static AttendanceCheck checkAttendance(LocalDateTime localDateTime) {
        for (AttendanceCriteria value : values()) {
            AttendanceCheck 출석 = getAttendanceCheck(localDateTime, value);
            if (출석 != null) {
                return 출석;
            }
        }
        return AttendanceCheck.결석;
    }

    private static AttendanceCheck getAttendanceCheck(LocalDateTime localDateTime, AttendanceCriteria value) {
        if (value.name().equals(localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA))) {
            if (value.startTime == localDateTime.getHour()) {
                return getAttendanceCheck(localDateTime);
            }
        }
        return null;
    }

    private static AttendanceCheck getAttendanceCheck(LocalDateTime localDateTime) {
        if (localDateTime.getMinute() <= 5) {
            return AttendanceCheck.출석;
        }
        if (localDateTime.getMinute() <= 30) {
            return AttendanceCheck.지각;
        }
        return AttendanceCheck.결석;
    }
}
