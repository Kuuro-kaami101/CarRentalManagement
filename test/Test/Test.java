package Test;

import DAO.DAO;
import entity.*;
import java.text.ParseException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws ParseException {
        DAO dao = new DAO();
        Rental list = dao.getRentalByRentalId(4);
            System.out.println(list.toString());
        
    }
}
