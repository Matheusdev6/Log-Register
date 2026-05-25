package Model;

import Server.Server;
import Service.DBConnector;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.*;

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
        String command_3 = String.format("""
                UPDATE %s.Total_Data
                SET
                  total_success = (SELECT SUM(total_success) FROM (
                  SELECT S1_success AS Total_success FROM S1_Data
                  UNION ALL
                  SELECT S2_success AS Total_success FROM S2_Data
                  UNION ALL
                  SELECT S3_success AS Total_success FROM S3_Data
                  )),
                  total_errors = (SELECT SUM(total_errors) FROM (
                  SELECT S1_errors AS Total_errors FROM S1_Data
                  UNION ALL
                  SELECT S2_errors AS Total_errors FROM S2_Data
                  UNION ALL
                  SELECT S3_errors AS Total_errors FROM S3_Data
                  )),
                  total_requests = (SELECT SUM(total) FROM (
                  SELECT S1_requests AS Total FROM S1_Data
                  UNION ALL
                  SELECT S2_requests AS Total FROM S2_Data
                  UNION ALL
                  SELECT S3_requests AS Total FROM S3_Data
                  ));
                ""","report_DB");
        Server serverObject = new Server();
        Connection con = DBConnector.getConnection("database.sqlite");
        serverObject.run();
        DAO_Object saveObject = new DAO_Object(serverObject.getRequestModel(), serverObject.getResponseModel());
        try{
            Statement attachStmt = con.createStatement();
            Statement timeout = con.createStatement();
            timeout.execute("PRAGMA busy_timeout=2000");
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
            PreparedStatement stmt3 = con.prepareStatement(command_3);
            stmt3.execute();
            stmt3.close();
            stmt2.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void display(){
        System.out.println("\t>>>>>REPORTS<<<<<");
        Connection con = DBConnector.getConnection("report.sqlite");
        String server = "";
        String command = """
                SELECT * FROM %s_Data;
                """;
        for(int i = 1; i <= 4; i++){
            server = switch (i) {
                case 1 -> "S1";
                case 2 -> "S2";
                case 3 -> "S3";
                case 4 -> "Total";
                default -> throw new IllegalStateException("Unexpected value: " + i);
            };
            try{
                String query = String.format(command,server);
                String tot = String.format("%s_requests",server);
                String suc = String.format("%s_success",server);
                String err = String.format("%s_errors",server);
                PreparedStatement stmt = con.prepareStatement(query);
                try (ResultSet rs = stmt.executeQuery()){
                    while (rs.next()){
                        int total =  rs.getInt(tot);
                        int success = rs.getInt(suc);
                        int error = rs.getInt(err);
                        if(i!=4){
                            System.out.printf("\tServer %d INFO:\n\n",i);
                        } else{
                            System.out.println("\tTotal INFO:\n");
                        }
                        System.out.printf("\tSuccess: %d\n\tError: %d\n\tTotal: %d\n", success, error,total);
                        System.out.printf("\tSuccess rate, percentage, of %s : %.2f.\n",server,(float)(100*success/total));
                        System.out.printf("\tError rate, percentage, of %s: %.2f.\n",server,(float)(100*error/total));
                        System.out.println("\t----------------------");
                    }
                } catch (SQLException e){
                    System.out.println("SQLException: " + e.getMessage());
                }
            } catch(SQLException e){
                System.out.println("SQLException: " + e.getMessage());
            }
        }
    }
}
