package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    public static Connection getConnection(String file) {
        String url = String.format("jdbc:sqlite:C:src/main/resources/%s",file);
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println("Connection Failed! Error: " + e.getMessage());
        }
        return connection;
    }

}
