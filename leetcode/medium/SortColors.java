class Solution {
    /**
     * Sorts an array containing only 0s, 1s, and 2s (representing colors red, white, and blue)
     * using the Dutch National Flag problem algorithm. This is an in-place, single-pass (O(n)) solution.
     *
     * @param nums The array to be sorted.
     */
    public void sortColors(int[] nums) {
        // 'low' pointer: maintains the boundary for 0s. Everything to the left of 'low' is 0.
        int low = 0;
        
        // 'mid' pointer: the current element being examined.
        // Everything between 'low' and 'mid' is 1.
        int mid = 0;
        
        // 'high' pointer: maintains the boundary for 2s. Everything to the right of 'high' is 2.
        int high = nums.length - 1;

        // Iterate while the 'mid' pointer has not crossed the 'high' pointer.
        while (mid <= high) {
            // Case 1: Current element is 0 (Red).
            // Swap it with the element at 'low' to place the 0 at its correct sorted position.
            if (nums[mid] == 0) {
                // Swap nums[mid] and nums[low]
                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;
                
                // Advance both 'low' and 'mid' pointers. 
                // The element swapped into nums[mid] must be 1 (from the invariant), 
                // so we can safely advance 'mid'.
                low++;
                mid++;
            }
            // Case 2: Current element is 2 (Blue).
            // Swap it with the element at 'high' to place the 2 at its correct sorted position.
            else if (nums[mid] == 2) {
                // Swap nums[mid] and nums[high]
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                
                // Decrement 'high'. The element swapped into nums[mid] is *unknown* (it could be 0, 1, or 2), 
                // so we *do not* increment 'mid'. We must re-examine nums[mid] in the next iteration.
                high--;
            }
            // Case 3: Current element is 1 (White).
            // It is already in the correct position (between 0s and 2s).
            else {
                // Simply advance 'mid' to the next element.
                mid++;
            }
        }       
    }
}