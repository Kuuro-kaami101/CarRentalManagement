package CustomerControl;

import dao.DAO;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/BookCarControl"})
public class BookCarControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        HttpSession session =request.getSession();
        
        Customer cus =(Customer) session.getAttribute("cus");
        Car car =(Car) session.getAttribute("detail");
        List<Location> listlocation = dao.getAllLocation();
        String cusID = request.getParameter("cusID");
        if(cusID == ""){
            response.sendRedirect("login");
        }else if (cusID != ""){
            int cusID_raw = Integer.parseInt(cusID);
            int rentalId = 1;
            for (Rental r : dao.getAllRental()) {
                rentalId++;
            }
            String startDate = request.getParameter("check-in");
            String endDate = request.getParameter("check-out");
            String address = request.getParameter("address");
            int locationID=0;
            for(int i=0;i<listlocation.size();i++){
                if(address.equals(listlocation.get(i).getAddress())){
                locationID=listlocation.get(i).getLocationId();
                break;
            }
            }
            int carId = Integer.parseInt(request.getParameter("carId"));

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date1 = format.parse(startDate);
            java.util.Date date2 = format.parse(endDate);
            java.sql.Date sqlDate1 = new java.sql.Date(date1.getTime());
            java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
            int totalCost = dao.calculateTotalCost(carId, sqlDate1, sqlDate2);
            
            String status = "uncheck";
            Rental rental = new Rental(rentalId, cusID_raw, startDate, endDate, locationID, totalCost, status);
            dao.addRental(rental);
            int rentalItemId = 1;
            for (RentalItem r : dao.getAllRentalItem()) {
                rentalItemId++;
            }
            RentalItem rt = new RentalItem(rentalItemId, rentalId, carId);
            dao.addRentalItem(rt);
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookCarControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookCarControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
