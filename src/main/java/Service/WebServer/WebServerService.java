package Service.WebServer;

import Model.WebServer.WebServerModel;
import Service.AuthServer.AuthServerService;
import Service.DatabaseServer.DatabaseServerService;

import java.util.Random;

public class WebServerService {
    WebServerModel webServerModel;
    AuthServerService authServerService;
    DatabaseServerService databaseServerService;
    public WebServerService(WebServerModel webServerModel, AuthServerService authServerService, DatabaseServerService databaseServerService) {
        this.webServerModel = webServerModel;
        this.authServerService =  authServerService;
        this.databaseServerService = databaseServerService;
    }
    boolean isOnline(){
        // fazer lógica de integração entre os services
        int  randomInteger = new Random().nextInt(2);
        if(randomInteger==1){
            return true;
        }
        else{

            return false;
        }
    }

}
