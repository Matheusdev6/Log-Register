package Server;

import Model.RequestModel;
import Model.ResponseModel;
import Model.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Writing {
    private UUID id;
    private LocalDateTime timestamp;
    private Status status;
    private String place;

    public Writing(RequestModel request, ResponseModel response) {
        this.id = request.getId();
        this.timestamp = request.getTimestamp();
        this.status = response.getStatus();
        this.place = response.getPlace();
    }
}
