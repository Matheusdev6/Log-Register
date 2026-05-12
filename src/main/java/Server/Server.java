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
    public Server(AuthServerService authServerService, DatabaseServerService databaseServerService, WebServerService webServerService, RequestModel requestModel, ResponseModel responseModel) {
        this.authServerService = authServerService;
        this.databaseServerService = databaseServerService;
        this.webServerService = webServerService;
        this.requestModel = requestModel;
        this.responseModel = responseModel;
    }

    public void fail(String place){
        responseModel.setStatus(Status.ERROR);
        responseModel.setPlace(place);
    }
    public ResponseModel run(Server server) {
        Status webstatus = webServerService.webServerSuccess();
        if(webstatus == Status.NOMINAL) {
            Status authStatus = authServerService.AuthSuccess();
            if(authStatus == Status.NOMINAL) {
                Status databaseStatus = databaseServerService.DBSuccess();
                if(databaseStatus == Status.NOMINAL) {
                    this.responseModel.setStatus(Status.SUCCESS);
                    System.out.println(this.responseModel.getStatus().getMessage());
                }
                else{
                    fail("DATABASE ERROR");
                    System.out.println(this.responseModel.getStatus().getMessage());
                }
            } else{
                    fail("AUTH ERROR");
                    System.out.println(this.responseModel.getStatus().getMessage());
            }
        } else{
            fail("WEB ERROR");
            System.out.println(this.responseModel.getStatus().getMessage());
        }
        return responseModel;
    }
    public Server( )
    {}
}
