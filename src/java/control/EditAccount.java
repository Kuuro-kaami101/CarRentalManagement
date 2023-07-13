package control;

import DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditAccount", urlPatterns = {"/editAccount"})
public class EditAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String customerId = request.getParameter("customerId");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String driverLicenseNumber = request.getParameter("driverLicenseNumber");
        String driverLicensePicture = customerId + ".jpg";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        DAO dao = new DAO();
        dao.updateInfo(customerId, fullName, email, phone, driverLicenseNumber);
        request.setAttribute("cus", dao.getCustomerById(customerId));
        request.getRequestDispatcher("ManageAccount.jsp").forward(request, response);
    }
}
