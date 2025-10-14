
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution for the 4Sum problem.
 * The method finds all unique quadruplets in the array which give the sum of the target.
 * The overall time complexity is O(n^3) due to the nested loop structure.
 * The space complexity is O(1) for the variables, and the space for the result list is not counted.
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // Sort the array to enable the two-pointer approach and easily handle duplicates.
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        // Iterate through the array, considering each element as a potential first element of a quadruplet.
        for(int i = 0; i < length - 3; i++) {
            // Skip duplicate elements for the first element to avoid duplicate quadruplets.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Iterate through the array, considering each element as a potential second element of a quadruplet.
            for (int j = i + 1; j < length - 2; j++) {
                // Skip duplicate elements for the second element to avoid duplicate quadruplets.
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Use two pointers for the remaining two elements.
                int left = j + 1, right = length - 1;
                while (left < right) {
                    // Using long for total to prevent integer overflow
                    long total = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    // If the sum is less than the target, we need a larger sum, so move the left pointer.
                    if (total < target) {
                        left++;
                    }
                    // If the sum is greater than the target, we need a smaller sum, so move the right pointer.
                    else if (total > target) {
                        right--;
                    }
                    // If the sum is equal to the target, we found a quadruplet.
                    else {
                        res.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicate elements for the third element.
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        // Skip duplicate elements for the fourth element.
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // Move both pointers to find the next potential quadruplet.
                        left++;
                        right--;
                    }
                }

            }
        }
        return res;
    }
}