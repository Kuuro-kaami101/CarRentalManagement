package control;

import dao.DAO;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Car> listCar = dao.getAllCarByLocationAndDate(locationId, startDate, endDate);
        List<Category> listCategory = dao.getAllCategory();
        request.setAttribute("listCar", listCar);
        request.setAttribute("listCategory", listCategory);
        List<Location> listlocation = dao.getAllLocation();
        request.setAttribute("listlocation", listlocation);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
}
