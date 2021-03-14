package com.cabinvoiceggenerator;

public class Ride {
    double distance;
    int time;
    int userId;

    public int getUserId() {
        return userId;
    }

    public Ride(double distance, int time, int userId) {
        this.distance = distance;
        this.time = time;
        this.userId=userId;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "distance=" + distance +
                ", time=" + time +
                ", userId=" + userId +
                '}';
    }
}
