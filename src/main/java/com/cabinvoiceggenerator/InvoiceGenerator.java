package com.cabinvoiceggenerator;

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

    public double[] enhancedInvoice(Ride[] rides) {
        double totalFare = this.calculateFareForAllRides(rides);
        int numberOfRides = rides.length;
        double averageFarePerRide = totalFare / numberOfRides;
        System.out.println("Number of Rides :" + numberOfRides);
        System.out.println("Average fare per ride :" + averageFarePerRide);
        System.out.println("Total Fare :" + totalFare);
        double[] result = {numberOfRides, averageFarePerRide, totalFare};
        return result;
    }
}
