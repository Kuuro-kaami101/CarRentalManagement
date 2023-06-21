package duc;

import control.*;
import dao.DAO;
import entity.Car;
import entity.Customer;
import entity.Rental;
import entity.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name="ManageStatisticControl", urlPatterns={"/ManageStatisticControl"})
public class ManageStatisticControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        List<Staff> listStaff = dao.getAllStaff();
        //PrintWriter out = response.getWriter();
        //out.println("<html><p>Tổng số nhân viên</p></html>");
        int staff = listStaff.size();
        //out.println(nhanvien);
        List<Car> listCar = dao.getAllCar();
        //out.println("<html><p>Tổng số xe</p></html>");
        int car = listCar.size();
        //out.println(xe);
        List<Customer> listCustomer = dao.getAllCustomer();
        int customer = listCustomer.size();
        List<Staff> listWorker = dao.getAllWorker();
        int worker = listWorker.size();
        List<Rental> listRental = dao.getAllRental();
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
