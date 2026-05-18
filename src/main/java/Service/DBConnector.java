package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    public static Connection getConnection() {
        String url = "jdbc:sqlite:src/main/resources/database.sqlite";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("Connection Failed! Error: " + e.getMessage());
        }
        return connection;
    }

}
