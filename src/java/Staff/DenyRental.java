package Staff;

import DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DenyRental", urlPatterns = {"/DenyRental"})

public class DenyRental extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int rentalId = Integer.parseInt(request.getParameter("rentalId"));
        String staffId = session.getAttribute("staffId").toString();
        DAO dao = new DAO();
        dao.denyRental(rentalId, staffId);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
