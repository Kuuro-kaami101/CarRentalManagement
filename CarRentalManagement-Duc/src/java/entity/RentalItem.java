package entity;

public class RentalItem {
    private int rentalItemId;
    private int rentalId;
    private int carId;

    public RentalItem(int rentalItemId, int rentalId, int carId) {
        this.rentalItemId = rentalItemId;
        this.rentalId = rentalId;
        this.carId = carId;
    }

    public int getRentalItemId() {
        return rentalItemId;
    }

    public void setRentalItemId(int rentalItemId) {
        this.rentalItemId = rentalItemId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "RentalItem{" + "rentalItemId=" + rentalItemId + ", rentalId=" + rentalId + ", carId=" + carId + '}';
    }
}
