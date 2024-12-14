package attendance.repository;

import attendance.constant.InputConstant;
import attendance.model.Attendance;
import attendance.model.AttendanceCheck;
import attendance.model.AttendanceCriteria;
import attendance.model.AttendanceRecord;
import attendance.model.Subject;
import attendance.util.Parser;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AttendanceRepository {

    private List<Attendance> attendances;

    public void initialize() {
        attendances = new ArrayList<>();
    }

    public void add(Attendance attendance) {
        attendances.add(attendance);
    }

    public List<AttendanceRecord> modify(String nickname, int day, String time) {
        List<AttendanceRecord> dateTimes = new ArrayList<>();
        for (Attendance attendance : attendances) {
            if (attendance.getNickname().equals(nickname) &&
                    String.valueOf(attendance.getDateTime().getDayOfMonth()).equals(String.valueOf(day))) {
                dateTimes.add(AttendanceRecord.from(nickname,
                        attendance.getDateTime(),
                        AttendanceCriteria.checkAttendance(attendance.getDateTime())));
                String dateTime = Parser.attach(
                        LocalDate.of(DateTimes.now().getYear(), DateTimes.now().getMonth(), day).toString(), time,
                        InputConstant.SPACE.getContent());
                LocalDateTime modifiedDateTime = Parser.parseDateTime(dateTime, " ");
                attendance.modifyDateTime(modifiedDateTime);
                dateTimes.add(AttendanceRecord.from(nickname,
                        modifiedDateTime,
                        AttendanceCriteria.checkAttendance(modifiedDateTime)));
            }
        }
        return dateTimes;
    }

    public List<AttendanceRecord> getAttendances(String nickname) {
        List<AttendanceRecord> sortedAttendances = new ArrayList<>();
        LocalDateTime now = DateTimes.now();
        for (int i = 1; i < now.getDayOfMonth(); i++) {
            LocalDate localDate = LocalDate.of(now.getYear(), now.getMonth(), i);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek.getValue() == 6 || dayOfWeek.getValue() == 7) {
                continue;
            }
            Optional<Attendance> attendance = get(nickname,
                    LocalDate.of(now.getYear(), now.getMonth(), i));
            if (attendance.isEmpty()) {
                sortedAttendances.add(AttendanceRecord.from(
                        nickname,
                        LocalDateTime.of(now.getYear(), now.getMonth(), i, 0, 0),
                        AttendanceCheck.결석));
                continue;
            }
            sortedAttendances.add(AttendanceRecord.from(
                    nickname,
                    attendance.get().getDateTime(),
                    AttendanceCriteria.checkAttendance(attendance.get().getDateTime())));
        }
        return sortedAttendances;
    }

    private Optional<Attendance> get(String nickname, LocalDate localDate) {
        for (Attendance attendance : attendances) {
            if (attendance.getNickname().equals(nickname) &&
                    attendance.getDateTime().toLocalDate().equals(localDate)) {
                return Optional.of(attendance);
            }
        }
        return Optional.empty();
    }

    public List<Integer> getTotal(String nickname) {
        List<Integer> total = new ArrayList<>();
        int attendanceTotal = 0;
        int beLateTotal = 0;
        int absenceTotal = 0;
        List<AttendanceRecord> attendanceRecords = getAttendances(nickname);
        for (AttendanceRecord attendanceRecord : attendanceRecords) {
            if (attendanceRecord.getCheck() == AttendanceCheck.출석) {
                attendanceTotal += 1;
                continue;
            }
            if (attendanceRecord.getCheck() == AttendanceCheck.지각) {
                beLateTotal += 1;
                continue;
            }
            if (attendanceRecord.getCheck() == AttendanceCheck.결석) {
                absenceTotal += 1;
            }
        }
        total.add(attendanceTotal);
        total.add(beLateTotal);
        total.add(absenceTotal);
        return total;
    }

    public String getSubject(String nickname) {
        Integer absenceTotal = getTotal(nickname).get(2);
        if (absenceTotal == 2) {
            return "경고";
        }
        if (absenceTotal == 3 || absenceTotal == 4) {
            return "면담";
        }
        if (absenceTotal > 5) {
            return "제적";
        }
        return "정상";
    }

    public List<Subject> findSubjects() {
        List<Subject> findSubjects = new ArrayList<>();
        Set<String> allNicknames = findAllNickname();
        for (String nickname : allNicknames) {
            findSubjects.add(Subject.from(nickname,
                    getTotal(nickname).get(1),
                    getTotal(nickname).get(2),
                    getSubject(nickname)));
        }
        return findSubjects;
    }

    public Set<String> findAllNickname() {
        return new HashSet<>(getAllNicknames());
    }

    private List<String> getAllNicknames() {
        List<String> allNicknames = new ArrayList<>();
        for (Attendance attendance : attendances) {
            allNicknames.add(attendance.getNickname());
        }
        return allNicknames;
    }
}
