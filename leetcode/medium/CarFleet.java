import java.util.Arrays;

/**
 * Solution class for the Car Fleet problem.
 */
class Solution {
    /**
     * Calculates the number of car fleets that will arrive at the target.
     * A car fleet is a non-empty set of cars driving at the same position and same speed.
     * Note that a single car is also a car fleet.
     *
     * The core idea is to sort the cars by their starting position in descending order
     * (from closest to the target to furthest). Then, we iterate through the cars
     * and determine if they form a new fleet or join an existing one.
     *
     * @param target   The distance to the target destination.
     * @param position An array where position[i] is the starting position of the i-th car.
     * @param speed    An array where speed[i] is the speed of the i-th car.
     * @return The total number of car fleets that will be formed.
     */
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) {
            return 0;
        }

        // Create a 2D array to pair each car's position with its speed.
        // cars[i][0] = position, cars[i][1] = speed
        int[][] cars = new int[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        // Sort the cars based on their starting position in descending order.
        // This allows us to process cars from the one closest to the target to the one furthest away.
        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

        int fleetCount = 0;
        double timeForLeadCar = 0.0; // Tracks the arrival time of the lead car of the current fleet.

        // Iterate through the sorted cars.
        for (int i = 0; i < n; i++) {
            // Calculate the time it will take for the current car to reach the target.
            double timeToTarget = (double) (target - cars[i][0]) / cars[i][1];

            // If the current car takes longer to reach the target than the lead car of the fleet ahead of it,
            // it means this car cannot catch up. Thus, it forms a new fleet.
            if (timeToTarget > timeForLeadCar) {
                // This car becomes the new lead car of a new fleet.
                timeForLeadCar = timeToTarget;
                fleetCount++;
            }
            // If the current car's time is less than or equal to timeForLeadCar,
            // it will catch up and merge with the fleet ahead, so we don't increment the fleet count.
        }

        return fleetCount;
    }
}