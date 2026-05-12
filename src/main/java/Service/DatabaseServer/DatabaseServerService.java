package Service.DatabaseServer;

import Model.DatabaseServer.DatabaseServerModel;
import Model.Status;

import java.util.Random;

public class DatabaseServerService {
    DatabaseServerModel databaseServerModel;
    public DatabaseServerService() {}
    public Status DBSuccess(){
        int number = new Random().nextInt(101);
        if  (number < 70) {
            databaseServerModel.setStatus(Status.NOMINAL);
        } else if (number < 90) {
            databaseServerModel.setStatus(Status.WARNING);
        } else{
            databaseServerModel.setStatus(Status.ERROR);
        }
        return databaseServerModel.getStatus();
    }
}
