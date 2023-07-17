package Manager;

import DAO.DAO;
import entity.Car;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

public class AddCar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAO();
        String name = request.getParameter("name");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int carinfoId = Integer.parseInt(request.getParameter("carinfo"));
        String detail = request.getParameter("detail");
        String regisnum = request.getParameter("registrationNumber");
        int locationId = Integer.parseInt(request.getParameter("address"));
        String image = "car" + Integer.toString(dao.generateCarId()) + ".jpg";
        int price = Integer.parseInt(request.getParameter("price"));
        String status = "Available";
        
        String updatePictureValue = request.getParameter("updatePicture");
        if ("yes".equals(updatePictureValue)){
            Part filePart = request.getPart("newPicture");
            String saveDirectory =  "C:\\Users\\Kuro\\Downloads\\Project_Final_v2\\web\\images\\cars";
            File file = new File(saveDirectory, image);
            filePart.write(file.getAbsolutePath());
        }
        
        Car car = new Car(name, categoryId, carinfoId, detail, regisnum, locationId, image, price, status);
        dao.addCar(car);
        response.sendRedirect("ManageCarControl");
    }
}
