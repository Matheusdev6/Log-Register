package Service;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {
    public static Connection getConnection(String file) {
        String url = String.format("jdbc:sqlite:C:src/main/resources/%s",file);
        Connection connection = null;
        SQLiteConfig config = new SQLiteConfig();
        config.setBusyTimeout(1500);
        try{
            connection = DriverManager.getConnection(url,config.toProperties());
        } catch(SQLException e){
            System.out.println("Connection Failed! Error: " + e.getMessage());
        }
        return connection;
    }

}
