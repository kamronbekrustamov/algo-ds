/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array. 
 * Each element in the array represents your maximum jump length at that position.
 * * Determine if you are able to reach the last index.
 * * The algorithm uses a greedy approach to track the farthest index that can be reached 
 * from any position examined so far.
 */
class Solution {
    /**
     * Checks if it is possible to reach the last index of the array from the first index.
     * * @param nums The array where nums[i] is the maximum jump length from index i.
     * @return true if the last index is reachable, false otherwise.
     */
    public boolean canJump(int[] nums) {
        // The target is the last index of the array.
        int targetIndex = nums.length - 1;
        
        // farthestReach tracks the maximum index that can be reached from the current starting index (0) 
        // up to the current index 'i'.
        int farthestReach = 0;
        
        // Iterate through the array. The loop can stop early if the 'farthestReach' 
        // already covers the 'targetIndex'.
        for (int i = 0; i < nums.length; i++) {
            
            // 1. **Check Reachability**: If the current index 'i' is beyond the 'farthestReach', 
            // it means the current index 'i' (and all subsequent indices) cannot be reached. 
            // Therefore, the end of the array is unreachable.
            if (i > farthestReach) {
                return false;
            }
            
            // 2. **Update Farthest Reach**: Calculate the maximum index we can reach from the 
            // current position 'i'. 
            int currentReach = i + nums[i];
            
            // Update the global farthest index reached so far.
            if (currentReach > farthestReach) {
                farthestReach = currentReach;
            }
            
            // 3. **Early Exit (Optimization)**: If the 'farthestReach' is already at or past the 
            // last index, we know the last index is reachable, and we can stop the loop immediately.
            if (farthestReach >= targetIndex) {
                return true;
            }
        }
        
        return farthestReach >= targetIndex; 
    }
}