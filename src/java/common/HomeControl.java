package common;

import DAO.DAO;
import entity.Car;
import entity.Category;
import entity.Location;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeControl", urlPatterns = {"/home"})
public class HomeControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        List<Car> listCar = dao.getAllCars();
        List<Location> listLocation = dao.getAllLocations();
        List<Category> listCategory = dao.getAllCategories();
        request.setAttribute("listCar", listCar);
        request.setAttribute("listLocation", listLocation);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("searchError", (String)request.getAttribute("searchError"));
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
