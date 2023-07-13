package Admin;

import AccountDAO.AccountDAO;
import CarDAO.*;
import DAO.DAO;
import RentalDAO.*;
import entity.Car;
import entity.Customer;
import entity.Rental;
import entity.Personnel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="ManageStatisticControl", urlPatterns={"/ManageStatisticControl"})
public class ManageStatistic extends HttpServlet {
   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        List<Personnel> listStaff = dao.getAllStaffs();
        int staff = listStaff.size();
        List<Car> listCar = dao.getAllCars();
        int car = listCar.size();
        List<Customer> listCustomer = dao.getAllCustomers();
        int customer = listCustomer.size();
        List<Personnel> listWorker = dao.getAllStaffs();
        int worker = listWorker.size();
        List<Rental> listRental = dao.getAllRentals();
        int rental = listRental.size();
        request.setAttribute("Rental", rental);
        request.setAttribute("Worker", worker);
        request.setAttribute("Staff", staff);
        request.setAttribute("Car", car);
        request.setAttribute("Customer", customer);
        request.getRequestDispatcher("ManageStatistic.jsp").forward(request, response);
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
