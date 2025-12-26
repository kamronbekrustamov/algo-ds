/**
 * Solution for "Remove Duplicates from Sorted Array II".
 * Allows at most two occurrences of each element in the final array.
 */
class Solution {
    /**
     * Removes duplicates from a sorted array in-place such that each unique element
     * appears at most twice.
     *
     * @param nums The input array, sorted in non-decreasing order.
     * @return The new length of the array with duplicates removed (k).
     * The first k elements of nums contain the result.
     */
    public int removeDuplicates(int[] nums) {
        // Handle edge cases for null or empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Handle case where array has 0, 1, or 2 elements.
        // In these cases, no element can appear more than twice, so the length is unchanged.
        if (nums.length <= 2) {
            return nums.length;
        }

        // k is the "slow" pointer (or the write index) that tracks the position
        // where the next valid element should be placed.
        // The first two elements (nums[0] and nums[1]) are always valid in the result
        // because the array length is guaranteed to be >= 2 at this point.
        int k = 2;

        // i is the "fast" pointer (or the read index) that iterates through the original array.
        // Start from index 2 since nums[0] and nums[1] are already considered valid.
        for (int i = 2; i < nums.length; i++) {
            // The core logic:
            // Check if the current element (nums[i]) is *different* from the element
            // two positions before the current write position (nums[k - 2]).
            // If it is different, it means we have found a number that is either:
            // 1. A completely new number (e.g., [1, 1, **2**, ...] -> k-2=0, nums[0]=1, nums[i]=2)
            // 2. The *second* occurrence of the current number (e.g., [1, 1, 2, 2, **3**, ...] -> k-2=2, nums[2]=2, nums[i]=3)
            // In either case, placing nums[i] at nums[k] will *not* create a triple duplicate.
            if (nums[k - 2] != nums[i]) {
                // Place the valid element into the next slot of the result array
                nums[k] = nums[i];
                // Move the write pointer forward to prepare for the next element
                k++;
            }
            // If nums[k - 2] == nums[i], it means nums[i] is the *third* (or more) occurrence
            // of the number currently at nums[k - 2] and nums[k - 1].
            // We skip nums[i] by not incrementing k, effectively removing it.
        }

        // k represents the new length of the modified array.
        return k;
    }
}