import main.java.com.bestpath.models.Customer;
import main.java.com.bestpath.models.Location;
import main.java.com.bestpath.models.Order;
import main.java.com.bestpath.models.Restaurant;
import main.java.com.bestpath.response.PathFinderResponse;
import main.java.com.bestpath.service.DeliveryService;
import main.java.com.bestpath.service.LocationService;
import main.java.com.bestpath.utils.HaversineLocationUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BestPathFinderMain {

    public static void main(String[] args) throws IOException {

        LocationService locationService = new HaversineLocationUtils();
        DeliveryService deliveryService = new DeliveryService(locationService);
        List<Order> orders = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of orders to be delivered: ");
        int numOrders = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOrders; i++) {
            orders.add(createOrder(br, i));
        }

        System.out.println("Please enter delivery partner details as Latitude, Longitude, with space between each field:");
        String[] deliveryPartnerLocation = br.readLine().split(" ");
        PathFinderResponse pathFinderResponse = deliveryService.findBestPath(orders,
                new Location(Double.parseDouble(deliveryPartnerLocation[0]),
                        Double.parseDouble(deliveryPartnerLocation[1])));

        pathFinderResponse.getBestPath().forEach(location ->
                System.out.printf("(%s) --> ", location.toString()));
        System.out.print("Orders are Delivered!!!\n");
    }

    //Taking inputs of the location details of customers and restaurants
    private static Order createOrder(BufferedReader br, int orderNumber) throws IOException {
        System.out.println("Please enter customer details as Name, Latitude, Longitude, with space between each field:");
        String[] customerDetails = br.readLine().split(" ");
        Customer customer = new Customer(customerDetails[0],
                new Location(Double.parseDouble(customerDetails[1]), Double.parseDouble(customerDetails[2])));

        System.out.println("Please enter restaurant details as Name, Latitude ,Longitude, PrepTime, with space between each field:");
        String[] restaurantDetails = br.readLine().split(" ");
        Restaurant restaurant = new Restaurant(restaurantDetails[0],
                new Location(Double.parseDouble(restaurantDetails[1]), Double.parseDouble(restaurantDetails[2])),
                Double.parseDouble(restaurantDetails[3]));
        return new Order(orderNumber, customer, restaurant);
    }

}