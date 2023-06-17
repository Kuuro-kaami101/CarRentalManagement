/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control2;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thinh
 */
@WebServlet(name = "UpdateCustomer", urlPatterns = {"/UpdateCustomer"})
public class UpdateCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setAttribute("id", id);
        request.getRequestDispatcher("UpdateCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String driverLicenseNumber = request.getParameter("driverLicenseNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DAO dao = new DAO();
        try {
            dao.UpdateCustomer(id,fullName,email,phone,driverLicenseNumber,username,password);
            response.sendRedirect("ManageCustomerControl");
        } catch (SQLException ex) {
            response.sendRedirect("ManageCustomerControl");
        }
    }
}
