package User;

import DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cusId = request.getParameter("customerId");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        DAO dao = new DAO();
        if (!dao.getCustomerById(cusId).getPassword().equals(currentPassword)) {
            request.setAttribute("mess", "Nhập sai mật khẩu hiện tại");
            request.getRequestDispatcher("ManageAccount.jsp").forward(request, response);
        } else {
            dao.changePassword(cusId, newPassword);
            request.setAttribute("cus", dao.getCustomerById(cusId));
            request.getRequestDispatcher("ManageAccount.jsp").forward(request, response);
        }
    }
}
