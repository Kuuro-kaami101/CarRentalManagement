/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Anh Tuan
 */
public class Moreinfo {
    private int infoid;
    private String transmission;
    private String fuel;
    private String consumption;
    public Moreinfo(){};
    public Moreinfo(int infoid,String transmission,String fuel, String consumption ){
        this.infoid= infoid;
        this.transmission= transmission;
        this.fuel=fuel;
        this.consumption=consumption;
    }
    public Moreinfo(String transmission,String fuel, String consumption ){
        this.transmission= transmission;
        this.fuel=fuel;
        this.consumption=consumption;
    }

    public int getInfoid() {
        return infoid;
    }

    public void setInfoid(int infoid) {
        this.infoid = infoid;
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
        return "Moreinfo{" + "infoid=" + infoid + ", transmission=" + transmission + ", fuel=" + fuel + ", consumption=" + consumption + '}';
    }

    
    
    
}
