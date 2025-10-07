import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a solution for the "Find All Numbers Disappeared in an Array" problem.
 */
class Solution {
    /**
     * Finds all the numbers that are missing from an array containing n integers
     * where each integer is in the range [1, n].
     *
     * The algorithm uses the array itself as a hash map to mark the presence of numbers.
     * It iterates through the array and for each number, it marks the corresponding index
     * as negative. A second pass over the array identifies the indices that are still
     * positive, which correspond to the missing numbers.
     *
     * @param nums The input array of integers.
     * @return A list of integers that are missing from the input array.
     * 
     * Time complexity: O(n), where n is the number of elements in the array.
     * Space complexity: O(1), excluding the space required for the result list.
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // First pass: Mark the presence of each number by negating the value
        // at the corresponding index. For a number `val`, the corresponding index is `val - 1`.
        for (int i = 0; i < nums.length; i++) {
            int indexToMark = Math.abs(nums[i]) - 1;
            // We use Math.abs on nums[indexToMark] because it might have been negated already.
            if (nums[indexToMark] > 0) {
                nums[indexToMark] = -nums[indexToMark];
            }
        }

        List<Integer> res = new ArrayList<>();
        // Second pass: Find the indices that were not marked (i.e., the values are still positive).
        // These indices correspond to the missing numbers.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                // The number `i + 1` is missing because the value at index `i` was not negated.
                res.add(i + 1);
            }
        }

        return res;
    }
}
