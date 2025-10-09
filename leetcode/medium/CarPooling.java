/**
 * Solution for the Car Pooling problem.
 * This class determines if a car with a given capacity can accommodate all passengers from a list of trips.
 */
class Solution {
    /**
     * Determines if it's possible to perform all trips without exceeding the car's capacity.
     *
     * This optimized solution uses a timeline or difference array approach.
     *
     * 1. We create a timeline array representing all possible locations (0 to 1000, so size 1001).
     *    This array will store the net change in the number of passengers at each location.
     *
     * 2. For each trip, we update the timeline:
     *    - At the start location ('from'), we add the number of passengers.
     *    - At the end location ('to'), we subtract the number of passengers.
     *    This marks the points where the passenger count changes.
     *
     * 3. We then iterate through the timeline from the first to the last location, calculating the
     *    cumulative number of passengers in the car at each point.
     *
     * 4. If the number of passengers at any location exceeds the car's capacity, we immediately
     *    return false.
     *
     * 5. If we successfully process all locations without exceeding capacity, we return true.
     *
     * This method is more efficient than using priority queues, with a time complexity of O(N + M),
     * where N is the number of trips and M is the maximum possible location. The space complexity
     * is O(M) for the timeline array.
     *
     * @param trips An array of trips, where each trip is `[numPassengers, from, to]`.
     * @param capacity The maximum number of passengers the car can hold.
     * @return `true` if all trips can be completed without exceeding capacity, `false` otherwise.
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // Create a timeline array to store passenger changes at each location.
        // The problem constraints state that locations are between 0 and 1000.
        int[] timeline = new int[1001];

        // Populate the timeline with passenger changes from each trip.
        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            timeline[from] += numPassengers; // Passengers get on at the start location.
            timeline[to] -= numPassengers;   // Passengers get off at the end location.
        }

        // Iterate through the timeline to calculate the number of passengers at each location.
        int currentPassengers = 0;
        for (int passengerChange : timeline) {
            currentPassengers += passengerChange;
            // Check if the current number of passengers exceeds the car's capacity.
            if (currentPassengers > capacity) {
                return false; // Capacity exceeded.
            }
        }

        // If the loop completes, it means capacity was never exceeded.
        return true;
    }
}
