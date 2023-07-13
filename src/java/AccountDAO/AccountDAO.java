package AccountDAO;

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
import entity.Personnel;
import java.sql.Statement;
import entity.*;
import java.sql.CallableStatement;
import java.sql.Types;

public class AccountDAO {

    public Connection getConnect() {
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

    public List<Customer> getAllCustomer() {
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
                String driverLicensePicture = resultSet.getString("driver_license_picture");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int status = resultSet.getInt("status");
                
                Customer customer = new Customer(customerId, fullName, email, phone, driverLicenseNumber, driverLicensePicture, username, password, status);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public void UpdateCustomer(String id, String fullname, String email, String phone, String license, String username, String pass) throws SQLException {
        Connection connection = null;
        String query = "UPDATE Customers\n"
                + "SET full_name = ?, email=?, phone=?, driver_license_number=?, username=?, password=?\n"
                + "WHERE customer_id = ?";
        try {
            connection = getConnect();
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, fullname);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, license);
            st.setString(5, username);
            st.setString(6, pass);
            st.setInt(7, Integer.parseInt(id));
            st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public List<Personnel> getAllStaff() {
        List<Personnel> list = new ArrayList<>();
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
                Personnel s = new Personnel(staff_id, role_id, username, password, fullName, email, phone);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Personnel> getAllWorker() {
        List<Personnel> list = new ArrayList<>();
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
                Personnel s = new Personnel(staff_id, 3, username, password, fullName, email, phone);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "INSERT INTO Customers"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ? , "+1+")";
            statement = connection.prepareStatement(query);
            statement.setInt(1, customer.getCustomerId());
            statement.setString(2, customer.getFullName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getDriverLicenseNumber());
            statement.setString(6, customer.getUsername());
            statement.setString(7, customer.getPassword());
            statement.setString(8, customer.getDriverLicensePicture());
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
                for (Personnel s : getAllStaff()) {
                    if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                        return s.getRole_id();
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9)
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
    
    public void deleteStaffById(String id) {
        String sql = "DELETE FROM staffs\n" + "WHERE staff_id= "+id;
        try (Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Personnel getStaff(String id) {
        String query = "select * from staffs where staff_id = " + id;
        try (Connection con = getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Personnel(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public int updateStaff(int id, String fullName, String email, String phone) {
        int check = 0;
        String sql = "update staffs set "
                + "full_name = ?,"
                + "email = ?,"
                + "phone = ? "
                + "where staff_id = ?";
        try (Connection con = getConnect()) {
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, id);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }
}
