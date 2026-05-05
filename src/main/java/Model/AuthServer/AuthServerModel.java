package Model.AuthServer;
import Model.Status;
import java.util.Random;
public class AuthServerModel {
    private Status status;
    private boolean loggedIn;
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
