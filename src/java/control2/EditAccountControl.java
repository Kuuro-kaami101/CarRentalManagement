package control2;

import dao.DAO;
import neww.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name="EditAccountControl", urlPatterns={"/editA"})
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EditAccountControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int id = a.getId();
        try {
            DAO dao = new DAO();
            Account account = dao.getAccountByID(id);
            request.setAttribute("detail", account);           
            request.getRequestDispatcher("EditAccount.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditAccountControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        DAO dao = new DAO();
        dao.editAccount(name,user,pass,id);
        response.sendRedirect("logout");     
    }
}
