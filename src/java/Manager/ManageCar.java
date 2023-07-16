package Manager;

import DAO.DAO;
import entity.Car;
import entity.Category;
import entity.Location;
import entity.Carinfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageCar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        List<Car> listCar = dao.getAllCars();
        request.setAttribute("listCar", listCar);
        List<Category> listCategory = dao.getAllCategories();
        request.setAttribute("listCategory", listCategory);
        List<Location> listLocation = dao.getAllLocations();
        request.setAttribute("listLocation", listLocation);
        List<Carinfo> listCarinfo = dao.getAllCarinfo();
        request.setAttribute("listCarinfo", listCarinfo);
        request.getRequestDispatcher("ManageCar.jsp").forward(request, response);
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
