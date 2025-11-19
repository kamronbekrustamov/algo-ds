import java.util.Arrays;

/**
 * Solution for the Coin Change problem using Dynamic Programming.
 */
class Solution {

    /**
     * Calculates the fewest number of coins needed to make up a given amount.
     *
     * @param coins  An array of integers representing the coin denominations.
     * @param amount The total amount of money.
     * @return The fewest number of coins needed, or -1 if the amount cannot be made.
     */
    public int coinChange(int[] coins, int amount) {
        // Optimization: Sort coins to enable early breaking in the inner loop.
        Arrays.sort(coins);

        // dp[i] stores the minimum coins needed for amount i.
        // Size is amount + 1 because we need to store data for 0 to amount.
        int[] dp = new int[amount + 1];

        // Initialize dp array with a value larger than any possible solution.
        // amount + 1 is effectively "infinity" in this context because
        // even with coins of value 1, the max coins needed is 'amount'.
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins are needed to make amount 0.
        dp[0] = 0;

        // Iterate through every amount from 1 to the target amount.
        for (int i = 1; i <= amount; i++) {
            // Try every coin denomination
            for (int coin : coins) {
                // If the current coin is larger than the current amount i,
                // we cannot use this coin or any subsequent coins (since it's sorted).
                if (coin > i) {
                    break;
                }

                // Recurrence relation:
                // dp[i] = min(current value, 1 coin + result for remainder amount)
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        // Check if the value at dp[amount] is still the initialized "infinity".
        // If so, it means the amount could not be reached.
        return dp[amount] > amount ? -1 : dp[amount];
    }
}