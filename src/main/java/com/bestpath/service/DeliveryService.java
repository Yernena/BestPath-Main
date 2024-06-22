package main.java.com.bestpath.service;

import main.java.com.bestpath.constant.ApplicationConstants;
import main.java.com.bestpath.helper.PathsBuilder;
import main.java.com.bestpath.models.Location;
import main.java.com.bestpath.models.Order;
import main.java.com.bestpath.models.Restaurant;
import main.java.com.bestpath.response.PathFinderResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryService {

    private final LocationService locationService;

    public DeliveryService(LocationService locationService) {
        this.locationService = locationService;
    }

    //Finding the best path starts
    public PathFinderResponse findBestPath(List<Order> orderList, Location startLocation) {
        validateInputs(orderList, startLocation);
        try {
            List<Location> restaurants = extractRestaurantLocations(orderList);
            List<Location> customers = extractCustomerLocations(orderList);
            Map<String, Restaurant> restaurantMap = buildRestaurantMap(orderList);

            List<List<Location>> validPaths = PathsBuilder.buildValidPaths(restaurants, customers);

            return findShortestPath(validPaths, startLocation, restaurantMap);
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException("restaurants and customers must not be null");
        }
    }

    //Validating the inputs provided
    private void validateInputs(List<Order> orderList, Location startLocation) {
        if (orderList == null || startLocation == null) {
            throw new IllegalArgumentException("Order list and start location must not be null");
        }
    }

    private List<Location> extractRestaurantLocations(List<Order> orderList) {
        List<Location> restaurants = new ArrayList<>();
        orderList.forEach(order -> restaurants.add(order.getRestaurant().getLocation()));
        return restaurants;
    }

    private List<Location> extractCustomerLocations(List<Order> orderList) {
        List<Location> customers = new ArrayList<>();
        orderList.forEach(order -> customers.add(order.getCustomer().getLocation()));
        return customers;
    }

    private Map<String, Restaurant> buildRestaurantMap(List<Order> orderList) {
        Map<String, Restaurant> restaurantMap = new HashMap<>();
        orderList.forEach(order -> restaurantMap.put(order.getRestaurant().getLocation().toString(), order.getRestaurant()));
        return restaurantMap;
    }

    //Finding the shortest paths out of valid paths
    private PathFinderResponse findShortestPath(List<List<Location>> validPaths, Location startLocation, Map<String, Restaurant> restaurantMap) {
        double minTime = Double.MAX_VALUE;
        List<Location> optimalPath = null;

        for (List<Location> path : validPaths) {
            double time = calculatePathTime(startLocation, path, restaurantMap);
            if (minTime > time) {
                minTime = time;
                optimalPath = path;
            }
        }
        return new PathFinderResponse(minTime, optimalPath);
    }

    //Calculating path time
    private double calculatePathTime(Location startLocation, List<Location> path, Map<String, Restaurant> restaurantMap) {
        double time = 0;
        Location currentLocation = startLocation;

        for (Location location : path) {
            time += locationService.calculateTravelTime(currentLocation, location);
            if (restaurantMap.containsKey(location.toString())) {
                time = Math.max(time, restaurantMap.get(location.toString()).getPrepTime() / 60.0);
            }
            currentLocation = location;
        }
        return time; // in hours
    }

    public double calculateTravelTime(double distance) {
        return distance / ApplicationConstants.AVERAGE_SPEED;
    }
}
