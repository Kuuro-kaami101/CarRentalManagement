package control;

import dao.DAO;
import entity.Admin;
import entity.Customer;
import entity.Manager;
import entity.Staff;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        DAO dao = new DAO();
        int a = dao.login(username, password);
        if (a == 0) {
            request.setAttribute("mess", "Sai tên đăng nhập hoặc mật khẩu");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            String fullname = null;
            if(a == 1){
                for (Customer c : dao.getAllCustomer()){
                    if (c.getUsername().equals(username))
                        fullname = c.getFullName();
                }
            }
            else if(a == 2){
                for (Manager c : dao.getAllManager()){
                    if (c.getUsername().equals(username))
                        fullname = c.getFullName();
                }
            }
            else if(a == 3){
                for (Admin c : dao.getAllAdmin()){
                    if (c.getUsername().equals(username))
                        fullname = c.getFullName();
                }
            }
            else{
                for (Staff c : dao.getAllStaff()){
                    if (c.getUsername().equals(username))
                        fullname = c.getFullName();
                }
            }
            session.setAttribute("fullname", fullname);
            session.setAttribute("acc", a);
            session.setMaxInactiveInterval(1000);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
