package Manager;

import entity.*;
import DAO.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "DeleteCar", urlPatterns = {"/delete"})
public class DeleteCar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String folder = "C:\\Users\\Kuro\\Downloads\\Project_Final_v2\\web\\images\\cars";
        String id_raw = request.getParameter("carId");
        int id, count=0, save=0;
        try {
            id = Integer.parseInt(id_raw);
            DAO dao = new DAO();
            List<Rental> rt = dao.getAllRentals();
            for(int i=0; i<rt.size(); i++){
                if (id == rt.get(i).getCarId()){
                    count++;
                    save = i;
                }
            }
            if (count==0){
                request.setAttribute("deletecar","Bạn có muốn xoá xe này không?");
                dao.deleteCarById(id);
//                dao.deleteImage(folder, dao.getCarById(id).getImage());
                response.sendRedirect("ManageCarControl");
            }
            else{
                request.setAttribute("deletecarbook","Xe đang được thuê bơi một khách hàng. Bạn có muốn xoá không?");
                dao.deleteRentalById(save);
                response.sendRedirect("ManageCarControl");
            }
            
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}
