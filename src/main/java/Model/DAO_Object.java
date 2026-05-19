package Model;

import Server.Server;
import Service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO_Object {
    String id_request;
    String timestamp;
    String status;
    String place;
    public DAO_Object(RequestModel request, ResponseModel response) {
        id_request = request.getId().toString();
        timestamp = request.getTimestamp().toString();
        status = response.getStatus().toString();
        place = response.getPlace();
    }
    public DAO_Object() {}
    public String getId_request() {
        return id_request;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getPlace() {
        return place;
    }

    public void saveObject(String server) {
        try{
            Server serverObject = new Server();
            Connection con = DBConnector.getConnection();
            serverObject.run();
            DAO_Object saveObject = new DAO_Object(serverObject.getRequestModel(), serverObject.getResponseModel());
            String command = String.format("""
                    INSERT INTO %s (id_request,status,place,timestamp) VALUES (?,?,?,?);
                    """,server);
            PreparedStatement stmt = con.prepareStatement(command);
            stmt.setString(1, saveObject.getId_request());
            stmt.setString(2, saveObject.getStatus());
            stmt.setString(3, saveObject.getPlace());
            stmt.setString(4, saveObject.getTimestamp());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
