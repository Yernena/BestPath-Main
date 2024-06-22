package main.test;

import main.java.com.bestpath.constant.ApplicationConstants;
import main.java.com.bestpath.models.Location;
import main.java.com.bestpath.utils.HaversineLocationUtils;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class HaversineLocationUtilsTest {

    private final HaversineLocationUtils locationUtils = new HaversineLocationUtils();

    //calculating distance for null locations
    @Test
    void calculateDistanceForNullLocations() {
        assertThrows(IllegalArgumentException.class, () -> locationUtils.calculateDistance(null, new Location(0, 0)));
        assertThrows(IllegalArgumentException.class, () -> locationUtils.calculateDistance(new Location(0, 0), null));
        assertThrows(IllegalArgumentException.class, () -> locationUtils.calculateDistance(null, null));
    }


    @Test
    void calculateDistanceForSameLocation() {
        Location loc = new Location(18.2966, 83.8961); // Srikakulam
        assertEquals(0.0, locationUtils.calculateDistance(loc, loc));
    }

    @Test
    void calculateDistanceForHappyCase() {
        Location srikakulam = new Location(18.2966, 83.8961); // Srikakulam
        Location nearbyLocation = new Location(18.2950, 83.8935); // Within 1km of Srikakulam
        double distance = locationUtils.calculateDistance(srikakulam, nearbyLocation);

        assertTrue(distance <= 1); // Within 1km
    }

    @Test
    void calculateTravelTimeForHappyCase() {
        Location srikakulam = new Location(18.2966, 83.8961); // Srikakulam
        Location nearbyLocation = new Location(18.2950, 83.8935); // Within 1km of Srikakulam
        double travelTime = locationUtils.calculateTravelTime(srikakulam, nearbyLocation);

        double distance = locationUtils.calculateDistance(srikakulam, nearbyLocation);
        double expectedTravelTime = distance / ApplicationConstants.AVERAGE_SPEED;
        assertEquals(expectedTravelTime, travelTime, 0.1);
    }
}
