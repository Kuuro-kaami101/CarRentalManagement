package entity;

public class Car {
    private int carId;
    private String name;
    private String category_title;
    private int infoid;
    private String detail;
    private String registrationNumber;
    private String address;
    private String image;
    private int cost;
    private String status;
    
    public Car(){};

    public Car(int carId, String name, String category_title,int infoid, String detail, String registrationNumber, String address, String image, int cost, String status) {
        this.carId = carId;
        this.name = name;
        this.category_title = category_title;
        this.infoid=infoid;
        this.detail = detail;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.image = image;
        this.cost = cost;
        this.status = status;
    }
    public Car(int CarId, String image, int cost, String status,String detail){
        this.carId = carId;
        this.detail = detail;
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

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }

    public int getInfoid() {
        return infoid;
    }

    public void setInfoid(int infoid) {
        this.infoid = infoid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "Car{" + "carId=" + carId + ", name=" + name + ", category_title=" + category_title + ", infoid=" + infoid + ", detail=" + detail + ", registrationNumber=" + registrationNumber + ", address=" + address + ", image=" + image + ", cost=" + cost + ", status=" + status + '}';
    }

   
    
}

