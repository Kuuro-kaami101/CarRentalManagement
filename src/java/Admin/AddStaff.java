package Admin;

import DAO.DAO;
import entity.Personnel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddStaff", urlPatterns = {"/addStaff"})
public class AddStaff extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        String id = dao.generatePersonnelID();
        String name = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Personnel staff = new Personnel(id, 3, name, email, phone, username, password);
        dao.addStaff(staff);
        response.sendRedirect("ManageStaffControl");
    }
}
