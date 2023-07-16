package entity;

public class Rental {
    private int rentalId;
    private String personelId;
    private String customerId;
    private int carId;
    private String rentalStartDate;
    private String rentalEndDate;
    private int locationId;
    private int totalCost;
    private String rentalStatus;

    public Rental() {
    }

    public Rental(int rentalId, String staffId, String customerId, int carId, String rentalStartDate,
                  String rentalEndDate, int locationId, int totalCost, String rentalStatus) {
        this.rentalId = rentalId;
        this.personelId = staffId;
        this.customerId = customerId;
        this.carId = carId;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.locationId = locationId;
        this.totalCost = totalCost;
        this.rentalStatus = rentalStatus;
    }

    public Rental(String staffId, String customerId, int carId, String rentalStartDate,
                  String rentalEndDate, int locationId, int totalCost, String rentalStatus) {
        this.personelId = staffId;
        this.customerId = customerId;
        this.carId = carId;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.locationId = locationId;
        this.totalCost = totalCost;
        this.rentalStatus = rentalStatus;
    }
    
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public String getStaffId() {
        return personelId;
    }

    public void setStaffId(String staffId) {
        this.personelId = staffId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(String rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public String getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(String rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    @Override
    public String toString() {
        return "Rental{" + "rentalId=" + rentalId + ", personelId=" + personelId + ", customerId=" + customerId + ", carId=" + carId + ", rentalStartDate=" + rentalStartDate + ", rentalEndDate=" + rentalEndDate + ", locationId=" + locationId + ", totalCost=" + totalCost + ", rentalStatus=" + rentalStatus + '}';
    }
}

