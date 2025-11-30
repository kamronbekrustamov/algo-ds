import java.util.Arrays;

/**
 * @title Target Sum
 * @description Given an array of non-negative integers nums and an integer target, 
 * you want to build an expression by adding one of the symbols '+' or '-' 
 * before each integer in nums and then concatenate all the integers. 
 * Return the number of different expressions that you can build which 
 * evaluates to target.
 * This problem is transformed into a Subset Sum problem.
 * The number of ways to achieve the target is equal to the number of 
 * subsets P of nums such that sum(P) = (sum(nums) + target) / 2.
 */
class Solution {
    /**
     * Finds the number of ways to assign signs to elements in nums to reach the target sum.
     *
     * @param nums   The array of non-negative integers.
     * @param target The target sum.
     * @return The number of ways to reach the target sum.
     */
    public int findTargetSumWays(int[] nums, int target) {
        // 1. Calculate the total sum of all numbers.
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // 2. Initial Checks (Transformation to Subset Sum)
        
        // Let S_P be the sum of numbers with a '+' sign and S_N be the sum of numbers with a '-' sign.
        // We need: S_P - S_N = target.
        // We also know: S_P + S_N = totalSum.
        // Adding the two equations: 2 * S_P = totalSum + target.
        // Therefore, S_P = (totalSum + target) / 2.
        
        // The problem is now: Find the number of subsets P with sum S_P.
        
        // If (totalSum + target) is negative or odd, S_P cannot be an integer or non-negative,
        // so no valid subset P exists. The count must be 0.
        // totalSum + target must be non-negative and even.
        if (totalSum < Math.abs(target) || (totalSum + target) % 2 != 0) {
            return 0;
        }

        // 3. Define the new target for the Subset Sum problem.
        int subsetTarget = (totalSum + target) / 2;

        // 4. Dynamic Programming: Count subsets that sum up to subsetTarget.
        // dp[i] represents the number of subsets in nums that sum up to i.
        // The size of the DP array is subsetTarget + 1.
        int[] dp = new int[subsetTarget + 1];

        // Base case: There is one way to achieve a sum of 0 (by choosing the empty set).
        dp[0] = 1;
        
        // Optimization: Sorting is not strictly necessary for correctness, but can potentially 
        // improve performance if the smaller numbers are processed first, especially if combined 
        // with early exit conditions (though not included here for the basic DP).
        // Arrays.sort(nums); // Can be removed without affecting correctness.

        // Iterate through each number in nums.
        for (int num : nums) {
            // Update the DP array. We iterate backwards to ensure that each number `num` is
            // used at most once for the current calculation (0/1 Knapsack style).
            for (int i = subsetTarget; i >= num; i--) {
                // To achieve a sum of 'i', we can either:
                // 1. NOT include the current 'num': The number of ways is dp[i].
                // 2. INCLUDE the current 'num': The number of ways is dp[i - num] 
                //    (i.e., the ways to achieve the remaining sum i - num).
                // We add the two possibilities.
                dp[i] = dp[i] + dp[i - num];
            }
        }

        // 5. The result is the number of ways to form the subsetTarget sum.
        return dp[subsetTarget];
    }
}