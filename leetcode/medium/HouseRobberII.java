/**
 * Solution for the House Robber II problem.
 * Houses are arranged in a circle, meaning the first house is adjacent to the last house.
 * This constraint means you cannot rob both the first and the last house simultaneously.
 * The problem is solved by running the standard linear House Robber I DP on two sub-arrays:
 * 1. All houses *excluding* the last house (0 to n-2).
 * 2. All houses *excluding* the first house (1 to n-1).
 * The maximum of these two results is the final answer.
 */
class Solution {
    /**
     * Calculates the maximum amount of money that can be robbed from houses in a circle.
     *
     * @param nums An array representing the amount of money in each house.
     * @return The maximum amount of money that can be robbed.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Base case: If there is only one house, rob it.
        if (nums.length == 1) {
            return nums[0];
        }

        // 1. Max amount by robbing houses from index 0 to n-2 (excluding the last house).
        // The 'end' index in robLinear is exclusive, so we pass nums.length - 1.
        int robExcludingLast = robLinear(nums, 0, nums.length - 1);

        // 2. Max amount by robbing houses from index 1 to n-1 (excluding the first house).
        // The 'end' index in robLinear is exclusive, so we pass nums.length.
        int robExcludingFirst = robLinear(nums, 1, nums.length);

        // The overall maximum is the greater of the two scenarios.
        return Math.max(robExcludingLast, robExcludingFirst);
    }

    /**
     * Helper function to solve the linear House Robber I problem using Dynamic Programming
     * with Space Optimization (O(1) extra space).
     *
     * @param arr The array of house money amounts.
     * @param start The starting index (inclusive) of the houses to consider.
     * @param end The ending index (exclusive) of the houses to consider.
     * @return The maximum amount of money that can be robbed within the specified range.
     */
    private int robLinear(int[] arr, int start, int end) {
        // 'prevRob' stores the max amount robbed up to the *previous* house (i-1).
        int prevRob = 0;
        // 'currentRob' stores the max amount robbed up to the *current* house (i).
        int currentRob = 0;

        // Iterate through the specified sub-array of houses.
        for (int i = start; i < end; i++) {
            int money = arr[i];
            
            // max amount if we choose to rob house 'i' (prevRob + money) 
            // OR if we choose *not* to rob house 'i' (currentRob).
            // This is the max amount for the current step (i).
            int newCurrentRob = Math.max(prevRob + money, currentRob);
            
            // Update for the next iteration:
            // The max amount for the house (i-1) becomes the new max amount for the house (i-2).
            prevRob = currentRob; 
            // The max amount for the house (i) becomes the new max amount for the house (i-1).
            currentRob = newCurrentRob;
        }
        
        // 'currentRob' holds the maximum total amount from 'start' up to 'end-1'.
        return currentRob;
    }
}