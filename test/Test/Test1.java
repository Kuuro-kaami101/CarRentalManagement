package Test;

import dao.DAO;
import entity.Car;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Test1 {

    public static void main(String[] args) throws ParseException {
        DAO dao = new DAO();
        List<Car> list = dao.getAllCarByLocationAndDate(1,"2023-09-01", "2023-09-05");
        for (Car c : list){
           System.out.println(c.toString());
        }
    }
    
}
