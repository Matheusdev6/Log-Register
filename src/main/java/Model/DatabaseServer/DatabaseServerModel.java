package Model.DatabaseServer;
import Model.Status;

public class DatabaseServerModel {
    private Status status;

    public DatabaseServerModel() {}

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

}
