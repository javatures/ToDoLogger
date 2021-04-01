package ToDoLogger;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

public class Operations {
    //Checks that the date is valid
    /**
     * Bugs: Does not work properly for leap year
     * @param dateData
     * @return boolean
    */
    public boolean checkDate(String dateData){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.US).withResolverStyle(ResolverStyle.STRICT);
        try {
            // LocalDate date = LocalDate.parse(dateData, dateFormatter);
            dateFormatter.parse(dateData);
            System.out.println(dateData);
        } catch (DateTimeException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        return true;
    }

    public void runSort(){

    }
}
