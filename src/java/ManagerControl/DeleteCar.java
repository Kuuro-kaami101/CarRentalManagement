/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ManagerControl;

import entity.*;
import CarDAO.*;
import RentalDAO.RentalDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Anh Tuan
 */
@WebServlet(name = "DeleteCar", urlPatterns = {"/delete"})
public class DeleteCar extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteCar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteCar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String id_raw =request.getParameter("carId");
        HttpSession session =request.getSession();
        int id,count=0,save=0;
        try {
            id=Integer.parseInt(id_raw);
            CarDAO cdao =new CarDAO();
            RentalDAO rdao = new RentalDAO();
            Car car =(Car) session.getAttribute("listCar");
            List<RentalItem> rt = rdao.getAllRentalItem();
            for(int i=0;i<rt.size();i++){
                if (id== rt.get(i).getCarId()){
                    count++;
                    save=i;
                }
            }
            if (count==0){
                request.setAttribute("deletecar","Do you want to delete Car?");
                cdao.deleteCarbyID(id);
                response.sendRedirect("ManageCarControl");
                
            }
            else{
                request.setAttribute("deletecarbook","The vehicle was booked by a customer. Do you want to delete it?");
                rdao.deleteRetalItembyID(id);
                rdao.deleteRetalbyID(save);
                response.sendRedirect("ManageCarControl");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(e);
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
        processRequest(request, response);
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
