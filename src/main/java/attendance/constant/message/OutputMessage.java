package attendance.constant.message;

public enum OutputMessage {

    OPTION_GUIDANCE("오늘은 %s월 %s일 금요일입니다. 기능을 선택해 주세요."),
    OPTION_ONE("1. 출석 확인"),
    OPTION_TWO("2. 출석 수정"),
    OPTION_THREE("3. 크루별 출석 기록 확인"),
    OPTION_FOUR("4. 제적 위험자 확인"),
    OPTION_Q("Q. 종료"),

    NICKNAME_GUIDANCE("닉네임을 입력해 주세요."),
    ATTENDANCE_TIME_GUIDANCE("등교 시간을 입력해 주세요."),
    ATTENDANCE_GUIDANCE("%d월 %02d일 %s요일 %02d:%02d (출석)"),

    MODIFICATION_NICKNAME_GUIDANCE("출석을 수정하려는 크루의 닉네임을 입력해 주세요."),
    MODIFICATION_DAY_GUIDANCE("수정하려는 날짜(일)를 입력해 주세요."),
    MODIFICATION_TIME_GUIDANCE("언제로 변경하겠습니까?"),
    MODIFICATION_GUIDANCE("%d월 %02d일 %s요일 %02d:%02d (%s) -> %02d:%02d (%s) 수정 완료!"),

    ATTENDANCE_RECORD_GUIDANCE("이번 달 %s의 출석 기록입니다."),
    ATTENDANCE_RECORD("%d월 %02d일 %s요일 %02d:%02d (%s)"),
    ABSENCE_RECORD("%d월 %02d일 %s요일 --:-- (%s)"),
    ATTENDANCE_RECORD_TOTAL("출석: %d회"),
    BE_LATE_RECORD_TOTAL("지각: %d회"),
    ABSENCE_RECORD_TOTAL("결석: %d회"),
    INTERVIEWEE_GUIDANCE("%s 대상자입니다."),

    RISK_OF_EXPULSION_GUIDANCE("제적 위험자 조회 결과"),
    RISK_OF_EXPULSION("- %s: 결석 %d회, 지각 %d회 (%s)")
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
