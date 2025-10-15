import java.util.Arrays;

class Solution {
    /**
     * Finds the minimum ship capacity to ship all packages within a given number of days.
     *
     * This solution uses a binary search on the answer space. The search space is between
     * the weight of the heaviest package and the sum of all package weights.
     *
     * Time Complexity: O(N * log(S)), where N is the number of packages and S is the sum of weights.
     * The binary search takes log(S) iterations, and each iteration requires an O(N) scan
     * of the weights to check feasibility.
     * Space Complexity: O(1) - We only use a few extra variables.
     *
     * @param weights An array of package weights.
     * @param days The maximum number of days allowed.
     * @return The minimum possible ship capacity.
     */
    public int shipWithinDays(int[] weights, int days) {
        // 'low' is the minimum possible capacity (the heaviest single package).
        // 'high' is the maximum possible capacity (sum of all packages).
        int low = Arrays.stream(weights).max().getAsInt();
        int high = Arrays.stream(weights).sum();

        // Perform binary search between low and high to find the minimum feasible capacity.
        while (low < high) {
            // Calculate the middle capacity to test.
            int mid = low + (high - low) / 2;

            // Calculate how many days are needed if the ship's capacity is 'mid'.
            int daysNeeded = 0;
            int currentCapacity = 0;
            for (int weight : weights) {
                // If adding the current package exceeds the ship's capacity,
                // we must ship it on the next day.
                if (weight > currentCapacity) {
                    currentCapacity = mid;
                    daysNeeded++;
                }
                currentCapacity -= weight;
            }

            // If we can ship all packages within the required days, 'mid' is a
            // potential answer. We try to find an even smaller capacity.
            if (daysNeeded <= days) {
                high = mid;
            } else {
                // Otherwise, the capacity 'mid' is too small. We need a larger capacity.
                low = mid + 1;
            }
        }

        // When the loop terminates, low and high converge to the minimum capacity.
        return low;
    }
}