package entity;

public class RentalShow {
    private int rentalId;
    private String customerName;
    private String startDate;
    private String endDate;
    private String address;
    private String carName;
    private String carPicture;
    private int cost;
    private String status;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPicture() {
        return carPicture;
    }

    public void setCarPicture(String carPicture) {
        this.carPicture = carPicture;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RentalShow(int rentalId, String customerName, String startDate, String endDate, String address, String carName, String carPicture, int cost, String status) {
        this.rentalId = rentalId;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.address = address;
        this.carName = carName;
        this.carPicture = carPicture;
        this.cost = cost;
        this.status = status;
    }

    
}
