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

public class DAO implements DBContext {
    
    public static Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static List<Car> getAllCar() {
        List<Car> list = new ArrayList<>();
        String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Cars.detail, Cars.registration_number, Locations.address, Cars.image, Cars.price_per_day, Cars.status\n" +
                       "FROM Cars\n" +
                       "JOIN Categories ON Cars.category_id = Categories.category_id\n" +
                       "JOIN Locations ON Cars.location_id = Locations.location_id;";
        try (Connection con = getConnect()) {
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
    
    public static List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
             Statement statement = connection.createStatement()) {
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
    
    public static List<Staff> getAllStaff(){
        List<Staff> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
             Statement statement = connection.createStatement()) {
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
    
    public static List<Staff> getAllWorker(){
        List<Staff> list = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
             Statement statement = connection.createStatement()) {
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
     
    public static List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Categories";
        try (Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement(query);
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
    
    public static List<Car> getCarByCategoryID(String cid) {
    List<Car> list = new ArrayList<>();
    String query = "SELECT Cars.car_id, Cars.name AS car_name, Categories.title AS category_title, Locations.address, Cars.detail, Cars.registration_number, Cars.image, Cars.price_per_day, Cars.status\n" +
                   "FROM Cars \n" +
                   "JOIN Categories ON Cars.category_id = Categories.category_id \n" +
                   "JOIN Locations ON Cars.location_id = Locations.location_id\n" +
                   "WHERE Categories.category_id = ?";
    try (Connection con=getConnect()) {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cid);
        ResultSet rs=ps.executeQuery();
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
            String query = "INSERT INTO Customers (customer_id, full_name, email, phone, driver_license_number, username, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
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
    
     public int login(String username, String password){
        try {
            Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            if (isCustomerAccount(connection, username, password)) {
                return 4;
            }
            if (isStaffAccount(connection, username, password)){
                for (Staff s : getAllStaff()){
                    if (s.getUsername().equals(username) && s.getPassword().equals(password)){
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
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        }
    }
    
    private boolean isStaffAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM Staffs WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    
    public static void main(String[] args) {
        
    }
}
