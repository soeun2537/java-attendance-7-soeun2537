package attendance.constant.message;

public enum ErrorMessage {

    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    NOT_FOUND_NICKNAME("등록되지 않은 닉네임입니다."),
    NOT_SCHOOL_DAY("%d월 %d일 토요일은 등교일이 아닙니다."),
    CANT_HANDLE_FUTURE("아직 수정할 수 없습니다."),
    NOT_ATTENDANCE_TIME("캠퍼스 운영 시간에만 출석이 가능합니다."),
    ALREADY_ATTENDANCE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요."),

    NOT_FOUND_FILE("파일이 존재하지 않습니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args);
    }
}
