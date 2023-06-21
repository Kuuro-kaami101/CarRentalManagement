package control;

import RentalDAO.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateRentalStatus", urlPatterns = {"/UpdateRentalStatus"})

public class UpdateRentalStatus extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rentalId = Integer.parseInt(request.getParameter("rentalId"));
        RentalDAO rdao = new RentalDAO();
        rdao.updateRentalStatus(rentalId);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
