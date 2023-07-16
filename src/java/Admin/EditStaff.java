package Admin;

import DAO.DAO;
import entity.Personnel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validate.Validate;

public class EditStaff extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Personnel staff = dao.getPersonnelById(request.getParameter("staff_id"));
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("EditStaff.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        Validate validate = new Validate();
        String id = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Personnel staff = dao.getPersonnelById(id);
        request.setAttribute("staff", staff);

        if (!validate.checkEmail(email)) {
            String alert = "Email wrong format";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("EditStaff.jsp").forward(request, response);

        } else if (!validate.checkPhone(phone)) {
            String alert = "Phone wrong format";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("EditStaff.jsp").forward(request, response);
        } else {
            int i = dao.updatePersonnel(id, fullName, email, phone);
            if (i == 0) {
                String alert = "Cannot update";
                request.setAttribute("alert", alert);
                request.getRequestDispatcher("EditStaff.jsp").forward(request, response);
            } else {
                response.sendRedirect("ManageStaffControl");
            }
        }
    }
}
