package ToDoLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Log {
    public String getFile() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // String input = reader.readLine();
        String input = "Log.txt";
        File file = new File(input);
        file.createNewFile();
        reader.close();
        return input;
    }

    public void readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
    
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();
        
    }

    public void runLogger(Log logger) {
        try {
            String fileName = logger.getFile();
            logger.readFile(fileName);
            logger.writeFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public void writeFile(String fileName) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        printWriter.printf("testing here: %s ", "Hello World!");
        printWriter.close();
    }
}
