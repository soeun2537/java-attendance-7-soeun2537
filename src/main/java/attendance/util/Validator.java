package attendance.util;

import static attendance.constant.InputConstant.*;
import static attendance.constant.message.ErrorMessage.*;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.Set;

public class Validator {

    private Validator() {
    }

    public static void validateNotNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateOption(String input) {
        if (!input.matches(OPTION_PATTERN.getContent())) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateCharacter(String input) {
        if (!input.matches(CHARACTER_PATTERN.getContent())) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateTime(String input) {
        if (!input.matches(TIME_PATTERN.getContent())) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateHour(int input) {
        if (input < 0 || input > 23) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateMinute(int input) {
        if (input < 0 || input > 59) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateNumeric(String input) {
        if (!input.matches(NUMERIC_PATTERN.getContent())) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateDay(int input) {
        if (input < 0 || input > 31) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    public static void validateNickname(Set<String> nicknames, String input) {
        for (String nickname : nicknames) {
            if (nickname.equals(input)) {
                return;
            }
        }
        throw new IllegalArgumentException(NOT_FOUND_NICKNAME.getMessage());
    }

    public static void validateSchoolDay() {
        if (DateTimes.now().getDayOfWeek().getValue() == 6 || DateTimes.now().getDayOfWeek().getValue() == 7) {
            throw new IllegalArgumentException(
                    NOT_SCHOOL_DAY.getMessage(DateTimes.now().getMonth().getValue(), DateTimes.now().getDayOfMonth()));
        }
    }
}
