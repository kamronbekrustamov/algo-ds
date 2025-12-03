import java.util.Arrays;

/**
 * Solves the problem of finding the minimum number of perfect squares that sum up to a given positive integer n.
 * This is a classic application of dynamic programming, relating to Lagrange's four-square theorem.
 * The time complexity is O(n * sqrt(n)).
 */
class Solution {
    /**
     * Calculates the least number of perfect square numbers (1, 4, 9, 16, ...) 
     * which sum to n.
     *
     * @param n The target positive integer.
     * @return The minimum number of perfect squares that sum up to n.
     */
    public int numSquares(int n) {
        // dp[i] stores the minimum number of perfect squares that sum up to i.
        // The array size is n + 1 to account for numbers from 0 to n.
        int[] dp = new int[n + 1];

        // Initialize the dp array. The maximum possible value for any number is n 
        // (since 1*1 + 1*1 + ... + 1*1 = n), so n+1 serves as a sentinel value
        // for "infinity" to ensure Math.min works correctly for the first update.
        // Note: According to Lagrange's four-square theorem, the result is at most 4.
        Arrays.fill(dp, n + 1);

        // Base case: 0 requires 0 perfect squares.
        dp[0] = 0;

        // Iterate through all possible target numbers from 1 up to n.
        for (int i = 1; i <= n; i++) {
            // Check all possible perfect squares (j*j) less than or equal to the current number i.
            // i = j*j + (i - j*j)
            for (int j = 1; j * j <= i; j++) {
                int perfectSquare = j * j;
                
                // The minimum number of squares for i is the minimum of:
                // 1. The current best count for i (which is initially n+1).
                // 2. The minimum count for (i - perfectSquare) PLUS one square (the 'perfectSquare' itself).
                // The recurrence relation is: dp[i] = min(dp[i], dp[i - j*j] + 1)
                dp[i] = Math.min(dp[i], dp[i - perfectSquare] + 1);
            }
        }

        // dp[n] contains the final result for the target number n.
        return dp[n];
    }
}