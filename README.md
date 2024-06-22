# Finding Best Path

---

## Models

### Location
- Represents a location of a customer or a restaurant
  - Attributes:
    - Latitude
    - Longitude

### Restaurant
- Represents a restaurant.
    - Attributes:
        - Location
        - Name
        - Preparation time (in minutes)

### Customer
- Represents a customer.
    - Attributes:
        - Location
        - Name

### Order
- Represents a delivery order.
    - Attributes:
        - Restaurant details
        - Customer details
        - Order ID

---

## Model Relationships
- A location has a longitude and a latitude
- A restaurant has a location.
- A customer has a location.
- An order has a restaurant and a customer.

---

## Interfaces

### LocationService
- Methods:
    - `calculateTimeBetweenLocations(location1, location2)`: Calculates the time taken between two locations.
    - `calculateDistanceBetweenLocations(location1, location2)`: Calculates the distance between two locations.

---

## Services

### DeliveryService
- Business logic for:
    - Calculating the time to reach between two locations.
    - Finding the best time and path for a given batch of orders.

---

## Utility

### HaversineLocationUtils
- Implementation of the LocationService interface using the Haversine formula.
- Fetching the time taken based on the distance calculated using Haversine formula.

---

## Helper

### PathsBuilder
- Generating all the paths between the locations.
- Filtering only valid paths.
---

## Approach

The method for identifying the best path encompasses the following steps:

1. Generate all feasible permutations for the given set of locations.
2. Validate each permutation to ensure it conforms to predefined constraints (e.g., customers visited only after their respective restaurant).
3. Compute the total time for each valid permutation to determine the most efficient path and time.
---

## Test Cases

### HaversineLocationUtilsTest
- `calculateDistanceForNullLocations`: Tests the calculation of distance for null locations.
- `calculateDistanceForSameLocation`: Tests the calculation of distance for the same location.
- `calculateDistanceForHappyCase`: Tests the calculation of distance between two locations for a happy case.
- `calculateTravelTimeForHappyCase`: Tests the calculation of travel time between two locations for a happy case.

### DeliveryServiceTest
- `calculateTravelTime`: Verifies the calculation of travel time between two locations.
- `findOptimalPathForInvalidInput`: Verifies the handling of invalid input in finding the best path.
- `findOptimalPath`: Verifies the finding of the optimal path for a given set of orders and start location.

---