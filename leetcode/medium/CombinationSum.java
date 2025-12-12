import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    /**
     * Solves the Combination Sum problem using Depth First Search (DFS) and Backtracking.
     * * <p><strong>Time Complexity:</strong> O(N^(T/M + 1)), where N is the number of candidates, 
     * T is the target value, and M is the minimal value among the candidates.
     * <br><strong>Space Complexity:</strong> O(T/M) for the recursion stack depth.</p>
     *
     * @param candidates An array of distinct integers.
     * @param target The target integer sum.
     * @return A list of all unique combinations that sum to the target.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Edge case handling
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        // Sorting is essential for the pruning logic to work effectively.
        Arrays.sort(candidates);

        backtrack(0, target, candidates, new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper method to perform the backtracking search.
     *
     * @param start The index in 'candidates' to start searching from (avoids duplicates).
     * @param remain The remaining sum required to reach the target.
     * @param nums The sorted array of candidates.
     * @param path The current combination of numbers being explored.
     * @param result The accumulator for valid combinations.
     */
    private void backtrack(int start, int remain, int[] nums, List<Integer> path, List<List<Integer>> result) {
        // Base Case: Target reached
        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Recursive Step
        for (int i = start; i < nums.length; i++) {
            // Pruning: Because the array is sorted, if the current number exceeds
            // the remaining sum, all subsequent numbers will also exceed it.
            if (nums[i] > remain) {
                break;
            }

            path.add(nums[i]);

            // Recurse with 'i' (not i+1) to allow reusing the same element.
            // The redundant 'if (remain < 0)' base case is removed as this logic 
            // ensures 'remain - nums[i]' is never negative.
            backtrack(i, remain - nums[i], nums, path, result);

            // Backtrack: remove the last element to explore other possibilities.
            path.remove(path.size() - 1);
        }
    }
}