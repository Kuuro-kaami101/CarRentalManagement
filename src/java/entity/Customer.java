package entity;

public class Customer {
    private int customerId;
    private String fullName;
    private String email;
    private String phone;
    private String driverLicenseNumber;
    private String username;
    private String password;

    public Customer(int customerId, String fullName, String email, String phone, String driverLicenseNumber, String username, String password) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.driverLicenseNumber = driverLicenseNumber;
        this.username = username;
        this.password = password;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", fullName=" + fullName + ", email=" + email + ", phone=" + phone + ", driverLicenseNumber=" + driverLicenseNumber + ", username=" + username + ", password=" + password + '}';
    }
}

