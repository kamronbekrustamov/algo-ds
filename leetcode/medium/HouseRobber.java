class Solution {
    /**
     * Solves the House Robber problem using dynamic programming with space optimization.
     * * The goal is to maximize the money stolen without robbing two adjacent houses.
     * The recurrence relation is: dp[i] = max(nums[i] + dp[i-2], dp[i-1]).
     * * @param nums An array representing the money in each house.
     * @return The maximum amount of money that can be robbed.
     */
    public int rob(int[] nums) {
        // 'prev2' stores the maximum amount from two steps back (dp[i-2]).
        int prev2 = 0; 
        
        // 'prev1' stores the maximum amount from one step back (dp[i-1]).
        int prev1 = 0; 

        for (int num : nums) {
            // 1. Calculate the maximum money if we *rob* the current house:
            //    This is the current house's money plus the max money from two steps back (prev2).
            int maxIfRobbing = num + prev2;
            
            // 2. The new overall max money up to the current house is the maximum of:
            //    a) Robbing the current house (maxIfRobbing)
            //    b) NOT robbing the current house (which means the max is simply prev1)
            int currentMax = Math.max(maxIfRobbing, prev1);
            
            // Update the state for the next iteration:
            // The new 'prev2' for the next step (i+1) becomes the old 'prev1' (i).
            prev2 = prev1;
            
            // The new 'prev1' for the next step (i+1) becomes the 'currentMax' we just calculated.
            prev1 = currentMax;
        }

        // 'prev1' holds the max money after iterating through the entire array.
        return prev1;
    }
}