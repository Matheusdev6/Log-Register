package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    public static Connection getConnection() {
        String url = "jdbc:sqlite:C:src/main/resources/database.sqlite";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
            System.out.println("Connected to database successfully");
        } catch(SQLException e){
            System.out.println("Connection Failed! Error: " + e.getMessage());
        }
        return connection;
    }

}
