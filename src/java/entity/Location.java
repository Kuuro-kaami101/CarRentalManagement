package entity;

public class Location {
    private int locationId;
    private String address;
    public Location(){}

    public Location(int locationId, String address) {
        this.locationId = locationId;
        this.address = address;
    }

    // Getters and setters

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", address=" + address + '}';
    }
    
    


}

