package User;

import DAO.DAO;
import entity.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

@WebServlet(name = "RegisterControl", urlPatterns = {"/register"})
public class RegisterControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        List<Customer> list = dao.getAllCustomers();
        int order = 1;
        if (!list.isEmpty()) {
            for (Customer c : list) {
                order++;
            }
        }

        String fullName = request.getParameter("name");
        String id = dao.generateCustomerID();
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String driverLicenseNumber = request.getParameter("driverLicense");
        String driverLicensePicture = id + ".jpg";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Part filePart = request.getPart("driverLicensePicture");
        String saveDirectory = "C:\\Users\\Kuro\\Downloads\\Project_Final_v2\\web\\images\\driver_licenses";
        File file = new File(saveDirectory, driverLicensePicture);
        filePart.write(file.getAbsolutePath());

        if (dao.checkDuplicateUsername(username, list)) {
            request.setAttribute("mess", "Trùng tên đăng nhập. Vui lòng dùng tên khác.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else if (dao.checkDuplicateEmail(email, list)) {
            request.setAttribute("email", "Email đã được sử dụng.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else if (dao.checkDuplicatePhone(phone, list)) {
            request.setAttribute("phone", "Số điện thoại đã được sử dụng.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            Customer customer = new Customer(id, fullName, email, phone, driverLicenseNumber, driverLicensePicture, username, password, 1);
            dao.addCustomer(customer);
            response.sendRedirect("Login.jsp");
        }
    }
}
