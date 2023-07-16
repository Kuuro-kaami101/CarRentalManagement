package Staff;

import DAO.DAO;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "ManageRentalControl", urlPatterns = {"/ManageRentalControl"})
public class ManageRental extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        String staffId = request.getParameter("staffId");
        List<Rental> listRental = dao.getAllRentals();
        List<Customer> listCustomer = dao.getAllCustomers();
        List<Location> listLocation = dao.getAllLocations();
        List<Car> listCar = dao.getAllCars();
        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("listLocation", listLocation);
        request.setAttribute("listRental", listRental);
        request.setAttribute("listCar", listCar);
        session.setAttribute("staffId", staffId);
        request.getRequestDispatcher("ManageRental.jsp").forward(request, response);
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
