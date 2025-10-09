import java.util.List;

class Solution {
    /**
     * Calculates the sum of XOR totals for all possible subsets of the input array.
     *
     * This solution uses a recursive (backtracking) approach to explore all subsets.
     * For each element in the array, we make two choices:
     * 1. Include the element in the current subset.
     * 2. Exclude the element from the current subset.
     *
     * The function recursively builds the XOR total for all subsets and sums them up.
     *
     * Time Complexity: O(2^n), as we explore every possible subset.
     * Space Complexity: O(n) due to the recursion depth.
     *
     * @param nums A list of integers.
     * @return The sum of the XOR totals of all subsets.
     */
    public int subsetXORSum(int[] nums) {
        return calculateSum(nums, 0, 0);
    }

    /**
     * A recursive helper function to calculate the sum of XOR totals.
     *
     * @param nums The input array of integers.
     * @param index The current index in the `nums` array to consider.
     * @param current_xor_total The XOR total of the subset built so far.
     * @return The sum of XOR totals for all subsets that can be formed from this point.
     */
    private int calculateSum(int[] nums, int index, int currentXorTotal) {
        // Base case: If we have considered all numbers, the XOR total of the
        // current subset is complete. Return it to be added to the overall sum.
        if (index == nums.length) {
            return currentXorTotal;
        }

        // --- Recursive Step ---
        // For each element `nums[index]`, we explore two branches:

        // 1. Include `nums[index]` in the current subset.
        //    We update the XOR total by XORing it with the current number.
        //    Then, we recurse to the next element.
        int sumWithCurrent = calculateSum(nums, index + 1, currentXorTotal ^ nums[index]);

        // 2. Exclude `nums[index]` from the current subset.
        //    The XOR total remains the same.
        //    Then, we recurse to the next element.
        int sumWithoutCurrent = calculateSum(nums, index + 1, currentXorTotal);

        // The total sum from this point is the sum of the results from both branches.
        return sumWithCurrent + sumWithoutCurrent;
    }

    /*
    // --- Alternative O(n) Linear Time Solution ---
    //
    // /**
    //  * Calculates the sum of XOR totals for all subsets in linear time.
    //  *
    //  * Mathematical Insight:
    //  * Consider the contribution of a single bit position (e.g., the i-th bit)
    //  * to the final sum across all subsets.
    //  *
    //  * Let's say at least one number in `nums` has the i-th bit set.
    //  * - For a subset's XOR total to have the i-th bit set, an ODD number of
    //  *   elements in that subset must have their i-th bit set.
    //  * - Let `n` be the total number of elements. There are `2^n` subsets in total.
    //  * - It can be shown that if at least one number has the i-th bit set,
    //  *   exactly half of the subsets (`2^(n-1)`) will have an odd number of
    //  *   elements with that bit set.
    //  * - Therefore, for `2^(n-1)` subsets, their XOR total will have the i-th bit set.
    //  *
    //  * The contribution of the i-th bit to the final sum is:
    //  * (Number of subsets where this bit is 1) * (Value of this bit)
    //  * = (2^(n-1)) * (2^i)
    //  *
    //  * The final sum is the sum of these contributions for all bit positions `i`
    //  * that are set in at least one number in `nums`.
    //  *
    //  * This is equivalent to:
    //  * (OR of all numbers) * (2^(n-1))
    //  *
    //  * The OR of all numbers (`bitwiseOrTotal`) gives us a number where the
    //  * i-th bit is 1 if and only if the i-th bit was set in at least one of the
    //  * original numbers.
    //  *
    //  * Time Complexity: O(n) to compute the bitwise OR of all numbers.
    //  * Space Complexity: O(1).
    //  *
    //  * @param nums A list of integers.
    //  * @return The sum of the XOR totals of all subsets.
    //  */
    // public int subsetXORSum_linear(int[] nums) {
    //     int n = nums.length;
    //     int bitwiseOrTotal = 0;
    //     for (int num : nums) {
    //         bitwiseOrTotal |= num;
    //     }
    //
    //     // The multiplier is 2^(n-1)
    //     // Handle n=0 case: 2^(-1) is not meaningful. If nums is empty, sum is 0.
    //     if (n == 0) return 0;
    //     int multiplier = 1 << (n - 1);
    //
    //     return bitwiseOrTotal * multiplier;
    // }
    
}
