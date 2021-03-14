package com.cabinvoiceggenerator;

enum RideType {
    REGULAR, PREMIUM;
}

public class Ride {
    RideType type;
    double distance;
    int time;
    int userId;

    public int getUserId() {
        return userId;
    }

    public Ride(double distance, int time, int userId, RideType type) {
        this.distance = distance;
        this.time = time;
        this.userId = userId;
        this.type = type;
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
