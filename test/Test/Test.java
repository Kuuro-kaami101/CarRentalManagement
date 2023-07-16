package Test;

import DAO.DAO;
import entity.*;
import java.text.ParseException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws ParseException {
        DAO dao = new DAO();
//        List<Car> listCarSearch = dao.getAvailableCars(1, "", "");
//        List<Car> listCarByCate = dao.getAllCarByCategoryId(1);
//        List<Car> listCommon = dao.findCommonCar(listCarSearch, listCarByCate);
//        for (Car c : listCarSearch){
//            System.out.println(c.toString());
//        }
//        
//        System.out.println("================");
//        
//        for (Car c : listCarByCate){
//            System.out.println(c.toString());
//        }
//        
//        System.out.println("================");
//        
//        for (Car c : listCommon){
//            System.out.println(c.toString());
//        }

        dao.deleteImage("C:\\Users\\Kuro\\Downloads\\Project_Final_v2\\web\\images\\cars", "car16.jpg");
    }
}
