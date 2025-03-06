package entity;

public enum AttendanceStatus {
    PRESENT("Present"), ABSENT("Absent");

    private final String status;

    private AttendanceStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static AttendanceStatus fromCode(String code) {
        for (AttendanceStatus status : AttendanceStatus.values()) {
            if (status.getStatus().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
