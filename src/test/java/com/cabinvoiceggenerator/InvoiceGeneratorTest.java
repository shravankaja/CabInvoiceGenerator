package com.cabinvoiceggenerator;

import org.junit.jupiter.api.*;

public class InvoiceGeneratorTest {
    @Test
    void givenDistanceAndTimeShouldReturnTotalFare() {
        InvoiceGenerator invoiceGenerator=new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        Assertions.assertEquals(25,invoiceGenerator.calculateFare(distance,time));
    }
}
