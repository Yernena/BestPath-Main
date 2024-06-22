package main.test;

import main.java.com.bestpath.models.Customer;
import main.java.com.bestpath.models.Location;
import main.java.com.bestpath.models.Order;
import main.java.com.bestpath.models.Restaurant;
import main.java.com.bestpath.response.PathFinderResponse;
import main.java.com.bestpath.service.DeliveryService;
import main.java.com.bestpath.utils.HaversineLocationUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeliveryServiceTest {

    private DeliveryService deliveryService;
    private Location startLocation;
    private List<Order> orderList;

    @Before
    public void initialize() {
        deliveryService = new DeliveryService(new HaversineLocationUtils());
        orderList = new ArrayList<>();
        startLocation = new Location(18.2966, 83.8961);

        // Initialize order list with coordinates
        orderList.add(new Order(new Restaurant("Restaurant1", new Location(18.3072, 83.8954), 10),
                new Customer("Customer1", new Location(18.2954, 83.9042))));
        orderList.add(new Order(new Restaurant("Restaurant2", new Location(18.2842, 83.9016), 20),
                new Customer("Customer2", new Location(18.2948, 83.8988))));
    }

    @Test
    public void testCalculateTravelTime() {
        double distance = 20.0; // in km
        double travelTime = deliveryService.calculateTravelTime(distance);
        assertEquals(1.0, travelTime, 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindOptimalPathWithInvalidInput(){
        orderList.get(0).setRestaurant(null);
        deliveryService.findBestPath(orderList, startLocation);
    }

    @Test
    public void testFindOptimalPath() {
        // Expected optimal time and path
        double optimalTime = 0.4; // in hr
        List<Location> optimalPath = getOptimalPath();

        PathFinderResponse response = deliveryService.findBestPath(orderList, startLocation);

        assertNotNull(response);
        assertEquals(optimalTime, response.getBestTime(), 0.01);

        List<Location> actualPath = response.getBestPath();
        assertNotNull(actualPath);
        assertEquals(optimalPath.size(), actualPath.size());

        for (int i = 0; i < optimalPath.size(); i++) {
            assertEquals(optimalPath.get(i).toString(), actualPath.get(i).toString());
        }
    }

    private static List<Location> getOptimalPath() {
        Location location1 = new Location(18.3072, 83.8954);
        Location location2 = new Location(18.2954, 83.9042);
        Location location3 = new Location(18.2842, 83.9016);
        Location location4 = new Location(18.2948, 83.8988);
        List<Location> optimalPath = new ArrayList<>();
        optimalPath.add(location1);
        optimalPath.add(location2);
        optimalPath.add(location3);
        optimalPath.add(location4);
        return optimalPath;
    }
}
