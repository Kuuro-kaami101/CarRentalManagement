package dao;

import context.DBContext;
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

public class DAO implements DBContext {

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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public  List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        try ( Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);  Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM Customers";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String driverLicenseNumber = resultSet.getString("driver_license_number");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Customer customer = new Customer(customerId, fullName, email, phone, driverLicenseNumber, username, password);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public void deleteCusbyID(int id) {
        String sql = "DELETE FROM [dbo].[Customers]\n" + "WHERE customer_id=?";
        try ( Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public  List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        try ( Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);  Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM Staffs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                int role_id = resultSet.getInt("role_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Staff s = new Staff(staff_id, role_id, username, password, fullName, email, phone);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public  List<Staff> getAllWorker() {
        List<Staff> list = new ArrayList<>();
        try ( Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);  Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM Staffs where role_id = 3";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Staff s = new Staff(staff_id, 3, username, password, fullName, email, phone);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "INSERT INTO Customers (customer_id, full_name, email, phone, driver_license_number, username, password) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getFullName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getDriverLicenseNumber());
            statement.setString(6, customer.getUsername());
            statement.setString(7, customer.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int login(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            if (isCustomerAccount(connection, username, password)) {
                return 4;
            }
            if (isStaffAccount(connection, username, password)) {
                for (Staff s : getAllStaff()) {
                    if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                        return s.getRole_id();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private boolean isCustomerAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM Customers WHERE username = ? AND password = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    //Lay thong tin cua account dua tren username va password
    public Customer getUserInfo(String username, String password) {
        String query = "select * from Customers where username = ? and password = ?";
        Customer customer = new Customer();
        try ( Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    private boolean isStaffAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM Staffs WHERE username = ? AND password = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void addRental(Rental rental) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  int calculateTotalCost(int carId, java.sql.Date startDate, java.sql.Date endDate) {
        int totalCost = 0;
        Connection connection = null;
        try {
            connection = getConnect();
            // Prepare the SQL statement
            String sql = "{? = call dbo.CalculateTotalCost(?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(sql);
            // Set the input parameters
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setInt(2, carId);
            statement.setDate(3, (java.sql.Date) startDate);
            statement.setDate(4, (java.sql.Date) endDate);

            // Execute the function
            statement.execute();

            // Retrieve the output parameter
            totalCost = statement.getInt(1);

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalCost;
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  void main(String[] args) {

    }
}
