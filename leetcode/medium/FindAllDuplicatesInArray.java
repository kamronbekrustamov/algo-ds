import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Finds all elements that appear twice in an array of integers.
 * The array is assumed to contain numbers in the range [1, n], where n is the size of the array.
 * This solution modifies the input array in place to track visited elements.
 *
 * Constraints:
 * - 1 <= a[i] <= n
 * - Each element appears once or twice.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1) (excluding the space required for the output list)
 */
class Solution {

    /**
     * Finds the duplicates in the array using the array elements themselves as a hash map.
     * The sign of the element at the index corresponding to a number is used to mark it as 'visited'.
     *
     * @param nums The input array where 1 <= nums[i] <= n.
     * @return A list of integers that appear twice. Returns an empty list if nums is null.
     */
    public List<Integer> findDuplicates(int[] nums) {
        // 1. Handle edge case for null input
        if (nums == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();

        // 2. Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // Get the value and determine the index it maps to.
            // We use Math.abs() because the number at the index might have been
            // negated in a previous iteration. The target index is (value - 1).
            int value = Math.abs(nums[i]);
            int index = value - 1;

            // 3. Check the sign at the target index
            if (nums[index] > 0) {
                // First time seeing this number 'value'.
                // Mark its corresponding index as 'visited' by making the element negative.
                // Multiplication is safer than direct negation in a loop if the element
                // could be Integer.MIN_VALUE, though it's unnecessary here since
                // constraints mean values are positive initially.
                nums[index] *= -1;
            } else {
                // Second time seeing this number 'value' because nums[index] is negative.
                // This means 'value' is a duplicate.
                result.add(value);
            }
        }

        // 4. Note: The input array 'nums' is modified (contains negative numbers) after this operation.
        return result;
    }
}