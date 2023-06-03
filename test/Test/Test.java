package Test;

import dao.DAO;
import entity.Car;
import entity.Staff;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception  {       
        DAO dao = new DAO();
        List<Car> list = dao.getAllCar();
        for (Car o: list){
        System.out.println(o.toString());
        }
    }
}
