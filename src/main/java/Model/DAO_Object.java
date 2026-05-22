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
        String attachDatabases = "ATTACH 'src/main/resources/report.sqlite' AS report_DB;";
        String command_1 = String.format("""
                    INSERT INTO %s (id_request,status,place,timestamp) VALUES (?,?,?,?);
                    """,server);
        String command_2 = String.format("""
                      UPDATE %s.%s_Data
                      SET
                        %s_success = CASE WHEN (SELECT status FROM %s WHERE id_request = ?) = ? THEN %s_success + 1 ELSE %s_success END,
                        %s_errors = CASE WHEN (SELECT status FROM %s WHERE id_request = ?) = ? THEN %s_errors + 1 ELSE %s_errors END,
                        %s_requests = (SELECT MAX(id) FROM %s)
                      WHERE id = ?;
                     ""","report_DB",server,server,server,server,server,server,server,server,server,server,server);
        Server serverObject = new Server();
        Connection con = DBConnector.getConnection();
        serverObject.run();
        DAO_Object saveObject = new DAO_Object(serverObject.getRequestModel(), serverObject.getResponseModel());
        try{
            Statement attachStmt = con.createStatement();
            attachStmt.execute(attachDatabases);
            attachStmt.close();
            PreparedStatement stmt = con.prepareStatement(command_1);
            stmt.setString(1, saveObject.getId_request());
            stmt.setString(2, saveObject.getStatus());
            stmt.setString(3, saveObject.getPlace());
            stmt.setString(4, saveObject.getTimestamp());
            stmt.execute();
            PreparedStatement stmt2 = con.prepareStatement(command_2);
            stmt2.setString(1, saveObject.getId_request());
            stmt2.setString(2, "SUCCESS");
            stmt2.setString(3, saveObject.getId_request());
            stmt2.setString(4, "ERROR");
            stmt2.setInt(5,1);
            stmt2.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
