/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Anh Tuan
 */
//rental_id INT PRIMARY KEY,
//  customer_id INT,
//  rental_start_date DATE,
//  rental_end_date DATE,
//  car_id INT,
//  total_cost DECIMAL(10, 2),
//  rental_status NVARCHAR(20),
//  FOREIGN KEY (customer_id) REFERENCES Customers(customer_id),
//  FOREIGN KEY (car_id) REFERENCES Cars(car_id),
public class Rental {
    private int rentalid;
    private int customerid;
    private int carid;
    private String check_in;
    private String check_out;
    private double cost;
    private String status;
    public Rental(){}
    public Rental(int rentalid,int customerid,int carid,String check_in,String check_out,double cost,String status){
        this.rentalid=rentalid;
        this.carid=carid;
        this.check_in=check_in;
        this.check_out=check_out;
        this.cost=cost;
        this.status=status;
    }
    public Rental(int customerid,int carid,String check_in,String check_out,double cost,String status){
        this.carid=carid;
        this.check_in=check_in;
        this.check_out=check_out;
        this.cost=cost;
        this.status=status;
    }

    public int getRentalid() {
        return rentalid;
    }

    public void setRentalid(int rentalid) {
        this.rentalid = rentalid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getCarid() {
        return carid;
    }

    public void setCarid(int carid) {
        this.carid = carid;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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
        return "Rental{" + "rentalid=" + rentalid + ", customerid=" + customerid + ", carid=" + carid + ", check_in=" + check_in + ", check_out=" + check_out + ", cost=" + cost + ", status=" + status + '}';
    }
    
    
    
}
