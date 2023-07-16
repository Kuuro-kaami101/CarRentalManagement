package entity;

public class Carinfo {
    private int carinfoId;
    private String transmission;
    private String fuel;
    private String consumption;

    public Carinfo() {
    }

    public Carinfo(int carinfoId, String transmission, String fuel, String consumption) {
        this.carinfoId = carinfoId;
        this.transmission = transmission;
        this.fuel = fuel;
        this.consumption = consumption;
    }

    public int getCarinfoId() {
        return carinfoId;
    }

    public void setCarinfoId(int carinfoId) {
        this.carinfoId = carinfoId;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "MoreInfo{" + "moreInfoId=" + carinfoId + ", transmission=" + transmission + ", fuel=" + fuel + ", consumption=" + consumption + '}';
    }
}