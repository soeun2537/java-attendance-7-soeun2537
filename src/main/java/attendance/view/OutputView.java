package attendance.view;

import static attendance.constant.message.OutputMessage.*;

import attendance.model.AttendanceCheck;
import attendance.model.AttendanceRecord;
import attendance.model.Subject;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class OutputView {

    public static void printOptionGuidance() {
        println(OPTION_GUIDANCE.getMessage(DateTimes.now().getMonthValue(), DateTimes.now().getDayOfMonth()));
        println(OPTION_ONE.getMessage());
        println(OPTION_TWO.getMessage());
        println(OPTION_THREE.getMessage());
        println(OPTION_FOUR.getMessage());
        println(OPTION_Q.getMessage());
    }

    public static void printNicknameGuidance() {
        printNewLine();
        println(NICKNAME_GUIDANCE.getMessage());
    }

    public static void printAttendanceTimeGuidance() {
        println(ATTENDANCE_TIME_GUIDANCE.getMessage());
    }

    public static void printAttendanceGuidance(LocalDateTime dateTime) {
        printNewLine();
        println(ATTENDANCE_GUIDANCE.getMessage(dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA),
                dateTime.getHour(),
                dateTime.getMinute()
        ));
        printNewLine();
    }

    public static void printModificationNicknameGuidance() {
        printNewLine();
        println(MODIFICATION_NICKNAME_GUIDANCE.getMessage());
    }

    public static void printModificationDayGuidance() {
        println(MODIFICATION_DAY_GUIDANCE.getMessage());
    }

    public static void printModificationTimeGuidance() {
        println(MODIFICATION_TIME_GUIDANCE.getMessage());
    }

    public static void printModificationGuidance(List<AttendanceRecord> records) {
        printNewLine();
        printModification(records);
        printNewLine();
    }

    private static void printModification(List<AttendanceRecord> records) {
        println(MODIFICATION_GUIDANCE.getMessage(
                records.get(0).getLocalDateTime().getMonthValue(),
                records.get(0).getLocalDateTime().getDayOfMonth(),
                records.get(0).getLocalDateTime().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA),
                records.get(0).getLocalDateTime().getHour(),
                records.get(0).getLocalDateTime().getMinute(),
                records.get(0).getCheck().name(),
                records.get(1).getLocalDateTime().getHour(),
                records.get(1).getLocalDateTime().getMinute(),
                records.get(1).getCheck().name()
        ));
    }

    public static void printAttendanceRecord(List<AttendanceRecord> attendanceRecords) {
        printNewLine();
        println(ATTENDANCE_RECORD_GUIDANCE.getMessage(attendanceRecords.getFirst().getNickname()));
        printNewLine();
        for (AttendanceRecord attendanceRecord : attendanceRecords) {
            printAttendanceRecords(attendanceRecord);
        }
        printNewLine();
    }

    private static void printAttendanceRecords(AttendanceRecord attendanceRecord) {
        if (attendanceRecord.getCheck() == AttendanceCheck.결석 &&
                attendanceRecord.getLocalDateTime().getHour() == 0) {
            println(ABSENCE_RECORD.getMessage(
                    attendanceRecord.getLocalDateTime().getMonthValue(),
                    attendanceRecord.getLocalDateTime().getDayOfMonth(),
                    attendanceRecord.getLocalDateTime().getDayOfWeek()
                            .getDisplayName(TextStyle.NARROW, Locale.KOREA),
                    attendanceRecord.getCheck().name()
            ));
            return;
        }
        println(ATTENDANCE_RECORD.getMessage(
                attendanceRecord.getLocalDateTime().getMonthValue(),
                attendanceRecord.getLocalDateTime().getDayOfMonth(),
                attendanceRecord.getLocalDateTime().getDayOfWeek()
                        .getDisplayName(TextStyle.NARROW, Locale.KOREA),
                attendanceRecord.getLocalDateTime().getHour(),
                attendanceRecord.getLocalDateTime().getMinute(),
                attendanceRecord.getCheck().name()
        ));
    }

    public static void printTotal(List<Integer> total) {
        println(ATTENDANCE_RECORD_TOTAL.getMessage(total.get(0)));
        println(BE_LATE_RECORD_TOTAL.getMessage(total.get(1)));
        println(ABSENCE_RECORD_TOTAL.getMessage(total.get(2)));
    }

    public static void printSubject(String subject) {
        printNewLine();
        if (!subject.equals("정상")) {
            println(INTERVIEWEE_GUIDANCE.getMessage(subject));
            printNewLine();
        }
    }

    public static void printSubjects(List<Subject> subjects) {
        printNewLine();
        println(RISK_OF_EXPULSION_GUIDANCE.getMessage());
        for (Subject subject : subjects) {
            if (!subject.getSubject().equals("정상")) {
                printSubjectsDetials(subject);
            }
        }
        printNewLine();
    }

    private static void printSubjectsDetials(Subject subject) {
        println(RISK_OF_EXPULSION.getMessage(
                subject.getNickname(),
                subject.getBeLateTotal(),
                subject.getAbsenceTotal(),
                subject.getSubject()
        ));
    }

    public static void printErrorMessage(RuntimeException e) {
        printNewLine();
        println(e.getMessage());
    }

    private static void println(String content) {
        System.out.println(content);
    }

    private static void printNewLine() {
        System.out.print(System.lineSeparator());
    }
}
