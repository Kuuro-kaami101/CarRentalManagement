/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import static context.DBContext.DBURL;
import static context.DBContext.DRIVERNAME;
import static context.DBContext.PASSDB;
import static context.DBContext.USERDB;
import entity.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Car;
import entity.Category;
import entity.Customer;
import entity.Location;
import entity.Manager;
import entity.Staff;
import entity.Rental;
import java.sql.Statement;

/**
 *
 * @author Anh Tuan
 */
public class RentalDAO {
    public static Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    public boolean saveRental(Rental r) {
        
        String query = "insert into  [dbo].[Rentals] values(?,?,?,?,?,?,?)";
        try(Connection conn=getConnect()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, r.getCarid());
            ps.setInt(2, r.getCarid());
            ps.setString(3, r.getCheck_in());
            ps.setString(4, r.getCheck_out());
            ps.setDouble(5, r.getCost());
            ps.setString(6, r.getStatus());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error in save order: " + e.getMessage());
            return false;
        }
    }

    //Xem toàn bộ Order
    public List<Rental> viewAllRental() {
        List<Rental> rentals = new ArrayList<Rental>();
        String query = "select * from  [dbo].[Order]";
        try(Connection conn=getConnect()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7)
                ));
            }
        } catch (Exception e) {
            System.out.println("Error in display all: " + e.getMessage());
            return null;
        }

        return rentals;
    }

    //Xem danh sách của order của customer
    public List<Rental> viewRentalByAccID(int id) {
        List<Rental> rentals = new ArrayList<Rental>();
        String query = "select * from  [dbo].[Rentals] where customer_id = ?";
        try (Connection conn=getConnect()){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7)
                ));
            }
        } catch (Exception e) {
            System.out.println("Error in display by accId: " + e.getMessage());
            return null;
        }

        return rentals;
    }

    //xem thông tin của order dựa vào id
    public Rental viewOrderByRentalID(int id) {
        String query = "select * from  [dbo].[Rentals] where rental_id = ?";
        try(Connection conn=getConnect()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return (new Rental(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7)
                ));
            }
        } catch (Exception e) {
            System.out.println("Error in display by accId: " + e.getMessage());
            return null;
        }
        return null;
    }
    
    
    //Hủy order
    public boolean deleterental(int id) {
        String query = "delete from [dbo].[Order] where orderId = ?";
        try(Connection conn=getConnect()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error in delete order: " + e.getMessage());
            return false;
        }
    }
}
