package User;

import DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

@WebServlet(name = "EditAccount", urlPatterns = {"/editAccount"})
public class EditAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String customerId = request.getParameter("customerId");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String driverLicenseNumber = request.getParameter("driverLicenseNumber");
        String driverLicensePicture = customerId + ".jpg";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Part filePart = request.getPart("newPicture");
        String saveDirectory =  "C:\\Users\\Kuro\\Downloads\\Project_Final_v2\\web\\images\\driver_licenses";
        File file = new File(saveDirectory, driverLicensePicture);
        filePart.write(file.getAbsolutePath());
        
        DAO dao = new DAO();
        dao.updateInfo(customerId, fullName, email, phone, driverLicenseNumber);
        request.setAttribute("cus", dao.getCustomerById(customerId));
        request.getRequestDispatcher("ManageAccount.jsp").forward(request, response);
    }
}
