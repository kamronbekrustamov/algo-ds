import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Solution class for generating permutations.
 */
class Solution {

    /**
     * Generates all possible permutations of an array of distinct integers.
     * <p>
     * This implementation uses a backtracking algorithm with in-place swapping
     * to minimize memory overhead and avoid unnecessary array allocations.
     * </p>
     *
     * @param nums The input array of distinct integers.
     * @return A list of lists, where each inner list is a unique permutation.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge case handling
        if (nums == null || nums.length == 0) {
            return result;
        }

        // Convert int[] to ArrayList<Integer> to facilitate easier swapping using Collections
        List<Integer> numsList = new ArrayList<>(nums.length);
        for (int num : nums) {
            numsList.add(num);
        }

        backtrack(nums.length, numsList, result, 0);
        return result;
    }

    /**
     * Helper method to generate permutations using backtracking.
     *
     * @param n      The length of the input array.
     * @param nums   The current state of the permutation list.
     * @param result The list to store all completed permutations.
     * @param first  The current index we are considering for swapping.
     */
    private void backtrack(int n, List<Integer> nums, List<List<Integer>> result, int first) {
        // Base Case: If the current index 'first' reaches the size of the array,
        // we have a complete permutation.
        if (first == n) {
            // We must create a new ArrayList (deep copy) because 'nums' is passed by reference.
            // If we added 'nums' directly, all entries in 'result' would look identical at the end.
            result.add(new ArrayList<>(nums));
            return;
        }

        for (int i = first; i < n; i++) {
            // Step 1: Place the i-th integer at the 'first' position
            Collections.swap(nums, first, i);

            // Step 2: Recurse to generate permutations for the rest of the array
            backtrack(n, nums, result, first + 1);

            // Step 3: Backtrack (Undo the swap) to restore the original state
            // so the next iteration can try a different number at 'first'.
            Collections.swap(nums, first, i);
        }
    }
}