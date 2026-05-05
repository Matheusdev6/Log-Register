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
        boolean randomBoolean = new Random().nextBoolean();
        authServerModel.setLoggedIn(randomBoolean);
        if (randomBoolean) {
            authServerModel.setStatus(Status.NOMINAL);
        }
        else {
            authServerModel.setStatus(Status.ERROR);
        }
    }
}
