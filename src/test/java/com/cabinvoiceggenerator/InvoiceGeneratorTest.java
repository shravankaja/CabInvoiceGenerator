package com.cabinvoiceggenerator;

import org.junit.jupiter.api.*;

import java.io.*;

public class InvoiceGeneratorTest {
    InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

    @Test
    void givenDistanceAndTimeShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        Assertions.assertEquals(25, invoiceGenerator.calculateFare(distance, time), 0.0);
    }

    @Test
    void givenDistanceAndTimeShouldReturnMinimumFareTotalFare() {
        double distance = 0.1;
        int time = 2;
        Assertions.assertEquals(5, invoiceGenerator.calculateFare(distance, time), 0.0);
    }

    @Test
    void givenMultipleRidesShouldReturnTotalCost() {
        Ride[] rides = {new Ride(2, 5, 156), new Ride(5, 6, 156)};
        Assertions.assertEquals(81, invoiceGenerator.calculateFareForAllRides(rides), 0.0);
    }

    @Test
    void givenMultipleRidesWeShouldGetAnEnhancedInvoice() throws IOException {
        Ride[] rides = {new Ride(2, 5, 156), new Ride(5, 6, 156)};
        double[] result = invoiceGenerator.enhancedInvoice(rides);
        Assertions.assertEquals(2, result[0]);
        Assertions.assertEquals(40.5, result[1]);
        Assertions.assertEquals(81.0, result[2]);
    }

    @Test
    void givenUserIdShoudldGetListOfRidesAndShouldReturnInvoice() throws IOException {
        Ride[] rides = {new Ride(2, 5, 154), new Ride(5, 6, 154)};
        invoiceGenerator.writeToUserFile(rides);
        int numberOfRides = invoiceGenerator.getListOfRides(156);
        Assertions.assertEquals(34, numberOfRides);
    }
}
