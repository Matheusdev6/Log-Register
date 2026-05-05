package Model.DatabaseServer;
import Model.Status;

public class DatabaseServerModel {
    private Status status;
    private boolean isHacked;

    public DatabaseServerModel(Status status, boolean isHacked) {
        this.status = status;
        this.isHacked = isHacked;
    }
    public DatabaseServerModel() {
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public boolean isHacked() {
        return isHacked;
    }
    public void setHacked(boolean hacked) {
        isHacked = hacked;
    }
}
