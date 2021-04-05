package ToDoLogger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

class AppTest {

    @BeforeEach 
    public void startTest(TestInfo testInfo) {
        System.out.printf("Starting %s: \n", testInfo.getDisplayName());
    }

    @Test 
    void readFileTest() throws FileNotFoundException, IOException{
        Log loggerTest = new Log();
        loggerTest.readFile("Log.txt");
        loggerTest.readFile("file.csv");
    }

    @Test
    void checkDateTest() {
        Log loggerTest = new Log();
        // assertTrue(loggerTest.checkDate("12/23/2020"));
        // assertTrue(loggerTest.checkDate("4/1/2021"));
        // assertTrue(loggerTest.checkDate("2/29/2021"));
        // assertTrue(loggerTest.checkDate("02/29/2020"));
        // assertTrue(loggerTest.checkDate("02/29/2016"));
        assertTrue(loggerTest.checkDate("2021-04-04"));
    }
}
