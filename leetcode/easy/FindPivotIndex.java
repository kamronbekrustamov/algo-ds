import java.util.Arrays;

/**
 * Solution class for finding the pivot index in an array.
 */
class Solution {
    /**
     * Given an array of integers nums, this method finds the pivot index.
     * The pivot index is where the sum of numbers to the left of the index
     * equals the sum of numbers to the right of the index.
     *
     * @param nums The input array of integers.
     * @return The leftmost pivot index, or -1 if no such index exists.
     */
    public int pivotIndex(int[] nums) {
        // First, calculate the total sum of all numbers in the array.
        int totalSum = Arrays.stream(nums).sum();

        // Initialize leftSum, which will store the sum of numbers to the left of the current index.
        int leftSum = 0;

        // Iterate through each element of the array to check if it's a pivot index.
        for (int i = 0; i < nums.length; i++) {
            // The right sum is the total sum minus the left sum and the current element's value.
            int rightSum = totalSum - leftSum - nums[i];

            // Check if the sum of numbers to the left equals the sum of numbers to the right.
            if (leftSum == rightSum) {
                // If they are equal, we've found the pivot index.
                return i;
            }

            // If it's not a pivot, add the current number to the leftSum for the next iteration.
            leftSum += nums[i];
        }

        // If the loop completes without finding a pivot index, it means no pivot exists.
        return -1;
    }
}
