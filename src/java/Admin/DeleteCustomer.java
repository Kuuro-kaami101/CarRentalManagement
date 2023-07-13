package Admin;

import DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(name = "DeleteCustomer", urlPatterns = {"/DeleteCustomer"})
public class DeleteCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String folder = "C:\\Users\\Kuro\\Downloads\\Project_Final_v2\\web\\images\\driver_licenses";
        String id = request.getParameter("id");
        DAO dao = new DAO();
        try {
            dao.deleteCustomer(id);
//            dao.deleteImage(folder, dao.getCustomerById(id).getDriverLicensePicture());
            request.getRequestDispatcher("/ManageCustomerControl").forward(request, response);
        } catch (SQLException ex) {
            request.getRequestDispatcher("/ManageCustomerControl").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}