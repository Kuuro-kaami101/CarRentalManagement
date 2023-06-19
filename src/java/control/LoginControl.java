package control;

import dao.DAO;
import entity.Customer;
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
        HttpSession session = request.getSession();
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        DAO dao = new DAO();
        Customer cus = dao.getUserInfo(username, password);
        if(cus.getUsername()!=null){
            session.setAttribute("cus",cus);
        }
        int a = dao.login(username, password);
        if (a == 0) {
            request.setAttribute("mess", "Sai tên đăng nhập hoặc mật khẩu");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            String fullname = null;
            if(a == 4){
                for (Customer c : dao.getAllCustomer()){
                    if (c.getUsername().equals(username))
                        fullname = c.getFullName();
                }
            }
            else{
                for (Staff s : dao.getAllStaff()){
                    if (s.getUsername().equals(username))
                        fullname = s.getFullName();
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
