package attendance.controller;

import attendance.constant.message.ErrorMessage;
import attendance.model.AttendanceRecord;
import attendance.model.Subject;
import attendance.service.AttendanceService;
import attendance.util.Validator;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public void run() {
        while (true) {
            Optional<String> option = returnOption();
            if (option.isEmpty()) {
                break;
            }
            if (option.get().equals("1")) {
                Validator.validateSchoolDay();
                Optional<String> nickname = returnNickname(attendanceService.getNicknames());
                if (nickname.isEmpty()) {
                    break;
                }
                Optional<String> attendanceTime = returnAttendanceTime();
                if (attendanceTime.isEmpty()) {
                    break;
                }
                LocalDateTime attendance = attendanceService.addAttendance(nickname.get(), attendanceTime.get());
                OutputView.printAttendanceGuidance(attendance);
                continue;
            }
            if (option.get().equals("2")) {
                Optional<String> nickname = returnModificationNickname(attendanceService.getNicknames());
                if (nickname.isEmpty()) {
                    break;
                }
                Optional<Integer> day = returnModificationDay();
                if (day.isEmpty()) {
                    break;
                }
                Optional<String> modificationTime = returnModificationTime();
                if (modificationTime.isEmpty()) {
                    break;
                }
                List<AttendanceRecord> attendanceRecords = attendanceService.modifyAttendance(nickname.get(), day.get(),
                        modificationTime.get());
                OutputView.printModificationGuidance(attendanceRecords);
                continue;
            }
            if (option.get().equals("3")) {
                Optional<String> nickname = returnNickname(attendanceService.getNicknames());
                if (nickname.isEmpty()) {
                    break;
                }
                List<AttendanceRecord> attendanceRecords = attendanceService.getAttendance(nickname.get());
                OutputView.printAttendanceRecord(attendanceRecords);
                List<Integer> total = attendanceService.getTotal(nickname.get());
                OutputView.printTotal(total);
                String subject = attendanceService.getSubject(nickname.get());
                OutputView.printSubject(subject);
                continue;
            }
            if (option.get().equals("4")) {
                List<Subject> subjects = attendanceService.findSubjects();
                OutputView.printSubjects(subjects);
                continue;
            }
            if (option.get().equals("Q")) {
                break;
            }
        }
    }

    private Optional<String> returnOption() {
        for (int attempt = 0; attempt < 1; attempt++) {
            try {
                OutputView.printOptionGuidance();
                return Optional.of(InputView.readOption());
            } catch (RuntimeException e) {
                OutputView.printErrorMessage(e);
            }
        }
        return Optional.empty();
    }

    private Optional<String> returnNickname(Set<String> nicknames) {
        for (int attempt = 0; attempt < 1; attempt++) {
            try {
                OutputView.printNicknameGuidance();
                return Optional.of(InputView.readNickname(nicknames));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return Optional.empty();
    }

    private Optional<String> returnAttendanceTime() {
        for (int attempt = 0; attempt < 1; attempt++) {
            try {
                OutputView.printAttendanceTimeGuidance();
                return Optional.of(InputView.readTime());
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return Optional.empty();
    }

    private Optional<String> returnModificationNickname(Set<String> nicknames) {
        for (int attempt = 0; attempt < 1; attempt++) {
            try {
                OutputView.printModificationNicknameGuidance();
                return Optional.of(InputView.readNickname(nicknames));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return Optional.empty();
    }

    private Optional<Integer> returnModificationDay() {
        for (int attempt = 0; attempt < 1; attempt++) {
            try {
                OutputView.printModificationDayGuidance();
                return Optional.of(InputView.readDay());
            } catch (RuntimeException e) {
                OutputView.printErrorMessage(e);
            }
        }
        return Optional.empty();
    }

    private Optional<String> returnModificationTime() {
        for (int attempt = 0; attempt < 1; attempt++) {
            try {
                OutputView.printModificationTimeGuidance();
                return Optional.of(InputView.readTime());
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return Optional.empty();
    }
}
