package ToDoLogger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogDao {
    Connection connection;
    private boolean upload = false;

    public LogDao(Connection connection) {
        this.connection = connection;
    }

    public void changeUpload() {
        this.upload = !this.upload;
    }

    public boolean getUpload(){
        return this.upload;
    }

    public void uploadTasks(String[] content){
        try {
            PreparedStatement pStatement = connection.prepareStatement("insert into logs (taskdate, tasktype, task) values (?, ?, ?)");
            pStatement.setDate(1, Date.valueOf(content[0]));
            pStatement.setString(2, content[1]);
            pStatement.setString(3, content[2]);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Opps something happened with the database.");
        }
    }

    public List<String[]> downloadTasks(){
        
        List<String[]> list = new ArrayList<String[]>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select taskdate, tasktype, task from logs");
            while(result.next()){
                String[] task = {"","",""};
                task[0] = result.getDate("taskdate").toString();
                task[1] = result.getString("tasktype");
                task[2] = result.getString("task");
                list.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to download tasks.");
        }
        
        return list;
    }

    public void getTasks (){
        try {
            Statement statement = connection.createStatement();
            // int rowCount = statement.executeUpdate("insert into Log values (1,'4/4/2021','Do this and that')");
            // System.out.println(rowCount + " rows affected. ");
    
            ResultSet result = statement.executeQuery("select * from logs");
            while (result.next()) {
                System.out.println(result.getInt("taskid"));
                System.out.println(result.getDate("taskdate"));
                System.out.println(result.getString("tasktype"));
                System.out.println(result.getString("task"));
            }
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
