
package ToDoLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public String getFile() throws IOException, FileNotFoundException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // String input = reader.readLine();
        String input = "Log.txt";
        File file = new File(input);
        file.createNewFile();
        reader.close();
        return input;
    }

    public static void main(String[] args) {
        App logger = new App();
        System.out.println("Welcome to To Do Logger.");
        System.out.println("Please enter the name of your To Do Log (Press enter for default name Log.txt):");
        try {
            String fileName = logger.getFile();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            
        }
    }
}
