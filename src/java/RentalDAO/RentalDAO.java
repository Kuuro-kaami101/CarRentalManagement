
package RentalDAO;
import context.DBContext;
import static context.DBContext.DBURL;
import static context.DBContext.DRIVERNAME;
import static context.DBContext.PASSDB;
import static context.DBContext.USERDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Car;
import entity.Category;
import entity.Customer;
import entity.Staff;
import java.sql.Statement;
import entity.*;
import java.sql.CallableStatement;
import java.sql.Types;
public class RentalDAO {
    public  Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    public  List<Rental> getAllRental() {
        List<Rental> list = new ArrayList<>();
        String query = "select * from Rentals";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Rental(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7)));
            }
        } catch (Exception ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<RentalItem> getAllRentalItem() {
        List<RentalItem> list = new ArrayList<>();
        String query = "select * from RentalItems";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new RentalItem(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)));
            }
        } catch (Exception ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<RentalShow> showRentalById(int id) {
        List<RentalShow> list = new ArrayList<>();
        String query = "SELECT Rentals.rental_id, Customers.full_name AS customer_name, Rentals.rental_start_date, Rentals.rental_end_date,\n"
                + "Locations.address, Cars.name AS car_name, Cars.image AS car_picture, Rentals.total_cost, rental_status\n"
                + "FROM Rentals\n"
                + "INNER JOIN Customers ON Rentals.customer_id = Customers.customer_id\n"
                + "INNER JOIN Locations ON Rentals.location_id = Locations.location_id\n"
                + "INNER JOIN RentalItems ON Rentals.rental_id = RentalItems.rental_id\n"
                + "INNER JOIN Cars ON RentalItems.car_id = Cars.car_id\n"
                + "where Rentals.customer_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new RentalShow(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
        } catch (Exception ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean addRental(Rental rental) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "INSERT INTO Rentals "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, rental.getRentalId());
            statement.setInt(2, rental.getCustomerId());
            statement.setString(3, rental.getStartDate());
            statement.setString(4, rental.getEndDate());
            statement.setInt(5, rental.getLocationId());
            statement.setInt(6, rental.getTotalCost());
            statement.setString(7, rental.getRentalStatus());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in save order: " + e.getMessage());
            return false;
        }
    }
    public void addRentalItem(RentalItem rt) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "insert into RentalItems values(?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, rt.getRentalItemId());
            statement.setInt(2, rt.getRentalId());
            statement.setInt(3, rt.getCarId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RentalShow> showRental() {
        List<RentalShow> list = new ArrayList<>();
        String query = "SELECT Rentals.rental_id, Customers.full_name AS customer_name, Rentals.rental_start_date, Rentals.rental_end_date,\n"
                + "Locations.address, Cars.name AS car_name, Cars.image AS car_picture, Rentals.total_cost, rental_status\n"
                + "FROM Rentals\n"
                + "INNER JOIN Customers ON Rentals.customer_id = Customers.customer_id\n"
                + "INNER JOIN Locations ON Rentals.location_id = Locations.location_id\n"
                + "INNER JOIN RentalItems ON Rentals.rental_id = RentalItems.rental_id\n"
                + "INNER JOIN Cars ON RentalItems.car_id = Cars.car_id;";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new RentalShow(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
        } catch (Exception ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public  void updateRentalStatus(int rentalId) {
        String query = "UPDATE Rentals SET rental_status = N'Unpay' WHERE rental_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, rentalId);
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
