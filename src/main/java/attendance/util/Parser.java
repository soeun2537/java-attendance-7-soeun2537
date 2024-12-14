package attendance.util;

import static attendance.constant.InputConstant.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    private Parser() {
    }

    public static List<String> separateBySeparator(String input, String separator) {
        return Arrays.stream(input.split(separator))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Integer convertStringToInteger(String input) {
        return Integer.parseInt(input);
    }

    // 2024-12-14 14:46로 입력
    public static LocalDateTime parseDateTime(String input, String separator) {
        List<String> separatedDate = separateBySeparator(input, separator);
        List<String> yearAndMonthAndDay = separateBySeparator(separatedDate.get(0), DATE_SEPARATOR.getContent());
        List<String> hourAndTime = separateBySeparator(separatedDate.get(1), TIME_SEPARATOR.getContent());
        return LocalDateTime.of(
                convertStringToInteger(yearAndMonthAndDay.get(0)),
                convertStringToInteger(yearAndMonthAndDay.get(1)),
                convertStringToInteger(yearAndMonthAndDay.get(2)),
                convertStringToInteger(hourAndTime.get(0)),
                convertStringToInteger(hourAndTime.get(1)));
    }

    public static String attach(String input1, String input2, String separator) {
        return input1 + separator + input2;
    }
}
