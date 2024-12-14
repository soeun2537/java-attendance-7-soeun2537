package attendance.constant;

public enum InputConstant {

    OPTION_PATTERN("^(1|2|3|4|Q)$"),
    CHARACTER_PATTERN("^[가-힣]+$"),
    TIME_PATTERN("^\\d{2}:\\d{2}$"),
    NUMERIC_PATTERN("\\d+"),

    TIME_SEPARATOR(":"),
    FILE_SEPARATOR(","),
    DATE_SEPARATOR("-"),
    SPACE(" "),
    ;

    private final String content;

    InputConstant(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
