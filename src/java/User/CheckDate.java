package User;

import static User.RentalService.convertToLocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class CheckDate {
    public boolean Checkday(String startDate, String endDate) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDate = convertToLocalDate(startDate);
        Date inDate = format.parse(startDate);
        Date outDate = format.parse(endDate);
        if(startDate.compareTo(endDate)>=0){
            return false;
        }else{
            if(firstDate.isEqual(currentDate)||firstDate.isAfter(currentDate)){
                return true;
            }
            return false;
        }
    }
}
