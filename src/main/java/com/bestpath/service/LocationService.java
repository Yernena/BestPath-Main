package main.java.com.bestpath.service;

import main.java.com.bestpath.models.Location;

public interface LocationService {

    public double calculateDistance(Location loc1, Location loc2);

    public double calculateTravelTime(Location loc1, Location loc2);
}
