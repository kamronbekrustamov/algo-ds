import java.util.Map;
import java.util.HashMap;

/**
 * Solution class to find the total number of continuous subarrays whose sum equals a target value k.
 */
class Solution {
    /**
     * Calculates the number of continuous subarrays in 'nums' that sum up to 'k'.
     *
     * This method uses the prefix sum concept and a HashMap to achieve O(N) time complexity.
     * The core idea is: if the cumulative sum up to the current index (currentSum) minus 
     * the target sum (k) equals a previous prefix sum (prefixSum[j]), then the subarray 
     * from index j+1 to the current index must sum to k.
     * * Mathematically: currentSum - k = prefixSum[j]  =>  currentSum - prefixSum[j] = k 
     * * @param nums The input array of integers.
     * @param k    The target sum.
     * @return The count of continuous subarrays whose sum equals k.
     */
    public int subarraySum(int[] nums, int k) {
        // Map to store the frequency of prefix sums encountered so far.
        // Key: The prefix sum value.
        // Value: The number of times this prefix sum has occurred.
        Map<Integer, Integer> prefixMap = new HashMap<>();
        
        // Initialize with a prefix sum of 0 occurring once. 
        // This handles the case where the subarray starts from the beginning (index 0) 
        // and its sum (currentSum) is exactly equal to k.
        prefixMap.put(0, 1);
        
        int currentSum = 0; // Stores the cumulative sum of elements up to the current index.
        int count = 0;      // Stores the final count of subarrays that sum to k.
        
        for (int num : nums) {
            // 1. Update the current prefix sum.
            currentSum += num;
            
            // 2. Check if a previous prefix sum exists that satisfies the condition: 
            //    prefixSum[j] = currentSum - k. 
            //    If it exists, the value of prefixMap.get(currentSum - k) tells us 
            //    how many subarrays ending at the current position sum up to k.
            count += prefixMap.getOrDefault(currentSum - k, 0);
            
            // 3. Update the frequency of the current prefix sum in the map.
            //    This is crucial for subsequent iterations.
            prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}