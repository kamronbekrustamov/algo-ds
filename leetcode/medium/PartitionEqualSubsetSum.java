import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    /**
     * Checks if the array 'nums' can be partitioned into two subsets with equal sums.
     * This is equivalent to finding a subset that sums up to TotalSum / 2.
     *
     * @param nums The input array of positive integers.
     * @return true if the array can be partitioned into two equal sum subsets, false otherwise.
     */
    public boolean canPartition(int[] nums) {
        // 1. Calculate Total Sum
        // The problem requires an even total sum to be solvable.
        int totalSum = IntStream.of(nums).sum();

        if (totalSum % 2 != 0) {
            // If the total sum is odd, it's impossible to divide it into two equal integer sums.
            return false;
        }

        // 2. Define the Target Sum
        // We need to find if there is a subset that sums up to this target.
        int target = totalSum / 2;

        // 3. Initialize DP Array
        // dp[i] will be true if a subset of the numbers processed so far can sum up to 'i'.
        // The size is (target + 1) to account for sums from 0 up to 'target'.
        boolean[] dp = new boolean[target + 1];

        // Base case: A sum of 0 is always achievable (by selecting an empty subset).
        dp[0] = true;

        // 4. Populate the DP Table (0/1 Knapsack-like approach)
        // Iterate through each number in the input array.
        for (int num : nums) {
            // Iterate backward from 'target' down to 'num'.
            // The backward loop ensures that each number 'num' is used at most ONCE (0/1 choice).
            // This prevents the same number from contributing multiple times to a single subset sum.
            for (int currentSum = target; currentSum >= num; currentSum--) {
                // If a sum of (currentSum - num) was previously achievable (dp[currentSum - num] is true),
                // then adding the current 'num' to that subset makes 'currentSum' also achievable.
                dp[currentSum] = dp[currentSum] || dp[currentSum - num];
            }

            // Early exit optimization: If the target sum is reached, we can stop immediately.
            if (dp[target]) {
                return true;
            }
        }

        // 5. Final Result
        // After processing all numbers, dp[target] indicates if the target sum is possible.
        return dp[target];
    }
}