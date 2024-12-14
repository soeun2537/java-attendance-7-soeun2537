package attendance.model;

public class Subject {

    private String nickname;
    private int beLateTotal;
    private int absenceTotal;
    private String subject;

    private Subject(String nickname, int beLateTotal, int absenceTotal, String subject) {
        this.nickname = nickname;
        this.beLateTotal = beLateTotal;
        this.absenceTotal = absenceTotal;
        this.subject = subject;
    }

    public static Subject from(String name, int beLateTotal, int absenceTotal, String subject) {
        return new Subject(name, beLateTotal, absenceTotal, subject);
    }

    public String getNickname() {
        return nickname;
    }

    public int getBeLateTotal() {
        return beLateTotal;
    }

    public int getAbsenceTotal() {
        return absenceTotal;
    }

    public String getSubject() {
        return subject;
    }
}
