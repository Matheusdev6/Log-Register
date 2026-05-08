package Service.AuthServer;

import Model.AuthServer.AuthServerModel;
import Model.Status;

import java.util.Random;

public class AuthServerService {
    AuthServerModel authServerModel;
    public AuthServerService(AuthServerModel authServerModel) {
        this.authServerModel = authServerModel;
    }
    public void AuthSuccess(){
        int number = new Random().nextInt(101);
        if (number < 80) {
            authServerModel.setStatus(Status.NOMINAL);
        }
        else  {
            authServerModel.setStatus(Status.ERROR);
        }
    }
}
