package Model.WebServer;
import Model.Status;

import java.time.LocalDateTime;

public class WebServerModel {
    private Status status;

    public WebServerModel() {}

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
