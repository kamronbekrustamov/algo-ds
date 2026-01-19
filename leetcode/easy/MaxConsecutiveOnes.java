/**
 * Problem: Max Consecutive Ones
 * Description: Given a binary array nums, return the maximum number of consecutive 1's in the array.
 */
class Solution {
    /**
     * Finds the maximum number of consecutive 1s in a binary array.
     * * Optimization: 
     * 1. We only update maxCount when a sequence of 1s ends (at a 0).
     * 2. This reduces the number of Math.max calls significantly if the array contains long sequences.
     *
     * @param nums An array of integers (0s and 1s)
     * @return The length of the longest streak of 1s
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;

        for (int num : nums) {
            if (num == 1) {
                currentCount++;
            } else {
                // Only update maxCount when the streak is broken
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                }
                currentCount = 0;
            }
        }

        // Final check to handle cases where the array ends with a 1
        return currentCount > maxCount ? currentCount : maxCount;
    }
}