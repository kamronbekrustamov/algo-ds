/**
 * Solution for LeetCode problem "Minimum Value to Get Positive Step by Step Sum".
 * This class provides a method to find the minimum positive `startValue` such that
 * the step-by-step sum of `startValue` plus elements in `nums` is never less than 1.
 */
class Solution {

    /**
     * Calculates the minimum positive `startValue` such that the step-by-step sum
     * of `startValue` plus elements in `nums` is always greater than or equal to 1.
     *
     * The approach is to track the minimum running sum encountered while iterating through `nums`.
     * Let `running_sum` be the sum of `startValue` and the elements of `nums` up to the current point.
     * We want `running_sum >= 1` at all times.
     * This is equivalent to `startValue + (sum of nums elements up to current point) >= 1`.
     * So, `startValue >= 1 - (sum of nums elements up to current point)`.
     *
     * To satisfy this for all steps, `startValue` must be greater than or equal to
     * `1 - (minimum sum of nums elements encountered during traversal)`.
     * If the minimum sum of `nums` elements is `min_prefix_sum` (which can be negative),
     * then `startValue` must be at least `1 - min_prefix_sum`.
     *
     * @param nums An array of integers.
     * @return The minimum positive `startValue`.
     */
    public int minStartValue(int[] nums) {
        // `min_running_sum` tracks the minimum prefix sum of the `nums` array encountered so far.
        // Initialize to 0, as an empty prefix sum is 0.
        int min_running_sum = 0;
        // `running_sum` tracks the current prefix sum of the `nums` array.
        int running_sum = 0;

        // Iterate through each number in the array.
        for (int num : nums) {
            // Update the current running sum.
            running_sum += num;
            // Update the minimum running sum encountered.
            min_running_sum = Math.min(min_running_sum, running_sum);
        }

        // The minimum startValue needed is 1 minus the minimum running sum encountered.
        // If min_running_sum is 0 or positive, 1 - min_running_sum will be 1 or less, but we need positive startValue.
        // If min_running_sum is negative, e.g., -5, then 1 - (-5) = 6. A startValue of 6 ensures the sum never drops below 1.
        return 1 - min_running_sum;
    }
}
