/**
 * Solution for the "Two Sum II - Input array is sorted" problem.
 *
 * This class provides a method to find two numbers in a sorted array that add up to a specific target number.
 * The implementation uses the Two-Pointer technique for an optimal O(n) time complexity.
 */
class Solution {
    /**
     * Finds the 1-based indices of two numbers in a sorted array that sum up to the target.
     *
     * @param numbers The 1-indexed, sorted array of integers.
     * @param target The target sum.
     * @return An array of two integers: [index1, index2], where index1 < index2,
     * and numbers[index1 - 1] + numbers[index2 - 1] == target.
     * Returns [-1, -1] if no such pair is found (though the problem guarantees a unique solution).
     *
     * Time Complexity: O(n), where n is the length of the array, as each element is processed at most once.
     * Space Complexity: O(1), as only a constant amount of extra space is used for the pointers.
     */
    public int[] twoSum(int[] numbers, int target) {
        // Initialize two pointers: 'left' at the beginning and 'right' at the end of the array.
        int left = 0;
        int right = numbers.length - 1;

        // Iterate while the pointers have not crossed.
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];

            if (currentSum == target) {
                // Found the pair. The problem requires 1-based indices, so we add 1 to the 0-based indices.
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                // The current sum is too small.
                // To increase the sum, we must move the 'left' pointer to the right to include a larger number.
                left++;
            } else { // currentSum > target
                // The current sum is too large.
                // To decrease the sum, we must move the 'right' pointer to the left to include a smaller number.
                right--;
            }
        }

        // If the loop finishes without finding a pair, return [-1, -1].
        // Note: The standard problem constraints usually guarantee a solution exists.
        return new int[]{-1, -1};
    }
}