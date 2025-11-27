/**
 * Implements the Heap Sort algorithm to sort an array of integers in ascending order.
 * This version uses an optimized, non-recursive (iterative) heapify method.
 * Time Complexity: O(n log n).
 * Space Complexity: O(1) (in-place sorting).
 */
class Solution {
    
    /**
     * Sorts the given array of integers using the Heap Sort algorithm.
     *
     * @param nums The array to be sorted.
     * @return The sorted array.
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }

        int length = nums.length;
        
        // 1. Build Max-Heap: Rearrange array into a Max-Heap.
        // Start from the last non-leaf node (length/2 - 1) up to the root (0).
        // 
        for (int i = length / 2 - 1; i >= 0; i--) {
            // The 0-based index of the last non-leaf node is floor(length/2) - 1.
            heapify(nums, i, length);
        }

        // 2. Heap Sort: Extract elements one by one from the heap.
        // The array will be sorted in place.
        for (int i = length - 1; i > 0; i--) {
            // Swap the current root (largest element) with the last element of the unsorted part (index i).
            swap(nums, 0, i);

            // Call max-heapify on the reduced heap (size 'i').
            // The largest element is now at index i, which is excluded from the new heap.
            heapify(nums, 0, i);
        }
        
        return nums;
    }

    // --- Core Helper Functions ---

    /**
     * Iteratively maintains the Max-Heap property in a subtree rooted at 'start'.
     * This function is non-recursive.
     *
     * @param nums The array representing the heap.
     * @param start The index of the root of the subtree to heapify.
     * @param end The exclusive boundary of the heap (the size of the heap is 'end').
     */
    private void heapify(int[] nums, int start, int end) {
        // 'curr' tracks the current node we are comparing/swapping down the heap.
        int curr = start; 

        // Loop until 'curr' is a leaf node or the Max-Heap property is satisfied at 'curr'.
        while (true) {
            int parent = curr;
            int left = curr * 2 + 1; // Left child index
            int right = curr * 2 + 2; // Right child index
            
            // Assume the current parent is the largest.
            int largest = parent; 

            // 1. Check if the left child exists and is larger than the current largest.
            if (left < end && nums[left] > nums[largest]) {
                largest = left;
            }

            // 2. Check if the right child exists and is larger than the current largest.
            // Note: This correctly compares the right child against the largest found so far (parent or left).
            if (right < end && nums[right] > nums[largest]) {
                largest = right;
            }

            // If the largest element is the parent itself, the heap property is satisfied.
            if (largest == parent) {
                break;
            }

            // If the largest is a child, swap it with the parent.
            swap(nums, parent, largest);
            
            // Move 'curr' down to the child's position to continue heapifying the sub-tree.
            curr = largest; 
        }
    }

    /**
     * Helper method to swap two elements in the array.
     * @param nums The array.
     * @param i Index of the first element.
     * @param j Index of the second element.
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}