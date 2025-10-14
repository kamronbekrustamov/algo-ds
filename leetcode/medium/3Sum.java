
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Solution for the 3Sum problem.
 * The method finds all unique triplets in the array which give the sum of zero.
 * The overall time complexity is O(n^2) due to the nested loop structure, where the outer loop runs n times and the inner two-pointer loop runs n times in the worst case.
 * The space complexity is O(1) for the variables, and the space for the result list is not counted.
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array to enable the two-pointer approach and easily handle duplicates.
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        // Iterate through the array, considering each element as a potential first element of a triplet.
        for (int i = 0; i < length - 2; i++) {
            // Skip duplicate elements for the first element of the triplet to avoid duplicate triplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Use two pointers, one starting from the element after the current one, and one from the end of the array.
            int left = i + 1, right = length - 1;
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];

                // If the sum is less than zero, we need a larger sum, so move the left pointer to the right.
                if (total < 0) {
                    left++;
                }
                // If the sum is greater than zero, we need a smaller sum, so move the right pointer to the left.
                else if (total > 0) {
                    right--;
                }
                // If the sum is zero, we found a triplet.
                else {
                    res.add(List.of(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicate elements for the second element of the triplet.
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    
                    // Skip duplicate elements for the third element of the triplet.
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move both pointers to find the next potential triplet.
                    left++;
                    right--;
                }
            }
        }       
        return res;
    }
}