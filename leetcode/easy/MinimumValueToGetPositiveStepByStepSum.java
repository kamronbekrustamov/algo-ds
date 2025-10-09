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
        int minRunningSum = 0;
        int runningSum = 0;

        for (int num : nums) {
            runningSum += num;
            minRunningSum = Math.min(minRunningSum, runningSum);
        }

        return 1 - minRunningSum;
    }
}
