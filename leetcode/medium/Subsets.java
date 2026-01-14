import java.util.ArrayList;
import java.util.List;

/**
 * Solution to generate all possible subsets (the power set) of a set of unique integers.
 */
class Solution {
    
    /**
     * Generates all subsets using an iterative bitmask approach.
     * This avoids the overhead of recursion and allows pre-allocation of the result list.
     * * Time Complexity: O(n * 2^n) - We iterate through 2^n combinations.
     * Space Complexity: O(n * 2^n) - To store the result.
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        // The number of subsets is 2^n
        int numberOfSubsets = 1 << n; 
        List<List<Integer>> result = new ArrayList<>(numberOfSubsets);

        for (int i = 0; i < numberOfSubsets; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if the j-th bit of i is set
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }
        return result;
    }
}