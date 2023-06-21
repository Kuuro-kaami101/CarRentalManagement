package CarDAO;

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

public class CarDAO {
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

    public List<Car> getAllCar() {
        List<Car> list = new ArrayList<>();
        String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Cars.detail, Cars.registration_number, Locations.address, Cars.image, Cars.price_per_day, Cars.status\n"
                + "FROM Cars\n"
                + "JOIN Categories ON Cars.category_id = Categories.category_id\n"
                + "JOIN Locations ON Cars.location_id = Locations.location_id\n";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Car(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Car> getAllCarByLocationAndDate(int locationId, String startDate, String endDate) {
        List<Car> list = new ArrayList<>();
        String query = "SELECT c.car_id, c.name, ca.title, c.detail, c.registration_number, l.address, c.image, c.price_per_day, c.status\n"
                + "FROM cars c\n"
                + "JOIN Locations l ON c.location_id = l.location_id\n"
                + "JOIN Categories ca ON c.category_id = ca.category_id\n"
                + "LEFT JOIN RentalItems ri ON c.car_id = ri.car_id\n"
                + "LEFT JOIN Rentals r ON ri.rental_id = r.rental_id\n"
                + "    WHERE NOT EXISTS (\n"
                + "    SELECT 1\n"
                + "    FROM Rentals r\n"
                + "    INNER JOIN RentalItems ri ON r.rental_id = ri.rental_id\n"
                + "    WHERE ri.car_id = c.car_id\n"
                + "    AND (\n"
                + "         r.rental_start_date <= '"+startDate+ "' AND r.rental_end_date >= '"+startDate+"'\n"
                + "        OR r.rental_start_date <= '"+endDate+"' AND r.rental_end_date >= '"+endDate+"'\n"
                + "        OR r.rental_start_date >= '"+startDate+"' AND r.rental_end_date <= '"+endDate+"'\n"
                + "    )\n"
                + ")\n"
                + "AND l.location_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, locationId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Car(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addCar(String name, String category, String detail, String regisnumString, String location,
            String image, String price, String status) {
        String sql = "INSERT [dbo].[Cars]\n"
                + "([name],[category_id],[detail],[registration_number],[location_id],[image],[price_per_day],[status])\n"
                + "VALUES(?,?,?,?,?,?,?,?)";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setString(3, detail);
            ps.setString(4, regisnumString);
            ps.setString(5, location);
            ps.setString(6, image);
            ps.setString(7, price);
            ps.setString(8, status);
            ps.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public void deleteCarbyID(int id) {
        String sql = "DELETE FROM [dbo].[Cars]\n" + "WHERE car_id=?";
        try ( Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public  Car getCarByID(String id) {
        Car c = new Car();
        String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Locations.address, Cars.detail, Cars.registration_number, Cars.image, Cars.price_per_day, Cars.status\n"
                + "FROM Cars \n"
                + "JOIN Categories ON Cars.category_id = Categories.category_id \n"
                + "JOIN Locations ON Cars.location_id = Locations.location_id\n"
                + "WHERE Cars.car_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new Car(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9));
            }
        } catch (Exception ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public  Car getCarByID2(int cid) {
        Car c = new Car();
        String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Locations.address, Cars.detail, Cars.registration_number, Cars.image, Cars.price_per_day, Cars.status\n"
                + "FROM Cars \n"
                + "JOIN Categories ON Cars.category_id = Categories.category_id \n"
                + "JOIN Locations ON Cars.location_id = Locations.location_id\n"
                + "WHERE Cars.car_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, cid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String name = resultSet.getString("car_name");
                String categoryId = resultSet.getString("category_title");
                String detail = resultSet.getString("detail");
                String registrationNumber = resultSet.getString("registration_number");
                String locationId = resultSet.getString("address");
                String image = resultSet.getString("image");
                int pricePerDay = resultSet.getInt("price_per_day");
                String status = resultSet.getString("status");
                c = new Car(carId, name, categoryId, detail, registrationNumber, locationId, image, pricePerDay, status);
            }
            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void editCar(String image, int cost, String status, String detail, String carId) {
        String query = "UPDATE [dbo].[Cars]\n"
                + "   SET [image] =?\n"
                + "      ,[price_per_day] = ?\n"
                + "      ,[status] =?\n"
                + "       ,[detail] =?\n"
                + " WHERE [car_id] =?";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = null;
            ps = con.prepareStatement(query);
            ps.setString(1, image);
            ps.setInt(2, cost);
            ps.setString(3, status);
            ps.setString(4, detail);
            ps.setString(5, carId);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Car> getCarBylocation(String txtSearch) {
        List<Car> list = new ArrayList<>();
        String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Cars.detail,Cars.registration_number ,Locations.address , Cars.image, Cars.price_per_day, Cars.status\n"
                + "FROM Cars \n"
                + "JOIN Categories ON Cars.category_id = Categories.category_id \n"
                + "JOIN Locations ON Cars.location_id = Locations.location_id\n"
                + "WHERE Locations.address like ?";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Car(rs.getInt(1),
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
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Location> getAllLocation() {
        List<Location> list = new ArrayList<>();
        String query = "select * from Locations";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Location(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Location getLocationByID(String id) {
        Location l = new Location();
        String query = "SELECT * from Locations \n"
                + "WHERE location_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                l = new Location(rs.getInt(1),
                        rs.getString(2));
            }
        } catch (Exception ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Categories";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public  List<Car> getCarByCategoryID(String cid) {
        List<Car> list = new ArrayList<>();
        String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Locations.address, Cars.detail, Cars.registration_number, Cars.image, Cars.price_per_day, Cars.status\n"
                + "FROM Cars \n"
                + "JOIN Categories ON Cars.category_id = Categories.category_id \n"
                + "JOIN Locations ON Cars.location_id = Locations.location_id\n"
                + "WHERE Categories.category_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Car(rs.getInt(1),
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
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
