package attendance.view;

import static attendance.constant.InputConstant.*;

import attendance.model.Time;
import attendance.util.Parser;
import attendance.util.Validator;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.Set;

public class InputView {

    public static String readOption() {
        String input = input();
        Validator.validateNotNull(input);
        Validator.validateOption(input);
        return input;
    }

    public static String readNickname(Set<String> nicknames) {
        String input = input();
        Validator.validateNotNull(input);
        Validator.validateCharacter(input);
        Validator.validateNickname(nicknames, input);
        return input;
    }

    public static String readTime() {
        String input = input();
        Validator.validateNotNull(input);
        Validator.validateTime(input);
        List<String> separatedTime = Parser.separateBySeparator(input, TIME_SEPARATOR.getContent());
        String hour = separatedTime.get(0);
        String minute = separatedTime.get(1);
        Validator.validateNumeric(hour);
        Validator.validateNumeric(minute);
        int convertedHour = Parser.convertStringToInteger(hour);
        int convertedMinute = Parser.convertStringToInteger(minute);
        Validator.validateHour(convertedHour);
        Validator.validateMinute(convertedMinute);
        return input;
    }

    public static int readDay() {
        String input = input();
        Validator.validateNotNull(input);
        Validator.validateNumeric(input);
        int day = Parser.convertStringToInteger(input);
        Validator.validateDay(day);
        return day;
    }

    private static String input() {
        return Console.readLine();
    }
}
