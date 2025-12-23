import java.util.HashSet;
import java.util.Set;

/**
 * Solution class for the Longest Consecutive Sequence problem.
 * Finds the length of the longest consecutive elements sequence in an unsorted array of integers.
 * The algorithm achieves an optimal time complexity of O(n).
 */
class Solution {
    /**
     * Finds the length of the longest consecutive elements sequence.
     * * @param nums The unsorted array of integers.
     * @return The length of the longest consecutive sequence.
     */
    public int longestConsecutive(int[] nums) {
        // Handle edge case for an empty or null array
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 1. Store all numbers in a HashSet for O(1) average time complexity lookup.
        // This is crucial for achieving O(n) overall time complexity.
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 2. Iterate through the set of unique numbers.
        for (int num : numSet) {
            
            // Optimization: Only start a new sequence check if 'num' is the 
            // potential *start* of a sequence. The start of a sequence is defined 
            // as a number for which 'num - 1' is NOT present in the set.
            // This ensures the inner 'while' loop runs at most 'n' total times across 
            // all iterations, keeping the complexity at O(n).
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 3. Extend the streak: check for consecutive numbers (currentNum + 1, + 2, ...)
                // until a number is not found in the set.
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // 4. Update the overall longest streak found so far.
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}