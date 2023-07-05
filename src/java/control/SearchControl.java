package control;

import CarDAO.CarDAO;
import CustomerControl.BookCarControl;
import entity.Car;
import Service.*;
import entity.Category;
import entity.Location;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SearchControl", urlPatterns = {"/SearchControl"})
public class SearchControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        CarDAO cdao = new CarDAO();
        CheckDate checkDate = new CheckDate();
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List<Car> listCar = cdao.getAllCarByLocationAndDate(locationId, startDate, endDate);
        List<Category> listCategory = cdao.getAllCategory();
        List<Location> listlocation = cdao.getAllLocation();
        if (checkDate.Checkday(startDate, endDate)) {
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            request.setAttribute("listCar", listCar);
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listlocation", listlocation);
            request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
        }else{
            request.setAttribute("searchError", "Search fail!");
            response.sendRedirect("home");
        }
        /*request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("listCar", listCar);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listlocation", listlocation);
        request.getRequestDispatcher("SearchResult.jsp").forward(request, response);*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SearchControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
