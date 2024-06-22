package main.java.com.bestpath.utils;

import main.java.com.bestpath.constant.ApplicationConstants;
import main.java.com.bestpath.models.Location;
import main.java.com.bestpath.service.LocationService;

import static main.java.com.bestpath.constant.ApplicationConstants.AVERAGE_SPEED;

public class HaversineLocationUtils implements LocationService {

    // Haversine formula to calculate the distance between two locations
    @Override
    public double calculateDistance(Location loc1, Location loc2) {
        if (loc1 == null || loc2 == null) {
            throw new IllegalArgumentException("location must not be empty");
        }
        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lonDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return ApplicationConstants.RADIUS_OF_EARTH_IN_KM * c;
    }

    @Override
    public double calculateTravelTime(Location loc1, Location loc2) {
        double distance = calculateDistance(loc1, loc2);
        return distance / AVERAGE_SPEED;
    }
}
