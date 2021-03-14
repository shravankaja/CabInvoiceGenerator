package com.cabinvoiceggenerator;

import org.junit.jupiter.api.*;

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
        Ride[] rides = {new Ride(2, 5), new Ride(5, 6)};
        Assertions.assertEquals(81, invoiceGenerator.calculateFareForAllRides(rides), 0.0);
    }

    @Test
    void givenMultipleRidesWeShouldGetAnEnhancedInvoice() {
        Ride[] rides = {new Ride(2, 5), new Ride(5, 6)};
        double[] result = invoiceGenerator.enhancedInvoice(rides);
        Assertions.assertEquals(2, result[0]);
        Assertions.assertEquals(40.5, result[1]);
        Assertions.assertEquals(81.0, result[2]);
    }
}
