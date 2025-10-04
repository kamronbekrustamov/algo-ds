import java.util.HashMap; // Import HashMap for O(1) average time complexity for key-value operations.
import java.util.Map;     // Import the Map interface, which is good practice for coding against interfaces.

/**
 * Solution class to find the length of the shortest contiguous subarray
 * with the same degree as the entire array.
 * 
 * The degree of an array is defined as the maximum frequency of any of its elements.
 * For example, the array [1, 2, 2, 3, 1] has a degree of 2 because both 1 and 2 appear twice.
 * The shortest subarray with this degree is [2, 2], with a length of 2.
 */
class Solution {
    /**
     * Finds the length of the shortest contiguous subarray that has the same degree as the input array.
     * 
     * This solution uses a single-pass approach with two hash maps to efficiently track the necessary information.
     * 
     * Algorithm:
     * 1. Iterate through the array once.
     * 2. For each number, track its frequency and the index of its first occurrence.
     * 3. Keep track of the maximum frequency (the degree) found so far.
     * 4. If a number's frequency exceeds the current maximum degree, update the maximum degree
     *    and calculate the new shortest subarray length (from its first occurrence to the current index).
     * 5. If a number's frequency equals the current maximum degree, check if its subarray
     *    (from its first occurrence to the current index) is shorter than the one found so far.
     * 
     * Time Complexity: O(n), where n is the number of elements in the array. We iterate through the array only once.
     * Space Complexity: O(k), where k is the number of unique elements in the array. In the worst case, this is O(n).
     * 
     * @param nums The input array of integers.
     * @return The length of the shortest contiguous subarray with the same degree as the array.
     */
    public int findShortestSubArray(int[] nums) {
        // Map to store the frequency of each number. Key: number, Value: count.
        Map<Integer, Integer> frequencyCount = new HashMap<>();
        
        // Map to store the first index at which each number appears. Key: number, Value: index.
        Map<Integer, Integer> firstIndex = new HashMap<>();
        
        int maxFrequency = 0;          // Stores the degree of the array (maximum frequency).
        int minSubarrayLength = Integer.MAX_VALUE; // Stores the length of the shortest subarray found.

        // Iterate through the array to populate the maps and find the answer.
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // Record the first occurrence of the number. putIfAbsent only adds the key-value pair
            // if the key is not already in the map.
            firstIndex.putIfAbsent(num, i);

            // Update the frequency of the current number. getOrDefault is a clean way to handle
            // numbers seen for the first time.
            int currentFrequency = frequencyCount.getOrDefault(num, 0) + 1;
            frequencyCount.put(num, currentFrequency);

            // Get the starting index of the subarray for this number.
            int startIndex = firstIndex.get(num);

            if (currentFrequency > maxFrequency) {
                // We have found a number with a higher frequency (a new degree).
                // This becomes our new candidate, so we update maxFrequency and
                // calculate the subarray length from its first occurrence to the current index.
                maxFrequency = currentFrequency;
                minSubarrayLength = i - startIndex + 1;
            } else if (currentFrequency == maxFrequency) {
                // We have found another number with the same frequency as the current maxFrequency.
                // We must check if its subarray is shorter than the one we have already found.
                // The length of the subarray for 'num' is (current index - first index + 1).
                minSubarrayLength = Math.min(minSubarrayLength, i - startIndex + 1);
            }
        }

        return minSubarrayLength;
    }
}