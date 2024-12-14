package attendance.util;

import static attendance.constant.InputConstant.*;

import attendance.constant.InputConstant;
import attendance.constant.PathConstant;
import attendance.model.Attendance;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    private FileParser() {
    }

    public static List<Attendance> readAttendanceFile() {
        List<Attendance> attendances = new ArrayList<>();
        List<String> readLines = FileReader.readFile(PathConstant.ATTENDANCE_FILE_PATH.getPath());
        for (String readLine : readLines) {
            List<String> attendance = Parser.separateBySeparator(readLine, FILE_SEPARATOR.getContent());
            attendances.add(Attendance.from(
                    attendance.get(0),
                    Parser.parseDateTime(attendance.get(1), SPACE.getContent())));
        }
        return attendances;
    }
}
