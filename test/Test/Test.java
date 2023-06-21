package Test;

import CarDAO.*;
import entity.Car;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Test {

    public static void main(String[] args) throws ParseException {
        CarDAO cdao = new CarDAO();
        List<Car> list = cdao.getAllCarByLocationAndDate(1,"2023-09-01", "2023-09-05");
        for (Car c : list){
           System.out.println(c.toString());
        }
    }
    
}
