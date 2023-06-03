package dao;

import context.DBContext;
import entity.Admin;
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
import entity.Manager;
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
        String query = "select * from Cars order by car_id";
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Car(rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
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
    
    public static List<Admin> getAllAdmin() {
        List<Admin> adminList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM SystemAdministrators";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int adminId = resultSet.getInt("admin_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                Admin admin = new Admin(adminId, username, password, fullName, email, phone);
                adminList.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }
    
    public static List<Manager> getAllManager() {
        List<Manager> managerList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM FleetManagers";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int managerId = resultSet.getInt("manager_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                Manager manager = new Manager(managerId, username, password, fullName, email, phone);
                managerList.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerList;
    }
    
    public static List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM Staffs";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int staffId = resultSet.getInt("staff_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                Staff staff = new Staff(staffId, username, password, fullName, email, phone);
                staffList.add(staff);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
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
    String query = "select * from Cars\n"
            + "where category_id = ?";
    try (Connection con=getConnect()) {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cid);
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            list.add(new Car(rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9)));
        }
    } catch (Exception ex) {
        Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }
    
    public Car getCarByID(String cid) {
    String query = "select * from Cars\n"
            + "where car_id = ?";
    try (Connection con=getConnect()) {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cid);
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            return new Car(rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getString(9));
        }
    } catch (Exception ex) {
        Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
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
    
     public int login(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB)) {
            if (isCustomerAccount(connection, username, password)) {
                return 1;
            }
            if (isManagerAccount(connection, username, password)) {
                return 2; 
            }
            if (isAdminAccount(connection, username, password)) {
                return 3; 
            }
            if (isStaffAccount(connection, username, password)) {
                return 4; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private boolean isCustomerAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM Customers WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if a customer account is found
            }
        }
    }

    private boolean isAdminAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM SystemAdministrators WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if an admin account is found
            }
        }
    }

    private boolean isManagerAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM FleetManagers WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if a manager account is found
            }
        }
    }
    
    private boolean isStaffAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM Staffs WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Return true if a customer account is found
            }
        }
    }
    
    public static void main(String[] args) {
        
    }
}
