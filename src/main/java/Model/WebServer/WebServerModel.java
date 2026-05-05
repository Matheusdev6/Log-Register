package Model.WebServer;
import Model.Status;

public class WebServerModel {
    private Status status;
    private boolean isOnline;

    public WebServerModel(Status status, boolean isOnline) {
        this.status = status;
        this.isOnline = isOnline;
    }
    public WebServerModel(){}

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public boolean isOnline() {
        return isOnline;
    }
    public void setOnline(boolean online) {
        isOnline = online;
    }
}
