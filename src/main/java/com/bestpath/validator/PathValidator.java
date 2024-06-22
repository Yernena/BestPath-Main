package main.java.com.bestpath.validator;

import main.java.com.bestpath.models.Location;

import java.util.List;

public class PathValidator {
    public static boolean isValidPath(List<Location> path, List<Location> restaurantList,
            List<Location> customerList) {
        for (int i = 0; i < restaurantList.size(); i++) {
            if (path.indexOf(customerList.get(i)) < path.indexOf(restaurantList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
