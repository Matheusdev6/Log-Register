package Service.WebServer;

import Model.Status;
import Model.WebServer.WebServerModel;
import java.util.Random;

public class WebServerService {
    WebServerModel webServerModel;
    public WebServerService() {
    }
    public Status webServerSuccess(){
        int  randomInteger = new Random().nextInt(101);
        if (randomInteger < 90) {
            webServerModel.setStatus(Status.NOMINAL);
        }  else if (randomInteger < 95) {
            webServerModel.setStatus(Status.WARNING);
        } else{
            webServerModel.setStatus(Status.ERROR);
        }
        return webServerModel.getStatus();
    }
}
