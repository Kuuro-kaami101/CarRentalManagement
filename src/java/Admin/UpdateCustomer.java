package Admin;

import DAO.DAO;
import entity.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UpdateCustomer", urlPatterns = {"/UpdateCustomer"})
public class UpdateCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Customer cus = dao.getCustomerById(request.getParameter("id"));
        request.setAttribute("cus", cus);
        request.getRequestDispatcher("UpdateCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("customerId");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String driverLicenseNumber = request.getParameter("driverLicenseNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DAO dao = new DAO();
        try {
            dao.updateCustomer(id,fullName,email,phone,driverLicenseNumber,username,password);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("ManageCustomerControl");
    }
}