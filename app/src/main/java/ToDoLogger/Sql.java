package ToDoLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sql {

    public void runSql (){
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
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
