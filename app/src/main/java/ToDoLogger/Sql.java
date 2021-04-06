package ToDoLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sql {

    private boolean upload = false;

    public void changeUpload() {
        this.upload = !this.upload;
    }

    public boolean getUpload(){
        return this.upload;
    }

    public void runSql (){
        // TRUE: FILE   FALSE: DB
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "password123";
    
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            int rowCount = statement.executeUpdate("insert into Log values (1,'4/4/2021','Do this and that')");
            System.out.println(rowCount + " rows affected. ");
    
            ResultSet result = statement.executeQuery("select * from Log");
            while (result.next()) {
                System.out.println(result.getInt("id"));
                System.out.println(result.getDate("date"));
                System.out.println(result.getString("task"));
    
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
