package common;

import DAO.DAO;
import entity.Customer;
import entity.Personnel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        DAO dao = new DAO();
        Customer cus = dao.getUserInfo(username, password);
        if (cus.getUsername() != null) {
            session.setAttribute("cus", cus);
        }
        int a = dao.login(username, password);
        if (a == 0) {
            request.setAttribute("mess", "Sai tên đăng nhập hoặc mật khẩu");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            String fullname = null;
            if (a == 4) {
                for (Customer c : dao.getAllCustomers()) {
                    if (c.getUsername().equals(username)) {
                        fullname = c.getFullName();
                    }
                }
            } else {
                for (Personnel s : dao.getAllPersonnels()) {
                    if (s.getUsername().equals(username)) {
                        fullname = s.getFullName();
                        session.setAttribute("staff", s);
                    }
                }
            }
            
            List<Customer> list = dao.getAllCustomers();
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getUsername().compareTo(username) == 0) {
                    count++;
                }
            }
            if (count != 0) {
                if (cus.getStatus() == 0) {
                    request.setAttribute("mess", "Tai khoan cua ban da bi khoa");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }
            
            session.setAttribute("fullname", fullname);
            session.setAttribute("acc", a);
            session.setMaxInactiveInterval(-1);

            String referer = request.getHeader("Referer");
            session.setAttribute("referer", referer);

            response.sendRedirect("home");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
