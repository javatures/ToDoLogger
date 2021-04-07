package ToDoLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Log extends Operations {

    public void blowUp (){
        System.out.println("          _ ._  _ , _ ._");
        System.out.println("        (_ ' ( `  )_  .__)");
        System.out.println("      ( (  (    )   `)  ) _)");
        System.out.println("     (__ (_   (_ . _) _) ,__)");
        System.out.println("         `~~`\\ ' . /`~~`");
        System.out.println("              ;   ;");
        System.out.println("              /   \\");
        System.out.println("_____________/_ __ \\_____________");
        System.exit(0);
    }

    public String getFile(String input) throws FileNotFoundException, IOException {
        File file = new File(input);
        if (file.createNewFile()) {
            System.out.println("File does not exist. Creating new file..... ");
        }
        return input;
    }

    public List<String[]> readFile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String[]> uploadContents = new ArrayList<String[]>();
        String line;
        String[] data;
        boolean validDate;
        System.out.println("Reading File.....");

        while ((line = reader.readLine()) != null) {
            if (line.length() == 0) {
                continue;
            }
            data = line.split("\\|", 3);
            validDate = checkDate(data[0]);
            if (validDate) {
                uploadContents.add(data);
            }
        }
        reader.close();
        return uploadContents;
    }

    public void writeFile(String fileName, BufferedReader reader) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true));
        List<String[]> writeContents = new ArrayList<String[]>();
        String input;
        boolean validDate;
        System.out.println("Please enter the date of the task you want logged:");
        System.out.println("I can only handle dates in this format: yyyy-mm-dd");
        while ((input = reader.readLine()) != null) {
            String[] content = { "", "", "" };
            content[0] = input;
            System.out.println("Please enter the task type:");
            input = reader.readLine();
            content[1] = input;
            System.out.println("Please enter the task:");
            input = reader.readLine();
            content[2] = input;
            writeContents.add(content);
            System.out.println("Would you like to add another task?(y/n):");
            input = reader.readLine();
            if (input.equals("n"))
                break;
            System.out.println("Please enter the date of the task you want logged:");
            System.out.println("I can only handle dates in this format: yyyy-mm-dd");
        }
        System.out.println("Writing to file.....");
        for (String[] content : writeContents) {
            validDate = checkDate(content[0]);
            if (validDate)
                printWriter.printf("%s|%s|%s \n", content[0], content[1], content[2]);
        }
        printWriter.close();
        System.out.println("Writing complete. Good Bye.");
    }

    public void writeFromDatabase (List<String[]> downloadList, String fileName) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, true));
        for (String[] content : downloadList) {
            printWriter.printf("%s|%s|%s \n", content[0], content[1], content[2]);
        }
        printWriter.close();
    }
}
