package entity;

public class Car {
    private int carId;
    private String name;
    private int categoryId;
    private String detail;
    private String registrationNumber;
    private int locationId;
    private String image;
    private int cost;
    private String status;

    public Car(int carId, String name, int categoryId, String detail, String registrationNumber, int locationId, String image, int cost, String status) {
        this.carId = carId;
        this.name = name;
        this.categoryId = categoryId;
        this.detail = detail;
        this.registrationNumber = registrationNumber;
        this.locationId = locationId;
        this.image = image;
        this.cost = cost;
        this.status = status;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public String toString() {
        return "Car{" + "carId=" + carId + ", name=" + name + ", categoryId=" + categoryId + ", detail=" + detail + ", registrationNumber=" + registrationNumber + ", locationId=" + locationId + ", image=" + image + ", cost=" + cost + ", status=" + status + '}';
    }
}

