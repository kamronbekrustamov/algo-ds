class Solution {
    /**
     * Finds the maximum sum of a contiguous subarray within a given array of integers.
     * This problem is solved using **Kadane's Algorithm**, which uses dynamic programming
     * to achieve O(n) time complexity.
     * * The algorithm iterates through the array, keeping track of two main variables:
     * 1. **currentMax**: The maximum sum of a subarray ending at the current position.
     * 2. **globalMax**: The maximum sum found anywhere in the array so far.
     * * @param nums The input array of integers.
     * @return The maximum sum of any contiguous subarray. Returns 0 if the array is null or empty, 
     * though standard problem constraints often guarantee a non-empty array and define 
     * the result for an empty array as a specific minimum value (or handle the single-element case).
     * The current implementation handles the edge case of null/empty array by returning 0.
     */
    public int maxSubArray(int[] nums) {
        // Handle edge case for null or empty array.
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize both max sums to the first element.
        // This is crucial because the array could contain all negative numbers.
        // If all numbers are negative, the max subarray sum is the largest negative number (i.e., nums[0]).
        int currentMax = nums[0]; // Max sum of subarray ending at the current index
        int globalMax = nums[0];  // Overall max sum found so far

        // Start iteration from the second element (index 1).
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // **Correct Kadane's Logic:**
            // The maximum sum ending at 'i' is either:
            // 1. Starting a new subarray at 'num' (i.e., num).
            // 2. Extending the previous maximum subarray by adding 'num' (i.e., currentMax + num).
            // We choose the larger of the two.
            currentMax = Math.max(num, currentMax + num);

            // The overall maximum is the maximum of the current overall maximum and the max sum ending at 'i'.
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }
}