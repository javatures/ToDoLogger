package ToDoLogger;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/todologger";
        String username = "todologger";
        String password = "password123";
        Log logger = new Log(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int blowUpCounter = 3;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            LogDao sql = new LogDao(connection);
            System.out.println("Welcome to To Do Logger");
            System.out.println("Please enter the name of your file:");
            String fileName = logger.getFile(reader.readLine());
            System.out.println("What would you like to do?");
            System.out.println("1 - Write to file\n2 - Upload file to database\n3 - Download data to file\nexit - To shut down program");
            while ((input = reader.readLine()) != null){
                switch(input){
                    case "1":
                        logger.writeFile(fileName, reader);
                        break;
                    case "2":
                        System.out.println("Uploading tasks.....");
                        List<String[]> uploadList = logger.readFile(fileName);
                        for (String[] content : uploadList) {
                            sql.uploadTasks(content);
                        }
                        System.out.println("Upload Complete.....");
                        break;
                    case "3":
                        System.out.println("Downloading tasks.....");
                        List<String[]> downloadList = sql.downloadTasks();
                        logger.writeFromDatabase(downloadList, fileName);
                        System.out.println("Download Complete.....");
                        break;
                    case "exit":
                        System.out.println("Program is shutting down. Goodbye.");
                        System.exit(0);
                    default:
                        if (blowUpCounter == 0)
                            logger.blowUp();
                        if (blowUpCounter == 1)
                            System.out.println("Last attempt.");
                        else 
                            System.out.printf("That is not an option. %s more tries before I blow up.", blowUpCounter);
                        blowUpCounter--;
                }
                System.out.println("What would you like to do next?");
                System.out.println("1 - Write to file\n2 - Upload file to database\n3 - Download data to file\nexit - To shut down program");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e){
            e.printStackTrace();
        }
    }
}
