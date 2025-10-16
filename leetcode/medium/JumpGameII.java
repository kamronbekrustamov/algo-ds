/**
 * Optimized Solution for Jump Game II (Minimum Jumps to Reach the End).
 * * Time Complexity: O(N) - We iterate through the array exactly once.
 * Space Complexity: O(1) - We only use a few integer variables.
 */
class Solution {
    /**
     * Calculates the minimum number of jumps required to reach the last index.
     * * @param nums An array of non-negative integers where nums[i] is the maximum jump length from index i.
     * @return The minimum number of jumps to reach nums.length - 1.
     */
    public int jump(int[] nums) {
        // Base case: If the array has 0 or 1 element, 0 jumps are needed.
        if (nums.length <= 1) {
            return 0;
        }

        int jumps = 0;              // The total minimum number of jumps taken.
        int currentReach = 0;       // The farthest index reachable with the current number of jumps ('jumps').
        int farthestReach = 0;      // The farthest index we can reach *globally* so far, considering all indices up to 'i'.

        // We only need to iterate up to the second-to-last element (nums.length - 2),
        // as reaching currentReach >= nums.length - 1 means we're done.
        for (int i = 0; i < nums.length - 1; i++) {
            
            // 1. Update the overall farthest reachable index (greedy choice).
            // We constantly look for the best potential next jump within the current range.
            farthestReach = Math.max(farthestReach, i + nums[i]);

            // 2. Check if we've reached the limit of the current jump.
            // When 'i' hits 'currentReach', it means we've explored all spots
            // reachable from the *previous* jump and must take a new one.
            if (i == currentReach) {
                // Take the jump.
                jumps++;
                
                // Update the new limit for the next jump.
                // The new 'currentReach' is the 'farthestReach' we found while exploring 
                // the previous jump's range.
                currentReach = farthestReach;
                
                // Early exit: If the new 'currentReach' is already at or past the end,
                // we've found the minimum number of jumps.
                if (currentReach >= nums.length - 1) {
                    return jumps;
                }
            }
        }
        
        // This line handles the case where the loop completes without the early exit 
        // (which shouldn't happen if a solution exists, but is a safe fallback).
        return jumps;          
    }
}