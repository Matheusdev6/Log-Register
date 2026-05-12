package App.example;

import Model.ResponseModel;
import Server.Server;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Simulating servers");
        for (int i = 1; i <= 10; i++){
            Server server = new Server();
            ResponseModel response = server.run();
        }
    }
}
