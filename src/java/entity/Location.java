package entity;

public class Location {
    private int locationId;
    private String name;
    private String address;

    public Location(int locationId, String name, String address) {
        this.locationId = locationId;
        this.name = name;
        this.address = address;
    }

    // Getters and setters

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

