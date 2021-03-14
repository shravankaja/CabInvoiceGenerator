package com.cabinvoiceggenerator;

import org.junit.jupiter.api.*;

import java.io.*;

public class InvoiceGeneratorTest {
    InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

    @Test
    void givenDistanceAndTimeShouldReturnTotalFare() {
        double distance = 10;
        int time = 20;
        Assertions.assertEquals(190, invoiceGenerator.calculateFare(distance, time, RideType.PREMIUM), 0.0);
    }

    @Test
    void givenDistanceAndTimeShouldReturnMinimumFareTotalFare() {
        double distance = 0.1;
        int time = 2;
        Assertions.assertEquals(20, invoiceGenerator.calculateFare(distance, time, RideType.PREMIUM), 0.0);
    }

    @Test
    void givenMultipleRidesShouldReturnTotalCost() {
        Ride[] rides = {new Ride(10, 5, 156, RideType.PREMIUM), new Ride(10, 5, 156, RideType.REGULAR)};
        Assertions.assertEquals(265.0, invoiceGenerator.calculateFareForAllRides(rides), 0.0);
    }

    @Test
    void givenMultipleRidesWeShouldGetAnEnhancedInvoice() throws IOException {
        Ride[] rides = {new Ride(2, 5, 156, RideType.PREMIUM), new Ride(5, 6, 156, RideType.REGULAR)};
        double[] result = invoiceGenerator.enhancedInvoice(rides);
        Assertions.assertEquals(2, result[0]);
        Assertions.assertEquals(48.0, result[1]);
        Assertions.assertEquals(96.0, result[2]);
    }

    @Test
    void givenUserIdShoudldGetListOfRidesAndShouldReturnInvoice() throws IOException {
        Ride[] rides = {new Ride(2, 5, 161, RideType.REGULAR), new Ride(5, 6, 161, RideType.PREMIUM)};
        invoiceGenerator.writeToUserFile(rides);
        int numberOfRides = invoiceGenerator.getListOfRides(160);
        Assertions.assertEquals(4, numberOfRides);
    }


}
