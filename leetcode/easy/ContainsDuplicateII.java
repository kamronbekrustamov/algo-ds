import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Checks if there are two distinct indices i and j in the array such that
     * nums[i] == nums[j] and abs(i - j) <= k.
     *
     * This solution uses a HashMap to store the most recent index of each number
     * as we iterate through the array. This allows for an efficient, single-pass solution.
     *
     * Algorithm Breakdown:
     * 1. Initialize a HashMap, `lastSeenIndex`, to store key-value pairs of
     *    (number -> index).
     * 2. Iterate through the input array `nums` with an index `i`.
     * 3. For each number `nums[i]`:
     *    a. Check if the number already exists as a key in our HashMap.
     *    b. If it exists, it means we have seen this number before. We get its
     *       previous index from the map and check if the difference between the
     *       current index `i` and the previous index is less than or equal to `k`.
     *    c. If the condition is met, we have found a valid nearby duplicate, and
     *       we return `true`.
     *    d. If the number doesn't exist in the map, or if the distance was too large,
     *       we update the map with the current number and its current index `i`.
     *       This ensures we always have the most recent index for any number.
     * 4. If the loop completes without finding any nearby duplicates, we return `false`.
     *
     * Time Complexity: O(n), where n is the number of elements in the array, because
     *                  we iterate through the array only once. HashMap operations
     *                  (put and get) take O(1) time on average.
     * Space Complexity: O(min(n, k)). The size of the map is bounded by the number
     *                   of unique elements and the distance `k`.
     *
     * @param nums The input array of integers.
     * @param k    The maximum allowed distance between the indices of two duplicate elements.
     * @return true if a nearby duplicate exists, false otherwise.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 1. Map to store the most recent index of each number encountered.
        Map<Integer, Integer> lastSeenIndex = new HashMap<>();

        // 2. Iterate through the array.
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];

            // 3a. Check if we have seen this number before.
            if (lastSeenIndex.containsKey(currentNum)) {
                // 3b. If yes, check if the distance is within k.
                int previousIndex = lastSeenIndex.get(currentNum);
                if (i - previousIndex <= k) {
                    // 3c. Found a nearby duplicate.
                    return true;
                }
            }
            
            // 3d. Update the map with the current number's most recent index.
            lastSeenIndex.put(currentNum, i);
        }

        // 4. If the loop completes, no nearby duplicates were found.
        return false;
    }
}
