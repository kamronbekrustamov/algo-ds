import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    /**
     * Finds all unique combinations in candidates where the candidate numbers sum to target.
     * Each number in candidates may only be used once in the combination.
     *
     * Time Complexity: O(2^N) - In the worst case, though pruning significantly reduces this.
     * Space Complexity: O(N) - For the recursion stack and temporary list storage.
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // OPTIMIZATION 1: Sorting
        // Sorting is crucial for two reasons:
        // 1. It allows us to stop iterating early if numbers get too large (Pruning).
        // 2. It allows us to easily skip duplicate numbers to avoid duplicate combinations.
        Arrays.sort(candidates);
        
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int start, int remainingTarget, 
                           List<Integer> currentPath, List<List<Integer>> result) {
        
        // Base Case: Target reached
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int currentCandidate = candidates[i];

            // OPTIMIZATION 2: Early Break (Pruning)
            // Since the array is sorted, if the current number is greater than 
            // the remaining target, all subsequent numbers will also be greater.
            // We can safely break the loop here.
            if (currentCandidate > remainingTarget) {
                break;
            }

            // OPTIMIZATION 3: Duplicate Handling
            // If this is not the first number we are checking in this specific recursive step 
            // (i > start) and it is the same as the previous number, skip it.
            // This prevents generating the same combination multiple times.
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Choose
            currentPath.add(currentCandidate);

            // Explore
            // We pass 'i + 1' because we cannot reuse the current element.
            backtrack(candidates, i + 1, remainingTarget - currentCandidate, currentPath, result);

            // Un-choose (Backtrack)
            currentPath.remove(currentPath.size() - 1);
        }
    }
}