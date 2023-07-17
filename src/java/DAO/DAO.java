package DAO;

import static context.DBContext.DBURL;
import static context.DBContext.DRIVERNAME;
import static context.DBContext.PASSDB;
import static context.DBContext.USERDB;
import entity.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

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
//=======================================================================================================================================

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Cars")) {
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_id");
                int carinfoId = resultSet.getInt("carinfo_id");
                String detail = resultSet.getString("detail");
                String registrationNumber = resultSet.getString("registration_number");
                int locationId = resultSet.getInt("location_id");
                String image = resultSet.getString("image");
                int pricePerDay = resultSet.getInt("price_per_day");
                String status = resultSet.getString("status");
                Car car = new Car(carId, name, categoryId, carinfoId, detail, registrationNumber, locationId,
                        image, pricePerDay, status);
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return cars;
    }

    public Car getCarById(int id) {
        String query = "SELECT * FROM Cars WHERE car_id = ?";
        Car car = null;
        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String name = resultSet.getString("name");
                int categoryId = resultSet.getInt("category_id");
                int carinfoId = resultSet.getInt("carinfo_id");
                String detail = resultSet.getString("detail");
                String registrationNumber = resultSet.getString("registration_number");
                int locationId = resultSet.getInt("location_id");
                String image = resultSet.getString("image");
                int pricePerDay = resultSet.getInt("price_per_day");
                String status = resultSet.getString("status");

                car = new Car(carId, name, categoryId, carinfoId, detail, registrationNumber, locationId,
                        image, pricePerDay, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public void editCar(String image, int cost, String status, String detail, int carId) {
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
            ps.setInt(5, carId);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public int generateCarId() {
        String query = "SELECT IDENT_CURRENT('Cars')";
        int latestId = 0;
        try (Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                latestId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latestId + 1;
    }

    public void addCar(Car car) {
        String query = "INSERT INTO Cars(name, category_id, carinfo_id, detail, registration_number, location_id, image, price_per_day, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, car.getName());
            statement.setInt(2, car.getCategoryId());
            statement.setInt(3, car.getCarinfoId());
            statement.setString(4, car.getDetail());
            statement.setString(5, car.getRegistrationNumber());
            statement.setInt(6, car.getLocationId());
            statement.setString(7, car.getImage());
            statement.setInt(8, car.getPricePerDay());
            statement.setString(9, car.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCarById(int id) {
        String sql = "DELETE FROM [dbo].[Cars]\n" + "WHERE car_id=?";
        try ( Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteImage(String folderPath, String fileName) {
        File imageFile = new File(folderPath, fileName);
        if (imageFile.exists()) {
            boolean isDeleted = imageFile.delete();
            if (isDeleted) {
                System.out.println("Image deleted successfully.");
            } else {
                System.out.println("Failed to delete the image.");
            }
        } else {
            System.out.println("Image does not exist.");
        }
    }

    public void deleteRentalById(int id) {
        String sql = "DELETE FROM [dbo].[Rentals]\n" + "WHERE rental_id=?";
        try ( Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Car> getAvailableCars(int locationId, String startDate, String endDate) {
        List<Car> availableCars = new ArrayList<>();
        String query = "SELECT DISTINCT c.* "
                + "FROM Cars c "
                + "LEFT JOIN Rentals r ON c.car_id = r.car_id "
                + "    AND r.location_id = ? "
                + "    AND ( "
                + "        (r.rental_start_date <= ? AND r.rental_end_date >= ?) "
                + "        OR (r.rental_start_date <= ? AND r.rental_end_date >= ?) "
                + "        OR (r.rental_start_date >= ? AND r.rental_end_date <= ?) "
                + "    ) "
                + "WHERE c.location_id = ? "
                + "    AND c.status = 'Available' "
                + "    AND r.car_id IS NULL";

        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, locationId);
            statement.setString(2, startDate);
            statement.setString(3, startDate);
            statement.setString(4, endDate);
            statement.setString(5, endDate);
            statement.setString(6, startDate);
            statement.setString(7, endDate);
            statement.setInt(8, locationId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setName(resultSet.getString("name"));
                car.setCategoryId(resultSet.getInt("category_id"));
                car.setCarinfoId(resultSet.getInt("carinfo_id"));
                car.setDetail(resultSet.getString("detail"));
                car.setRegistrationNumber(resultSet.getString("registration_number"));
                car.setLocationId(resultSet.getInt("location_id"));
                car.setImage(resultSet.getString("image"));
                car.setPricePerDay(resultSet.getInt("price_per_day"));
                car.setStatus(resultSet.getString("status"));

                availableCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableCars;
    }
    
    public List<Car> findCommonCar(List<Car> listCar1, List<Car> listCar2) {
        Set<Integer> set = new HashSet<>();

        for (Car car : listCar1) {
            set.add(car.getCarId());
        }

        List<Car> listCommonCar = new ArrayList<>();

        for (Car car : listCar2) {
            if (set.contains(car.getCarId())) {
                listCommonCar.add(car);
            }
        }

        return listCommonCar;
    }
    
//=======================================================================================================================================
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Roles")) {

            while (resultSet.next()) {
                int roleId = resultSet.getInt("role_id");
                String title = resultSet.getString("title");

                Role role = new Role(roleId, title);
                roles.add(role);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return roles;
    }
//=======================================================================================================================================

    public List<Personnel> getAllPersonnels() {
        List<Personnel> staffs = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Personnel")) {
            while (resultSet.next()) {
                String personnelId = resultSet.getString("personnel_id");
                int roleId = resultSet.getInt("role_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Personnel staff = new Personnel(personnelId, roleId, fullName, email, phone, username, password);
                staffs.add(staff);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return staffs;
    }

    public List<Personnel> getAllStaffs() {
        List<Personnel> staffs = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Personnel where role_id = 3")) {
            while (resultSet.next()) {
                String personnelId = resultSet.getString("personnel_id");
                int roleId = resultSet.getInt("role_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Personnel staff = new Personnel(personnelId, roleId, fullName, email, phone, username, password);
                staffs.add(staff);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return staffs;
    }

    public Personnel getPersonnelById(String personnelId) {
        String query = "SELECT * FROM Personnel WHERE personnel_id = ?";
        Personnel staff = null;
        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, personnelId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int roleId = resultSet.getInt("role_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                staff = new Personnel(personnelId, roleId, fullName, email, phone, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public int updatePersonnel(String id, String fullName, String email, String phone) {
        int check = 0;
        String sql = "update Personnel set "
                + "full_name = ?,"
                + "email = ?,"
                + "phone = ? "
                + "where personnel_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement ps = null;
            ps = con.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, id);
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void deletePersonnelById(String id) {
        String sql = "DELETE FROM Personnel WHERE personnel_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//=======================================================================================================================================
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Locations")) {

            while (resultSet.next()) {
                int locationId = resultSet.getInt("location_id");
                String address = resultSet.getString("address");

                Location location = new Location(locationId, address);
                locations.add(location);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return locations;
    }

//=======================================================================================================================================
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Categories")) {

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                String title = resultSet.getString("title");

                Category category = new Category(categoryId, title);
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return categories;
    }

    public List<Car> getAllCarByCategoryId(int categoryId) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM Cars WHERE category_id = ?";
        Connection con = getConnect();
        try ( PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Car car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setName(resultSet.getString("name"));
                car.setCategoryId(resultSet.getInt("category_id"));
                car.setCarinfoId(resultSet.getInt("carinfo_id"));
                car.setDetail(resultSet.getString("detail"));
                car.setRegistrationNumber(resultSet.getString("registration_number"));
                car.setLocationId(resultSet.getInt("location_id"));
                car.setImage(resultSet.getString("image"));
                car.setPricePerDay(resultSet.getInt("price_per_day"));
                car.setStatus(resultSet.getString("status"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

//=======================================================================================================================================
    public List<Carinfo> getAllCarinfo() {
        List<Carinfo> carinfos = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Carinfo")) {

            while (resultSet.next()) {
                int carinfoId = resultSet.getInt("carinfo_id");
                String transmission = resultSet.getString("transmission");
                String fuel = resultSet.getString("fuel");
                String consumption = resultSet.getString("consumption");

                Carinfo moreInfo = new Carinfo(carinfoId, transmission, fuel, consumption);
                carinfos.add(moreInfo);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return carinfos;
    }
//=======================================================================================================================================

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Rentals")) {

            while (resultSet.next()) {
                int rentalId = resultSet.getInt("rental_id");
                String personnelId = resultSet.getString("personnel_id");
                String customerId = resultSet.getString("customer_id");
                int carId = resultSet.getInt("car_id");
                String rentalStartDate = resultSet.getString("rental_start_date");
                String rentalEndDate = resultSet.getString("rental_end_date");
                int locationId = resultSet.getInt("location_id");
                int totalCost = resultSet.getInt("total_cost");
                String rentalStatus = resultSet.getString("rental_status");

                Rental rental = new Rental(rentalId, personnelId, customerId, carId, rentalStartDate, rentalEndDate,
                        locationId, totalCost, rentalStatus);
                rentals.add(rental);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return rentals;
    }

    public void checkRental(int rentalId, String staffId) {
        Rental rental = getRentalByRentalId(rentalId);
        String query = null;
        if (rental.getRentalStatus().equals("Chưa thanh toán")) {
            query = "UPDATE Rentals SET rental_status = N'Đã thanh toán', personnel_id = ? WHERE rental_id = ?";
        } else if(rental.getRentalStatus().equals("Chưa xác nhận")){
            query = "UPDATE Rentals SET rental_status = N'Chưa thanh toán', personnel_id = ? WHERE rental_id = ?";
        }
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, staffId);
            stmt.setInt(2, rentalId);
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void denyRental(int rentalId, String staffId) {
        String query = "UPDATE Rentals SET rental_status = N'Từ chối', personnel_id = ? WHERE rental_id = ?";;
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, staffId);
            stmt.setInt(2, rentalId);
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void payRental(int rentalId){
        String query = "UPDATE Rentals SET rental_status = N'Đã thanh toán' WHERE rental_id = ?";
        try ( Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, rentalId);
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Rental getRentalByRentalId(int rentalId) {
        Rental rental = null;
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Rentals where rental_id = '" + rentalId + "'")) {
            while (resultSet.next()) {
                String personnelId = resultSet.getString("personnel_id");
                String customerId = resultSet.getString("customer_id");
                int carId = resultSet.getInt("car_id");
                String rentalStartDate = resultSet.getString("rental_start_date");
                String rentalEndDate = resultSet.getString("rental_end_date");
                int locationId = resultSet.getInt("location_id");
                int totalCost = resultSet.getInt("total_cost");
                String rentalStatus = resultSet.getString("rental_status");
                rental = new Rental(rentalId, personnelId, customerId, carId, rentalStartDate, rentalEndDate,
                        locationId, totalCost, rentalStatus);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return rental;
    }

    public boolean addRental(Rental rental) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "INSERT INTO Rentals (personnel_id, customer_id, car_id, rental_start_date, rental_end_date, location_id, total_cost, rental_status)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, rental.getStaffId());
            statement.setString(2, rental.getCustomerId());
            statement.setInt(3, rental.getCarId());
            statement.setString(4, rental.getRentalStartDate());
            statement.setString(5, rental.getRentalEndDate());
            statement.setInt(6, rental.getLocationId());
            statement.setInt(7, rental.getTotalCost());
            statement.setString(8, rental.getRentalStatus());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error in save order: " + e.getMessage());
            return false;
        }
    }

    public List<Rental> getRentalByCustomerID(String cusId) {
        List<Rental> rentals = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Rentals where customer_id = '" + cusId + "'")) {
            while (resultSet.next()) {
                int rentalId = resultSet.getInt("rental_id");
                String personnelId = resultSet.getString("personnel_id");
                String customerId = resultSet.getString("customer_id");
                int carId = resultSet.getInt("car_id");
                String rentalStartDate = resultSet.getString("rental_start_date");
                String rentalEndDate = resultSet.getString("rental_end_date");
                int locationId = resultSet.getInt("location_id");
                int totalCost = resultSet.getInt("total_cost");
                String rentalStatus = resultSet.getString("rental_status");

                Rental rental = new Rental(rentalId, personnelId, customerId, carId, rentalStartDate, rentalEndDate,
                        locationId, totalCost, rentalStatus);
                rentals.add(rental);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return rentals;
    }

//=======================================================================================================================================
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try ( Connection connection = getConnect();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers")) {

            while (resultSet.next()) {
                String customerId = resultSet.getString("customer_id");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String driverLicenseNumber = resultSet.getString("driver_license_number");
                String driverLicensePicture = resultSet.getString("driver_license_picture");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int status = resultSet.getInt("status");

                Customer customer = new Customer(customerId, fullName, email, phone, driverLicenseNumber,
                        driverLicensePicture, username, password, status);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return customers;
    }

    public boolean checkDuplicateUsername(String username, List<Customer> userList) {
        for (Customer user : userList) {
            if (user.getUsername().equals(username)) {
                return true; 
            }
        }
        return false;
    }
    
    public boolean checkDuplicateEmail(String email, List<Customer> userList) {
        for (Customer user : userList) {
            if (user.getEmail().equals(email)) {
                return true; 
            }
        }
        return false;
    }
    
    public boolean checkDuplicatePhone(String phone, List<Customer> userList) {
        for (Customer user : userList) {
            if (user.getPhone().equals(phone)) {
                return true; 
            }
        }
        return false;
    }
    
    public void addCustomer(Customer customer) {
        try ( Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB)) {
            String query = "INSERT INTO Customers (customer_id, full_name, email, phone, driver_license_number, driver_license_picture, username, password, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getFullName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getDriverLicenseNumber());
            statement.setString(6, customer.getDriverLicensePicture());
            statement.setString(7, customer.getUsername());
            statement.setString(8, customer.getPassword());
            statement.setInt(9, customer.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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
                        rs.getString(1),
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

    public int login(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            if (isCustomerAccount(connection, username, password)) {
                return 4;
            }
            if (isPersonnelAccount(connection, username, password)) {
                for (Personnel s : getAllPersonnels()) {
                    if (s.getUsername().equals(username) && s.getPassword().equals(password)) {
                        return s.getRoleId();
                    }
                }
            }
        } catch (SQLException ex) {

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

    private boolean isPersonnelAccount(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM Personnel WHERE username = ? AND password = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public Customer getCustomerById(String customerId) {
        Customer customer = null;
        String query = "SELECT * FROM Customers WHERE customer_id = ?";
        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerId);
            try ( ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("customer_id");
                    String fullName = resultSet.getString("full_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String driverLicenseNumber = resultSet.getString("driver_license_number");
                    String driverLicensePicture = resultSet.getString("driver_license_picture");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    int status = resultSet.getInt("status");

                    customer = new Customer(id, fullName, email, phone, driverLicenseNumber,
                            driverLicensePicture, username, password, status);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer: " + e.getMessage());
        }
        return customer;
    }

    public void updateInfo(String customerId, String fullName, String email, String phone, String driverLicenseNumber) {
        String query = "UPDATE Customers SET full_name = ?, email = ?, phone = ?, driver_license_number = ? WHERE customer_id = ?";
        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, driverLicenseNumber);
            statement.setString(5, customerId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer information updated successfully.");
            } else {
                System.out.println("Failed to update customer information.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating customer information: " + e.getMessage());
        }
    }

    public void updateCustomer(String id, String fullname, String email, String phone, String license, String username, String pass) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "UPDATE Customers\n"
                    + "SET full_name = ?, email=?, phone=?, driver_license_number=?, username=?, password=?\n"
                    + "WHERE customer_id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, fullname);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, license);
            statement.setString(5, username);
            statement.setString(6, pass);
            statement.setString(7, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnect();
            String query = "DELETE FROM Customers\n"
                    + " where customer_id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomerStatus(String id) {
        Customer cus = getCustomerById(id);
        String query = null;
        if (cus.getStatus() == 0) {
            query = "UPDATE Customers SET status = 1 WHERE customer_id = ?";
        } else {
            query = "UPDATE Customers SET status = 0 WHERE customer_id = ?";
        }
        try ( Connection connection = getConnect();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generatePersonnelID() {
        int latestOrder = 0;
        for (Personnel staff : getAllStaffs()) {
            String staffID = staff.getPersonnelId();
            int order = Integer.parseInt(staffID.substring(2));
            if (order > latestOrder) {
                latestOrder = order;
            }
        }
        String newPersonnelID = "ST" + String.format("%03d", latestOrder + 1);
        return newPersonnelID;
    }

    public String generateCustomerID() {
        int latestOrder = 0;
        for (Customer cus : getAllCustomers()) {
            String cusID = cus.getCustomerId();
            int order = Integer.parseInt(cusID.substring(3));
            if (order > latestOrder) {
                latestOrder = order;
            }
        }
        String newCusID = "CUS" + String.format("%03d", latestOrder + 1);
        return newCusID;
    }

    public void addStaff(Personnel staff) {
        try ( Connection conn = getConnect()) {
            String sql = "INSERT INTO Personnel (personnel_id, role_id, full_name, email, phone, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, staff.getPersonnelId());
            statement.setInt(2, staff.getRoleId());
            statement.setString(3, staff.getFullName());
            statement.setString(4, staff.getEmail());
            statement.setString(5, staff.getPhone());
            statement.setString(6, staff.getUsername());
            statement.setString(7, staff.getPassword());
            statement.executeUpdate();
            System.out.println("Staff added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
