package ToDoLogger;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;

abstract class Operations {

    abstract void exitLogger();

    //Checks that the date is valid
    /**
     * Bugs: Does not work properly for leap year
     * @param dateData
     * @return boolean
    */
    public boolean checkDate(String dateData){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-m-d", Locale.US).withResolverStyle(ResolverStyle.STRICT);
        try {
            dateFormatter.parse(dateData);
            // System.out.println(dateData);
        } catch (DateTimeException e) {
            // System.out.println(e.getLocalizedMessage());
            return false;
        }

        return true;
    }

    public void runSort(List<String> list){

    }
}
