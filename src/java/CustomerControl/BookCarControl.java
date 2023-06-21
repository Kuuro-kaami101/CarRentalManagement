package CustomerControl;


import Service.*;
import entity.*;
import CarDAO.*;
import RentalDAO.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@WebServlet(urlPatterns = {"/BookCarControl"})
public class BookCarControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        CarDAO cdao = new CarDAO();
        RentalDAO rdao = new RentalDAO();
        RentalService service = new RentalService();
        HttpSession session =request.getSession();
        
        Customer cus =(Customer) session.getAttribute("cus");
        Car car =(Car) session.getAttribute("detail");
        List<Location> listlocation = cdao.getAllLocation();
        String cusID = request.getParameter("cusID");
        if(cusID == ""){
            //response.sendRedirect("login");
            request.setAttribute("loginnow","Vui long dang nhap");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }else if (cusID != ""){
            int cusID_raw = Integer.parseInt(cusID);
            int rentalId = 1;
            for (Rental r : rdao.getAllRental()) {
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
            
            int totalCost = Integer.parseInt(request.getParameter("cost"));
            String status = "uncheck";
            if (service.addRental(rentalId, cusID_raw, startDate, endDate, locationID, totalCost, status)){
                int rentalItemId = 1;
                int carId = Integer.parseInt(request.getParameter("carId"));
                for (RentalItem r : rdao.getAllRentalItem()) {
                    rentalItemId++;
                }
                RentalItem rt = new RentalItem(rentalItemId, rentalId, carId);
                rdao.addRentalItem(rt);
                response.sendRedirect("home");
            }else{
                System.out.println("false - not save");
                //nếu lỗi thì sẽ có thông báo rồi trả về trang detail
                request.setAttribute("bookError", "Book fail!");
                RequestDispatcher rd = request.getRequestDispatcher("detail?carId=" + car.getCarId());
                rd.include(request, response);
            }
            //Rental rental = new Rental(rentalId, cusID_raw, startDate, endDate, locationID, totalCost, status);
            //dao.addRental(rental);
/*            int rentalItemId = 1;
            int carId = Integer.parseInt(request.getParameter("carId"));
            for (RentalItem r : dao.getAllRentalItem()) {
                rentalItemId++;
            }
            RentalItem rt = new RentalItem(rentalItemId, rentalId, carId);
            dao.addRentalItem(rt);
            response.sendRedirect("home");*/
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookCarControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(BookCarControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
