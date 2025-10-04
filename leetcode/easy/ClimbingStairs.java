class Solution {
    /**
     * Calculates the number of distinct ways to climb to the top of a staircase.
     *
     * This problem is a classic example of dynamic programming and is equivalent
     * to generating the Fibonacci sequence.
     *
     * Let `dp[i]` be the number of ways to reach the i-th step.
     * To reach step `i`, you can either take one step from `i-1` or two steps from `i-2`.
     * Therefore, the recurrence relation is: `dp[i] = dp[i-1] + dp[i-2]`.
     *
     * This solution uses a space-optimized DP approach. Instead of storing all
     * previous results in an array, we only need to keep track of the last two
     * values (the ways to reach the previous step and the step before that).
     *
     * Time Complexity: O(n), as we iterate from 3 to n once.
     * Space Complexity: O(1), as we only use a few variables to store state.
     *
     * @param n The total number of steps in the staircase.
     * @return The total number of distinct ways to climb to the top.
     */
    public int climbStairs(int n) {
        // Base cases:
        // If n=0, there is 1 way (do nothing).
        // If n=1, there is 1 way (1 step).
        // If n=2, there are 2 ways (1+1 or 2 steps).
        if (n <= 2) {
            return n;
        }

        // `twoStepsBack` stores the number of ways to reach step i-2.
        // Initialize with the ways to reach step 1.
        int twoStepsBack = 1;
        
        // `oneStepBack` stores the number of ways to reach step i-1.
        // Initialize with the ways to reach step 2.
        int oneStepBack = 2;

        // Iterate from the 3rd step up to n.
        for (int i = 3; i <= n; i++) {
            // The number of ways to reach the current step `i` is the sum of the ways
            // to reach the previous two steps.
            int currentWays = oneStepBack + twoStepsBack;
            
            // Update the pointers for the next iteration.
            twoStepsBack = oneStepBack;
            oneStepBack = currentWays;
        }

        // `oneStepBack` now holds the number of ways to reach the n-th step.
        return oneStepBack;
    }
}
