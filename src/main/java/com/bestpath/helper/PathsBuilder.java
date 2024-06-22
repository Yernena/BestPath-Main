package main.java.com.bestpath.helper;

import main.java.com.bestpath.models.Location;
import main.java.com.bestpath.validator.PathValidator;

import java.util.*;

public class PathsBuilder {

    //Generating all the valid paths
    public static List<List<Location>> buildValidPaths(List<Location> restaurantList, List<Location> customerList) {
        validateInputLists(restaurantList, customerList);

        List<Location> combinedLocations = combineLocations(restaurantList, customerList);
        List<List<Location>> allPermutations = generateAllPermutations(combinedLocations);

        return filterValidPaths(allPermutations, restaurantList, customerList);
    }

    //Validating the inputs provided
    private static void validateInputLists(List<Location> restaurantList, List<Location> customerList) {
        if (restaurantList == null || restaurantList.isEmpty() || customerList == null || customerList.isEmpty()) {
            throw new IllegalArgumentException("Input lists cannot be null or empty");
        }
        if (restaurantList.size() != customerList.size()) {
            throw new IllegalArgumentException("The number of restaurants and customers must be equal");
        }
    }

    //adding the all the locationd into one list
    private static List<Location> combineLocations(List<Location> restaurantList, List<Location> customerList) {
        List<Location> combinedLocations = new ArrayList<>(restaurantList);
        combinedLocations.addAll(customerList);
        return combinedLocations;
    }

    private static List<List<Location>> generateAllPermutations(List<Location> locations) {
        List<List<Location>> permutations = new ArrayList<>();
        generatePermutations(locations, 0, permutations);
        return permutations;
    }

    private static void generatePermutations(List<Location> locations, int start, List<List<Location>> permutations) {
        if (start == locations.size() - 1) {
            permutations.add(new ArrayList<>(locations));
            return;
        }
        for (int i = start; i < locations.size(); i++) {
            Collections.swap(locations, i, start);
            generatePermutations(locations, start + 1, permutations);
            Collections.swap(locations, i, start); // backtrack
        }
    }

    //removing the invalid paths
    private static List<List<Location>> filterValidPaths(List<List<Location>> allPermutations,
            List<Location> restaurantList,
            List<Location> customerList) {
        Set<List<Location>> validPaths = new HashSet<>();
        for (List<Location> permutation : allPermutations) {
            if (PathValidator.isValidPath(permutation, restaurantList, customerList)) {
                validPaths.add(permutation);
            }
        }
        return new ArrayList<>(validPaths);
    }
}
