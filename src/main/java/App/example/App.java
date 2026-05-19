package App.example;

import Model.DAO_Object;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Simulating servers!");
        for (int i = 1; i <= 5; i++){
            DAO_Object dao = new DAO_Object();
            dao.saveObject("Server_3");
        }
    }
}
