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

public class Log extends Operations{
    //Expected content: [ date, task ]
    private List<String[]> contents = new ArrayList<String[]>();
    // TRUE: FILE   FALSE: DB
    private boolean FileOrDB = true; 

    void exitLogger(){
        System.out.println("Program shutting down.");
        System.exit(0);
    }

    public String getFile(String input) throws FileNotFoundException, IOException {
        // String input = "Log.txt";
        File file = new File(input);
        if (file.createNewFile()){
            System.out.println("File does not exist. Creating new file..... ");
        }
        return input;
    }

    // Future feature update: if adding type of task as possible input, have type be before or after date
    public void readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] data;
        boolean validDate;

        while ((line = reader.readLine()) != null){
            if (line.length() == 0){
                continue;
            }
            // System.out.println(line);
            data = line.split("\\|", 3);
            validDate = checkDate(data[0]);
            if(validDate) {
                storeContents(data);
                // System.out.println(line);
            }
            else (validDate) {
                System.out.println("Incorrect Log Format.....");
                exitLogger();
            }
        }
        reader.close();
    }
    
    public void storeContents(String[] data) {
        contents.add(data);
    }

    public void writeFile(String fileName, BufferedReader reader) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true));
        // printWriter.println("");
        String input;
        while ((input = reader.readLine()) != null) {
            System.out.println("Please enter the date of the task you logged");
            System.out.println("I can only handle dates in this format: yyyy-mm-dd");

        }
        // for (String[] element : contents){
        //     printWriter.printf("%s|%s|%s \n", element[0], element[1], element[2]);
        // }
        printWriter.close();
    }
    
    public void runLogger() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            // do {
                
            // } while (!input.equals("exit"));
            while((input = reader.readLine()) != null) {
                if (input.equals("exit")) exitLogger();
                String fileName = getFile(input);
                System.out.println("Reading File.....");
                readFile(fileName);
                writeFile(fileName, reader);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
