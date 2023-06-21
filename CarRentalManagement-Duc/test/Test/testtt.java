/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author DucLe
 */
import java.util.HashMap;
import java.util.Map;

class CarRentalSystem {
    private Map<String, Integer> rentalCounts;
    private Map<String, Double> revenue;
    private Map<String, Double> rentalDurations;

    public CarRentalSystem() {
        rentalCounts = new HashMap<>();
        revenue = new HashMap<>();
        rentalDurations = new HashMap<>();
    }

    public void rentCar(String carId, double amount, double duration) {
        if (rentalCounts.containsKey(carId)) {
            int count = rentalCounts.get(carId);
            rentalCounts.put(carId, count + 1);
        } else {
            rentalCounts.put(carId, 1);
        }

        if (revenue.containsKey(carId)) {
            double totalRevenue = revenue.get(carId);
            revenue.put(carId, totalRevenue + amount);
        } else {
            revenue.put(carId, amount);
        }

        if (rentalDurations.containsKey(carId)) {
            double totalDuration = rentalDurations.get(carId);
            rentalDurations.put(carId, totalDuration + duration);
        } else {
            rentalDurations.put(carId, duration);
        }
    }

    public int getRentalCount(String carId) {
        return rentalCounts.getOrDefault(carId, 0);
    }

    public double getTotalRevenue(String carId) {
        return revenue.getOrDefault(carId, 0.0);
    }

    public double getAverageRentalDuration(String carId) {
        int rentalCount = getRentalCount(carId);
        double totalDuration = rentalDurations.getOrDefault(carId, 0.0);
        return rentalCount > 0 ? totalDuration / rentalCount : 0.0;
    }
}

public class testtt {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        rentalSystem.rentCar("ABC123", 100.0, 3.5);
        rentalSystem.rentCar("DEF456", 200.0, 2.0);
        rentalSystem.rentCar("ABC123", 150.0, 4.0);
        rentalSystem.rentCar("GHI789", 300.0, 1.5);
        rentalSystem.rentCar("DEF456", 250.0, 3.0);

        int rentalCount1 = rentalSystem.getRentalCount("ABC123");
        int rentalCount2 = rentalSystem.getRentalCount("DEF456");
        int rentalCount3 = rentalSystem.getRentalCount("GHI789");
        int rentalCount4 = rentalSystem.getRentalCount("JKL012");

        double totalRevenue1 = rentalSystem.getTotalRevenue("ABC123");
        double totalRevenue2 = rentalSystem.getTotalRevenue("DEF456");
        double totalRevenue3 = rentalSystem.getTotalRevenue("GHI789");
        double totalRevenue4 = rentalSystem.getTotalRevenue("JKL012");

        double averageDuration1 = rentalSystem.getAverageRentalDuration("ABC123");
        double averageDuration2 = rentalSystem.getAverageRentalDuration("DEF456");
        double averageDuration3 = rentalSystem.getAverageRentalDuration("GHI789");
        double averageDuration4 = rentalSystem.getAverageRentalDuration("JKL012");

        System.out.println("Rental count for car ABC123: " + rentalCount1);
        System.out.println("Rental count for car DEF456: " + rentalCount2);
        System.out.println("Rental count for car GHI789: " + rentalCount3);
        System.out.println("Rental count for car JKL012: " + rentalCount4);

        System.out.println("Total revenue for car ABC123: $" + totalRevenue1);
        System.out.println("Total revenue for car DEF456: $" + totalRevenue2);
        System.out.println("Total revenue for car GHI789: $" + totalRevenue3);
        System.out.println("Total revenue for car JKL012: $" + totalRevenue4);

        System.out.println("Average rental duration for car ABC123: " + averageDuration1 + " days");
        System.out.println("Average rental duration for car DEF456: " + averageDuration2 + " days");
        System.out.println("Average rental duration for car GHI789: " + averageDuration3 + " days");
        System.out.println("Average rental duration for car JKL012: " + averageDuration4 + " days");
    }
}

