class Solution {
    public int lastStoneWeightII(int[] stones) {
        // Smashing stones together is equivalent to splitting them into two
        // piles and taking the difference of their sums. We want that
        // difference as small as possible, so we look for a subset whose
        // sum is as close as possible to half the total (without going over).
        int total = 0;
        for (int stone : stones) {
            total += stone;
        }
        int target = total / 2;

        // dp[i] = true if some subset of stones sums to exactly i.
        // dp[0] is trivially true (the empty subset sums to 0).
        boolean[] reachable = new boolean[target + 1];
        reachable[0] = true;

        // Standard 0/1 knapsack: for each stone, decide whether to include it.
        // Iterating the sum backwards (high -> low) ensures each stone is only
        // used once per outer iteration (prevents reusing the same stone twice).
        for (int stone : stones) {
            for (int sum = target; sum >= stone; sum--) {
                if (reachable[sum - stone]) {
                    reachable[sum] = true;
                }
            }
        }

        // Find the largest achievable subset sum <= target.
        // That subset becomes one pile; the rest of the stones form the other.
        int bestPileSum = 0;
        for (int sum = target; sum >= 0; sum--) {
            if (reachable[sum]) {
                bestPileSum = sum;
                break;
            }
        }

        // The two piles are bestPileSum and (total - bestPileSum).
        // Their difference is the minimum possible final stone weight.
        return total - 2 * bestPileSum;
    }
}