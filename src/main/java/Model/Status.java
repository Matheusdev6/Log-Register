package Model;

public enum Status {
    NOMINAL("CONNECTION ESTABLISHED"),
    WARNING("SUSPICIOUS ACTIVITY DETECTED"),
    ERROR("ERROR");
    private final String message;
    Status(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
