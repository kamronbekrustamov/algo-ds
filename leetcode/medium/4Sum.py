from typing import List

class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        """
        Finds all unique quadruplets in the array which give the sum of target.

        This solution follows a generalized approach for k-Sum problems. It uses
        sorting and a two-pointer technique to achieve an O(n^3) time complexity.

        Algorithm Breakdown:
        1. Sort the input array. This is crucial for using the two-pointer
           technique and for efficiently skipping duplicate numbers.
        2. Iterate through the array with a primary pointer `i`. This will be the
           first number in our potential quadruplet.
        3. For each `i`, iterate with a second pointer `j` starting from `i + 1`.
           This will be the second number.
        4. For each pair `(i, j)`, we now need to solve a 2-Sum problem for the
           remaining part of the array. The new target will be `target - nums[i] - nums[j]`.
        5. Use two pointers, `left` and `right`, to scan the subarray from `j + 1`
           to the end. `left` starts at `j + 1` and `right` starts at the end of the array.
        6. Move the `left` and `right` pointers inwards based on their sum:
           - If `sum < target`, move `left` right to increase the sum.
           - If `sum > target`, move `right` left to decrease the sum.
           - If `sum == target`, we've found a quadruplet.
        7. To ensure uniqueness, skip duplicate numbers at every stage (for `i`,
           `j`, `left`, and `right`) after they have been processed.

        Time Complexity: O(n^3), due to the nested loops and the two-pointer scan.
        Space Complexity: O(1) if we don't count the space for the result list.
        """
        n = len(nums)
        if n < 4:
            return []

        # 1. Sort the array
        nums.sort()
        result = []

        # 2. Outer loop for the first number
        for i in range(n - 3):
            # Skip duplicates for the first number
            if i > 0 and nums[i] == nums[i - 1]:
                continue

            # --- Early exit optimizations ---
            # If the smallest possible sum from this point is > target, we can stop.
            if nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target:
                break
            # If the largest possible sum from this point is < target, we can skip to the next i.
            if nums[i] + nums[n-1] + nums[n-2] + nums[n-3] < target:
                continue

            # 3. Inner loop for the second number
            for j in range(i + 1, n - 2):
                # Skip duplicates for the second number
                if j > i + 1 and nums[j] == nums[j - 1]:
                    continue
                
                # --- Early exit optimizations for the inner loop ---
                if nums[i] + nums[j] + nums[j+1] + nums[j+2] > target:
                    break
                if nums[i] + nums[j] + nums[n-1] + nums[n-2] < target:
                    continue

                # 5. Two-pointer approach for the remaining two numbers
                left, right = j + 1, n - 1
                while left < right:
                    current_sum = nums[i] + nums[j] + nums[left] + nums[right]

                    if current_sum < target:
                        left += 1
                    elif current_sum > target:
                        right -= 1
                    else:
                        # Found a quadruplet
                        result.append([nums[i], nums[j], nums[left], nums[right]])
                        
                        # 7. Skip duplicates for the third and fourth numbers
                        # Move left pointer forward while the value is the same
                        left += 1
                        while left < right and nums[left] == nums[left - 1]:
                            left += 1
                        
                        # Move right pointer backward while the value is the same
                        right -= 1
                        while left < right and nums[right] == nums[right + 1]:
                            right -= 1
                            
        return result