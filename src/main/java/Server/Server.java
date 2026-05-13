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
    public ResponseModel runAndWrite() {
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
        if (this.responseModel.getStatus() == Status.ERROR) {
            System.out.printf("Request ID: %s\n",this.requestModel.getId());
            System.out.println("Request TIME: " + this.requestModel.getTimestamp());
            System.out.println(this.responseModel.getStatus().getMessage());
            System.out.println("Error in : " + this.responseModel.getPlace());
            System.out.println();
        } else {
            System.out.printf("Request ID: %s\n",this.requestModel.getId());
            System.out.println("Request TIME: " + this.requestModel.getTimestamp());
            System.out.println(this.responseModel.getStatus().getMessage());
            System.out.println();
        }
        return responseModel;
    }
}
