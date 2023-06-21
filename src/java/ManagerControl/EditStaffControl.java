/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ManagerControl;

import AcountDAO.*;
import entity.Staff;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import validate.Validate;

/**
 *
 * @author Anh Tuan
 */
public class EditStaffControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        processRequest(request, response);
        AcountDAO adao = new AcountDAO();
        Staff staff = adao.getStaff(request.getParameter("staff_id"));
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("EditStaff.jsp").forward(request, response);
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
        processRequest(request, response);
        AcountDAO adao = new AcountDAO();
        Validate validate = new Validate();
        int id = Integer.parseInt(request.getParameter("id"));

        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Staff staff = adao.getStaff(id + "");
        request.setAttribute("staff", staff);

        if (!validate.checkEmail(email)) {
            String alert = "Email wrong format";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("EditStaff.jsp").forward(request, response);

        } else if (!validate.checkPhone(phone)) {
            String alert = "Phone wrong format";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("EditStaff.jsp").forward(request, response);
        } else {
            int i = adao.updateStaff(id, fullName, email, phone);
            if (i == 0) {
                String alert = "Cannot update";
                request.setAttribute("alert", alert);
                request.getRequestDispatcher("EditStaff.jsp").forward(request, response);
            } else {
                response.sendRedirect("ManageStaffControl");
            }
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
