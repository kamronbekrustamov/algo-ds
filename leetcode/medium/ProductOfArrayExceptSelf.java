import java.util.Arrays;

/**
 * Given an integer array 'nums', return an array 'answer' such that 'answer[i]' 
 * is equal to the product of all the elements of 'nums' except 'nums[i]'.
 * The product of any prefix or suffix of 'nums' is guaranteed to fit in a 32-bit integer.
 * * Must run in O(n) time and without using the division operation.
 * The solution is optimized to use O(1) extra space complexity (excluding the output array).
 */
class Solution {
    /**
     * Calculates the product of all elements in the array except the element at the current index.
     * This is achieved in a single pass for left products and a single pass for right products,
     * utilizing the result array 'res' to store intermediate and final results.
     * * The final product for 'res[i]' is: (Product of all elements to the left of i) * (Product of all elements to the right of i).
     *
     * @param nums The input array of integers.
     * @return An array where 'answer[i]' is the product of all elements in 'nums' except 'nums[i]'.
     */
    public int[] productExceptSelf(int[] nums) {
        // 1. Initialize the result array 'res'. 
        // We can reuse this array to store the Left Products first, and then multiply by the Right Products.
        // It's initialized to the size of 'nums'.
        int[] res = new int[nums.length];
        
        // This is a stylistic choice; the loops below can handle the initialization implicitly.
        // However, it explicitly sets the stage for the logic: the product is initialized to 1.
        Arrays.fill(res, 1); 

        // --- 2. Calculate Left Products (Prefix Products) ---
        // 'res[i]' will temporarily store the product of all elements to the LEFT of 'nums[i]'.
        int leftProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            // 'res[i]' gets the product of elements *before* 'nums[i]'.
            // For i=0, 'leftProduct' is 1, so res[0] = 1 (empty prefix product).
            res[i] = leftProduct;
            
            // Update 'leftProduct' to include 'nums[i]' for the *next* iteration (i+1).
            leftProduct *= nums[i];
        }
        
        // Example after this loop for nums = [1, 2, 3, 4]:
        // res = [1, 1, 2, 6]
        
        // --- 3. Calculate Right Products (Suffix Products) and Final Result ---
        // 'rightProduct' will hold the product of all elements to the RIGHT of the current index.
        int rightProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 'res[i]' currently holds the Left Product.
            // Multiply it by the 'rightProduct' (product of elements *after* 'nums[i]') 
            // to get the final result: Left Product * Right Product.
            // For i=N-1, 'rightProduct' is 1, so res[N-1] is just the Left Product (multiplied by 1).
            res[i] = res[i] * rightProduct;
            
            // Update 'rightProduct' to include 'nums[i]' for the *next* iteration (i-1).
            rightProduct *= nums[i];
        }

        // The 'res' array now contains the final answer.
        return res;
    }
}