package attendance.service;

import static attendance.constant.InputConstant.*;

import attendance.model.Attendance;
import attendance.model.AttendanceRecord;
import attendance.model.Subject;
import attendance.repository.AttendanceRepository;
import attendance.util.FileParser;
import attendance.util.Parser;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void presetting() {
        attendanceRepository.initialize();
        List<Attendance> attendances = FileParser.readAttendanceFile();
        for (Attendance attendance : attendances) {
            attendanceRepository.add(attendance);
        }
    }

    public LocalDateTime addAttendance(String nickname, String attendanceTime) {
        String dateTime = Parser.attach(DateTimes.now().toString(), attendanceTime, SPACE.getContent());
        List<String> separatedDateTime = Parser.separateBySeparator(dateTime, SPACE.getContent());
        String date = Parser.separateBySeparator(separatedDateTime.get(0), T.getContent()).get(0);
        String time = separatedDateTime.get(1);
        String attach = Parser.attach(date, time, SPACE.getContent());
        LocalDateTime attendance = Parser.parseDateTime(attach, SPACE.getContent());
        attendanceRepository.add(Attendance.from(nickname, attendance));
        return attendance;
    }

    public List<AttendanceRecord> modifyAttendance(String nickname, int day, String time) {
        return attendanceRepository.modify(nickname, day, time);
    }

    public List<AttendanceRecord> getAttendance(String nickname) {
        return attendanceRepository.getAttendances(nickname);
    }

    public List<Integer> getTotal(String nickname) {
        return attendanceRepository.getTotal(nickname);
    }

    public String getSubject(String nickname) {
        return attendanceRepository.getSubject(nickname);
    }

    public List<Subject> findSubjects() {
        return attendanceRepository.findSubjects();
    }

    public Set<String> getNicknames() {
        return attendanceRepository.findAllNickname();
    }
}
