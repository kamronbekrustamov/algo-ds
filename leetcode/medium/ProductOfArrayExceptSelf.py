from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        """
        Given an integer array nums, return an array answer such that answer[i] is
        equal to the product of all the elements of nums except nums[i].

        This solution uses a two-pass approach to achieve O(N) time and O(1) extra space
        (the output array does not count as extra space).
        """
        n = len(nums)
        # Initialize the answer array. answer[i] will store the final product.
        answer = [1] * n

        # --- Pass 1: Calculate left products ---
        # `left_product` stores the product of all elements to the left of the current index.
        # For each index `i`, `answer[i]` will first store the product of elements to its left.
        left_product = 1
        for i in range(n):
            answer[i] = left_product
            left_product *= nums[i]

        # --- Pass 2: Calculate right products and combine ---
        # `right_product` stores the product of all elements to the right of the current index.
        # We multiply the existing left product in `answer[i]` with the right product.
        right_product = 1
        for i in range(n - 1, -1, -1):
            answer[i] *= right_product
            right_product *= nums[i]

        return answer