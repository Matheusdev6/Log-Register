package Model;

public enum Status {
    NOMINAL("CONNECTION ESTABLISHED"),
    WARNING("SUSPICIOUS ACTIVITY DETECTED"),
    ERROR("ERROR"),
    SUCCESS("EVERYTHING IS WORKING");
    private final String message;
    Status(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
