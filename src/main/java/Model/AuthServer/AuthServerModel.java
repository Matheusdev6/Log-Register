package Model.AuthServer;
import Model.Status;
public class AuthServerModel {
    private Status status;
    public AuthServerModel() {}

    public Status getStatus() {return status;}
    public void setStatus(Status status) {
        this.status = status;
    }
}
