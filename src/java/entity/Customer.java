package entity;

public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    private String driverLicenseNumber;
    private String driverLicensePicture;
    private String username;
    private String password;
    private int status;

    public Customer() {
    }

    public Customer(String customerId, String fullName, String email, String phone, String driverLicenseNumber,
                    String driverLicensePicture, String username, String password, int status) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverLicensePicture = driverLicensePicture;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverLicensePicture() {
        return driverLicensePicture;
    }

    public void setDriverLicensePicture(String driverLicensePicture) {
        this.driverLicensePicture = driverLicensePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}

