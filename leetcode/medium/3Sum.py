from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        """
        Finds all unique triplets in the array which give the sum of zero.

        The solution uses a two-pointer approach after sorting the array, which is an
        efficient way to solve this problem with a time complexity of O(n^2).

        Algorithm:
        1.  Sort the input array `nums`. Sorting allows us to easily handle duplicates
            and use the two-pointer technique.
        2.  Iterate through the array with an index `i` from the start up to `len(nums) - 2`.
            The element `nums[i]` will be the first element of a potential triplet.
        3.  To avoid duplicate triplets, we skip any element `nums[i]` that is the same
            as the one before it.
        4.  For each `nums[i]`, initialize two pointers: `left = i + 1` and
            `right = len(nums) - 1`. These pointers will search for the other two
            elements of the triplet in the rest of the sorted array.
        5.  In a loop, calculate the sum `nums[i] + nums[left] + nums[right]`:
            - If the sum is less than 0, we need a larger sum, so we move the `left`
              pointer to the right (`left += 1`).
            - If the sum is greater than 0, we need a smaller sum, so we move the `right`
              pointer to the left (`right -= 1`).
            - If the sum is exactly 0, we have found a valid triplet. We add it to our
              result list.
        6.  After finding a valid triplet, we must move the pointers and skip any
            duplicate elements to ensure uniqueness. We increment `left` and decrement
            `right` while they point to the same values as before.

        Time Complexity: O(n^2). The O(n log n) for sorting is dominated by the O(n^2)
        of the nested loops (the outer `for` loop and the inner `while` loop).
        Space Complexity: O(1) or O(n), depending on the space complexity of the sorting
        algorithm used. The space for the result list is not counted.
        """
        # Sort the array to enable the two-pointer approach and handle duplicates easily.
        nums.sort()
        result = []
        # Iterate through the array. `i` will be the first element of the triplet.
        # We only need to go up to len(nums) - 2 to leave space for the two pointers.
        for i in range(len(nums) - 2):
            # *** Optimization: Skip duplicate first elements ***
            # If the current element is the same as the previous one, we've already
            # processed all possible triplets starting with that value, so we skip it.
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            # Initialize two pointers for the rest of the array.
            left, right = i + 1, len(nums) - 1
            while left < right:
                total = nums[i] + nums[left] + nums[right]
                # If the sum is too small, move the left pointer to a larger value.
                if total < 0:
                    left += 1
                # If the sum is too large, move the right pointer to a smaller value.
                elif total > 0:
                    right -= 1
                # If the sum is zero, we found a triplet.
                else:
                    result.append([nums[i], nums[left], nums[right]])
                    # *** Optimization: Skip duplicate second and third elements ***
                    # Move the left pointer forward as long as it's pointing to a duplicate.
                    while left < right and nums[left] == nums[left + 1]:
                        left += 1
                    # Move the right pointer backward as long as it's pointing to a duplicate.
                    while left < right and nums[right] == nums[right - 1]:
                        right -= 1
                    # Move pointers to the next unique elements.
                    left += 1
                    right -= 1
        return result
        