package Model.AuthServer;
import Model.Status;

public class AuthServerModel {
    private Status status;
    private boolean loggedIn;
    public AuthServerModel(Status status, boolean loggedIn) {
        this.status = status;
        this.loggedIn = loggedIn;
    }
    public AuthServerModel() {}

    public Status getStatus() {
        return status;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
