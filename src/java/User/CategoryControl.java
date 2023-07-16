package User;

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

@WebServlet(name = "CategoryControl", urlPatterns = {"/category"})
public class CategoryControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int cateID = Integer.parseInt(request.getParameter("cid"));
        DAO dao = new DAO();
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Location> listLocation = dao.getAllLocations();
        List<Car> listCarSearch = dao.getAvailableCars(locationId, startDate, endDate);
        List<Car> listCarByCate = dao.getAllCarByCategoryId(cateID);
        List<Car> listCommon = dao.findCommonCar(listCarSearch, listCarByCate);
        List<Category> listCategory = dao.getAllCategories();
        request.setAttribute("listCar", listCommon);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listLocation", listLocation);
        request.setAttribute("tag", cateID);
        request.setAttribute("locationId", locationId);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
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
