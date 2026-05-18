package Server;

import Model.RequestModel;
import Model.ResponseModel;
import Model.Status;
import Service.AuthServer.AuthServerService;
import Service.DatabaseServer.DatabaseServerService;
import Service.WebServer.WebServerService;

public class Server {
    AuthServerService authServerService;
    DatabaseServerService databaseServerService;
    WebServerService webServerService;
    RequestModel requestModel;
    ResponseModel responseModel;
    public Server() {
        this.authServerService = new AuthServerService();
        this.databaseServerService = new DatabaseServerService();
        this.webServerService = new WebServerService();
        this.requestModel = new RequestModel();
        this.responseModel = new ResponseModel();
    }

    public void fail(String place){
        responseModel.setStatus(Status.ERROR);
        responseModel.setPlace(place);
    }
    public void runAndWrite(String fileName) {
        Status webstatus = this.webServerService.webServerSuccess();
        if (webstatus == Status.NOMINAL) {
            Status authStatus = this.authServerService.AuthSuccess();
            if (authStatus == Status.NOMINAL) {
                Status databaseStatus = this.databaseServerService.DBSuccess();
                if (databaseStatus == Status.NOMINAL) {
                    this.responseModel.setStatus(Status.SUCCESS);
                } else {
                    fail("DATABASE");
                }
            } else {
                fail("AUTH");
            }
        } else {
            fail("WEB");
        }
    }
}
