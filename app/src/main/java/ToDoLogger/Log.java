package ToDoLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Log {
    //Expected content: [ date, task ]
    private List<String[]> contents = new ArrayList<String[]>();

    public String getFile() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // String input = reader.readLine();
        String input = "Log.txt";
        File file = new File(input);
        file.createNewFile();
        reader.close();
        return input;
    }

    // Future feature update: if adding type of task as possible input, have type be before or after date
    public void readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] data;

        while ((line = reader.readLine()) != null){
            if (line.length() == 0){
                continue;
            }
            // System.out.println(line);
            data = line.split(" ", 2);
            storeContents(data);
        }
        reader.close();
        
    }
    
    public void storeContents(String[] data) {
        contents.add(data);
    }

    public void runLogger(Log logger) {
        try {
            String fileName = getFile();
            readFile(fileName);
            logger.writeFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
    
    public void writeFile(String fileName) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true));
        // printWriter.println("");
        for (String[] element : contents){
            printWriter.printf("%s %s \n", element[0], element[1]);
        }
        printWriter.close();
    }
}
