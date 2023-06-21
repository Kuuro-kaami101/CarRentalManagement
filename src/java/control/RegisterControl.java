package control;

import AcountDAO.*;
import entity.Customer;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(name = "RegisterControl", urlPatterns = {"/register"})
public class RegisterControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        AcountDAO adao = new AcountDAO();
        int customerId = 1;
        for (Customer c : adao.getAllCustomer()) {
            customerId++;
        }
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String driverLicenseNumber = request.getParameter("driverLicenseNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String saveDirectory = "Drive license/"; // Specify the directory to save the uploaded picture
        String fileName = driverLicenseNumber + ".png"; // Specify the desired file name for the uploaded picture

        Part filePart = request.getPart("frontPicture"); // "picture" is the name of the input file element in the HTML form

        InputStream fileContent = filePart.getInputStream();
        Path filePath = Paths.get(saveDirectory, fileName);
        
        Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);

        Customer customer = new Customer(customerId, fullName, email, phone, driverLicenseNumber, username, password);
        adao.addCustomer(customer);
        response.sendRedirect("Login.jsp");
    }
}
