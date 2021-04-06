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
    //Expected content: [ date, type, task ]
    private List<String[]> writeContents = new ArrayList<String[]>();
    private List<String[]> uploadContents = new ArrayList<String[]>();

    public String getFile(String input) throws FileNotFoundException, IOException {
        File file = new File(input);
        if (file.createNewFile()){
            System.out.println("File does not exist. Creating new file..... ");
        }
        return input;
    }
    
    public void readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        String[] data;
        boolean validDate;
        System.out.println("Reading File.....");
        
        while ((line = reader.readLine()) != null){
            if (line.length() == 0){
                continue;
            }
            // System.out.println(line);
            data = line.split("\\|", 3);
            validDate = checkDate(data[0]);
            if(validDate) {
                storeContents(this.uploadContents, data);
                // System.out.println(line);
            }
        }
        reader.close();
    }
    
    public void storeContents(List<String[]> contents,String[] data) {
        contents.add(data);
    }

    public void writeFile(String fileName, BufferedReader reader) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true));
        String input;
        boolean type = false;
        System.out.println("Would you like to add a type to the task?(y/n):");
        input = reader.readLine();
        if (input.equals("y")) type = true;
        System.out.println("Please enter the date of the task you want logged:");
        System.out.println("I can only handle dates in this format: yyyy-mm-dd");
        while ((input = reader.readLine()) != null){
            String[] content = {" "," "," "};
            content[0] = input;
            if (type == true){
                System.out.println("Please enter the task type:");
                input = reader.readLine();
                content[1] = input;
            }
            System.out.println("Please enter the task:");
            input = reader.readLine();
            content[2] = input;
            storeContents(this.writeContents, content);
            System.out.println("Would you like to add another task?(y/n):");
            input = reader.readLine();
            if (input.equals("n")) break;
            System.out.println("Please enter the date of the task you want logged:");
            System.out.println("I can only handle dates in this format: yyyy-mm-dd");
        }
        System.out.println("Writing to file.....");
        for (String[] element : this.writeContents){
            printWriter.printf("%s|%s|%s \n", element[0], element[1], element[2]);
        }
        printWriter.close();
        System.out.println("Writing complete. Good Bye.");
    }
    
    public void runLogger() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Sql sql = new Sql();
            String fileName = getFile(reader.readLine());
            String input;
            System.out.println("Do you want to upload your file to the database?(y/n):");
            input = reader.readLine();
            if (input.equals("y")) sql.changeUpload();
            if (sql.getUpload()) readFile(fileName);
            writeFile(fileName, reader);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
