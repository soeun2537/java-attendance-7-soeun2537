package attendance;

import attendance.controller.AttendanceController;
import attendance.repository.AttendanceRepository;
import attendance.service.AttendanceService;

public class Application {
    public static void main(String[] args) {
        AttendanceRepository attendanceRepository = new AttendanceRepository();
        AttendanceService attendanceService = new AttendanceService(attendanceRepository);
        attendanceService.presetting();
        AttendanceController attendanceController = new AttendanceController(attendanceService);
        attendanceController.run();
    }
}
