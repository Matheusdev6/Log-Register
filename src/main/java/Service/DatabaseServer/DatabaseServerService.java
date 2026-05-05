package Service.DatabaseServer;

import Model.DatabaseServer.DatabaseServerModel;
import Model.Status;

import java.util.Random;

public class DatabaseServerService {
    DatabaseServerModel databaseServerModel;
    public DatabaseServerService() {}
    public void isHacked(){
        int randomInteger = new Random().nextInt(3);
        if(randomInteger==0){
            databaseServerModel.setStatus(Status.ERROR); // not connected
            databaseServerModel.setHacked(false);
        } else if (randomInteger==1){
            databaseServerModel.setStatus(Status.WARNING);
            databaseServerModel.setHacked(true);
        } else{
            databaseServerModel.setStatus(Status.NOMINAL);
            databaseServerModel.setHacked(false);
        }
    }
}
