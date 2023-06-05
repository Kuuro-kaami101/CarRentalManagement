/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import dao.*;
import entity.*;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
public class RentalService {
    private RentalDAO rentaldao = new RentalDAO();
    private DAO cardao = new DAO();
    
    public List<Rental> displayAllRental(){
        return rentaldao.viewAllRental();
    }
    public List<Rental> displayRentalssByID(int id) {
        return rentaldao.viewRentalByAccID(id);
    }

    public boolean deleteRental(int id) {
        //lấy thông tin order dựa vào id
        Rental list = rentaldao.viewOrderByRentalID(id);
        //lấy thông tin của hotel dựa vào id có trong order
        Car c = cardao.getCar(list.getCarid());

        //Khi hủy hóa đơn thì số phòng sẽ tăng lại về ban đầu
        //c.updateHotel(h);
        return rentaldao.deleterental(id);
    }

    public boolean saveRental(int customerid,int carid,String check_in,String check_out,double cost,String status) throws ParseException{
        //String date trả về của check in và check out là -> YYYY - MM - DD
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");

        //Format string về kiểu date
        Date inDate = format.parse(check_in);
        Date outDate = format.parse(check_out);

        //tính giá tiền phòng dựa vào số ngày * số phòng *  giá
        double sumcost = (((outDate.getTime() - inDate.getTime()) //tính ra được số ngày đặt -> trả về dưới dạng miliseconds
                / (1000 * 60 * 60 * 24)) % 365)*cost // chuyển về ngày
                ;

        System.out.println("cost: " + sumcost);
        System.out.println("in: " + check_in + "\nout: " + check_out);

        //Check thử xem ngày check in < check out ko
        // compareTo trả về 0 -> cùng ngày
        // compareTo trả về 1 -> check in > check out
        // compareTo trả về -1 -> check in < check out
        if (check_in.compareTo(check_out) >= 0) {
            return false;
        } else {
            return rentaldao.saveRental(new Rental(customerid, carid,check_in,check_out, sumcost,status));
        }

    }
}
