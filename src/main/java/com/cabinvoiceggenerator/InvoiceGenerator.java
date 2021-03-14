package com.cabinvoiceggenerator;

import com.opencsv.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class InvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final int COST_PER_TIME = 1;
    private static final int MINIMUM_FARE = 5;


    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_TIME;
        if (totalFare < MINIMUM_FARE) return MINIMUM_FARE;
        return totalFare;
    }

    public double calculateFareForAllRides(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public double[] enhancedInvoice(Ride[] rides) throws IOException {
        double totalFare = this.calculateFareForAllRides(rides);
        int numberOfRides = rides.length;
        double averageFarePerRide = totalFare / numberOfRides;
        System.out.println("Number of Rides :" + numberOfRides);
        System.out.println("Average fare per ride :" + averageFarePerRide);
        System.out.println("Total Fare :" + totalFare);
        double[] result = {numberOfRides, averageFarePerRide, totalFare};
        return result;
    }

    public void writeToUserFile(Ride[] rides) throws IOException {
        Path directoryPath = Paths.get("E:\\Eclipse_Practise\\CabInvoiceGenerator\\src\\main\\resources");
        if (Files.isDirectory(directoryPath)) {
            for (Ride ride : rides) {
                String userId = String.valueOf(ride.getUserId());
                String pathOfUserFile = directoryPath + "/" + userId + ".txt";
                Path filePath = Paths.get(pathOfUserFile);
                if (!Files.exists(filePath)) {
                    BufferedWriter out = new BufferedWriter(
                            new FileWriter(String.valueOf(filePath)));
                    Files.createFile(filePath);
                    appendStrToFile(String.valueOf(filePath), "Distance,Time,UserId");
                    appendStrToFile(String.valueOf(filePath), "\n");
                    appendStrToFile(String.valueOf(filePath), String.valueOf(ride.distance));
                    appendStrToFile(String.valueOf(filePath), ",");
                    appendStrToFile(String.valueOf(filePath), String.valueOf(ride.time));
                    appendStrToFile(String.valueOf(filePath), ",");
                    appendStrToFile(String.valueOf(filePath), String.valueOf(ride.userId));
                    appendStrToFile(String.valueOf(filePath), "\n");
                } else if (Files.exists(filePath)) {
                    appendStrToFile(String.valueOf(filePath), String.valueOf(ride.distance));
                    appendStrToFile(String.valueOf(filePath), ",");
                    appendStrToFile(String.valueOf(filePath), String.valueOf(ride.time));
                    appendStrToFile(String.valueOf(filePath), ",");
                    appendStrToFile(String.valueOf(filePath), String.valueOf(ride.userId));
                    appendStrToFile(String.valueOf(filePath), "\n");
                }
            }
        }
    }

    public int getListOfRides(int userId) throws IOException {
        ArrayList<Ride> rideArrayList = new ArrayList<>();
        int count = 0;
        Ride[] ridesList = new Ride[100];
        Path directoryPath = Paths.get("E:\\Eclipse_Practise\\CabInvoiceGenerator\\src\\main\\resources");
        if (Files.isDirectory(directoryPath)) {
            String userFile = String.valueOf(userId);
            System.out.println(userFile);
            String pathOfUserFile = directoryPath + "/" + userFile + ".txt";
            System.out.println(pathOfUserFile);
            Path filePath = Paths.get(pathOfUserFile);
            if (Files.exists(filePath)) {
                CSVReader reader = new CSVReader(new FileReader(String.valueOf(filePath)));
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {

                    System.out.print("Distance :" + nextLine[0]);
                    System.out.println("\n");
                    System.out.print("Time :" + nextLine[1]);
                    System.out.println("\n");
                    System.out.print("User Id :" + nextLine[2]);
                    System.out.println("\n");
                    System.out.println("--------------------------------");
                    count = count + 1;
                    rideArrayList.add(new Ride(Double.parseDouble(nextLine[0]), Integer.parseInt(nextLine[1]),
                            Integer.parseInt(nextLine[2])));
                }
            }
        }
        this.enhancedInvoiceList(rideArrayList);
        return count;
    }


    public static void appendStrToFile(String fileName, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    public double[] enhancedInvoiceList(ArrayList<Ride> rides) throws IOException {
        double totalFare = this.calculateFareForAllRidesList(rides);
        int numberOfRides = rides.size();
        double averageFarePerRide = totalFare / numberOfRides;
        System.out.println("Number of Rides :" + numberOfRides);
        System.out.println("Average fare per ride :" + averageFarePerRide);
        System.out.println("Total Fare :" + totalFare);
        double[] result = {numberOfRides, averageFarePerRide, totalFare};
        return result;
    }

    public double calculateFareForAllRidesList(ArrayList<Ride> rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }
}
