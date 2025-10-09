/**
 * Solution for LeetCode problem "Missing Number".
 * This class provides a method to find the missing number in an array containing n distinct numbers
 * taken from 0, 1, 2, ..., n.
 */
class Solution {

    /**
     * Finds the missing number in an array containing n distinct numbers taken from 0, 1, 2, ..., n.
     *
     * This method uses the sum approach:
     * 1. Calculate the expected sum of numbers from 0 to n using the formula `n * (n + 1) / 2`.
     * 2. Calculate the actual sum of numbers present in the given array `nums`.
     * 3. The missing number is the difference between the expected sum and the actual sum.
     *
     * This approach is efficient with O(N) time complexity (for iterating through the array once)
     * and O(1) space complexity.
     *
     * @param nums An array containing n distinct numbers taken from 0, 1, ..., n.
     * @return The number that is missing from the array.
     */
    public int missingNumber(int[] nums) {
        // Get the length of the array, which is 'n' in the problem description
        // (since the numbers are from 0 to n, and one is missing, the array has n elements).
        int n = nums.length;

        // Calculate the expected sum of numbers from 0 to n using Gauss's formula.
        int expectedSum = n * (n + 1) / 2;

        // Calculate the actual sum of numbers present in the given array.
        int actualSum = 0;
        for (int num : nums) {
            actual_sum += num;
        }

        // The missing number is the difference between the expected sum and the actual sum.
        return expectedSum - actualSum;
    }
}
