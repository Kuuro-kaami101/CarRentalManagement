package entity;

public class Rental {
    private int rentalId;
    private int customerId;
    private String startDate;
    private String endDate;
    private int locationId;
    private int totalCost;
    private String rentalStatus;
    
    public Rental() {
    }

    public Rental(int rentalId, int customerId, String startDate, String endDate, int locationId, int totalCost, String rentalStatus) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
        return "Rental{" + "rentalId=" + rentalId + ", customerId=" + customerId + ", startDate=" + startDate + ", endDate=" + endDate + ", locationId=" + locationId + ", totalCost=" + totalCost + ", rentalStatus=" + rentalStatus + '}';
    }
    
}
