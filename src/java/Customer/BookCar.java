package Customer;

import Service.*;
import entity.*;
import DAO.DAO;
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

@WebServlet(urlPatterns = {"/BookCarControl"})
public class BookCar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        RentalService service = new RentalService();
        HttpSession session =request.getSession();
        
        Customer cus = (Customer) session.getAttribute("cus");
        Car car =(Car) session.getAttribute("car");
        String cusID = request.getParameter("cusID");
        if(cusID == ""){
            request.setAttribute("loginnow","Vui long dang nhap");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }else if (cusID != ""){
            String startDate = request.getParameter("check-in");
            String endDate = request.getParameter("check-out");
            int locationID = car.getLocationId();
            int totalCost = Integer.parseInt(request.getParameter("cost"));
            String status = "Chưa xác nhận";
            if (service.addRental(cusID, car.getCarId(),startDate, endDate, locationID, totalCost, status)){
                response.sendRedirect("home");
            }else{
                System.out.println("false - not save");
                request.setAttribute("bookError", "Book fail!");
                RequestDispatcher rd = request.getRequestDispatcher("detail?carId=" + car.getCarId());
                rd.include(request, response);
            }
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
            Logger.getLogger(BookCar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookCar.class.getName()).log(Level.SEVERE, null, ex);
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
