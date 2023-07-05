package Service;

import entity.*;
import RentalDAO.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalService {
    private RentalDAO rdao = new RentalDAO();
    public boolean addRental (int rentalId,int customerId,String startDate, String endDate,int locationId,int totalCost,String rentalStatus ) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDate = convertToLocalDate(startDate);
        Date inDate = format.parse(startDate);
        Date outDate = format.parse(endDate);
        int cost =(int) ((((outDate.getTime() - inDate.getTime()) //tính ra được số ngày đặt -> trả về dưới dạng miliseconds
                / (1000 * 60 * 60 * 24)) % 365) // chuyển về ngày
                * totalCost) ;
        
        if(startDate.compareTo(endDate)>=0){
            return false;
        }else{
            if(firstDate.isEqual(currentDate)||firstDate.isAfter(currentDate)){
                return rdao.addRental(new Rental( rentalId,customerId, startDate, endDate, locationId, cost, rentalStatus));
            }
            return false;
        }
        
    }
    public static LocalDate convertToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, formatter);
    }

}
