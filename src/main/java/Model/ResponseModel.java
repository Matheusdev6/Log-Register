package Model;

public class ResponseModel {
    private Status status;
    private String place;
    public ResponseModel() {}

    public void setStatus(Status status) {
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
