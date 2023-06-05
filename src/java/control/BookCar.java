/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import entity.*;
import Service.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anh Tuan
 */

public class BookCar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //Controller dùng để đặt phòng
            RentalService service = new RentalService();
            CarService carservice =new CarService();
            
            HttpSession session=request.getSession();
            //
            Car c=(Car) session.getAttribute("car");
            Customer cus =(Customer) session.getAttribute("cus");
            String check_in = request.getParameter("check-in");
            String check_out = request.getParameter("check-out");
            if (service.saveRental(cus.getCustomerId(), c.getCarId(), check_in, check_out,c.getCost(),c.getStatus())) {
                System.out.println("true - save");
                //trả về lại trang home
                response.sendRedirect("Home");
            } else {
                System.out.println("false - not save");
                //nếu lỗi thì sẽ có thông báo rồi trả về trang detail
                request.setAttribute("bookError", "Book fail!");
                RequestDispatcher rd = request.getRequestDispatcher("DetailCar?carId=" + c.getCarId());
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
        } catch (ParseException pe) {
            Logger.getLogger(BookCar.class.getName()).log(Level.SEVERE, null, pe);
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
        } catch (ParseException pe) {
            Logger.getLogger(BookCar.class.getName()).log(Level.SEVERE, null, pe);
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
