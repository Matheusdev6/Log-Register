package App.example;

import Model.ResponseModel;
import Server.Server;
import com.google.gson.Gson;
public class App
{
    public static void main( String[] args )
    {
        System.out.println("Simulating servers");
        System.out.println();
        for (int i = 1; i <= 10; i++){
            Server server = new Server();
            server.runAndWrite("file_name.json");
        }
    }
}
