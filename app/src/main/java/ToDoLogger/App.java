package ToDoLogger;

public class App {
    public static void main(String[] args) {
        Log logger = new Log();
        System.out.println("Welcome to To Do Logger.");
        System.out.println("Please enter the name of your To Do Log (Press enter for default name Log.txt):");
        logger.runLogger(logger);
    }
}
