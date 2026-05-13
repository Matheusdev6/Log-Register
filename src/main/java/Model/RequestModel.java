package Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RequestModel {
    private UUID id;
    private LocalDateTime timestamp;
    public RequestModel() {
        this.timestamp = LocalDateTime.now();
        this.id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
