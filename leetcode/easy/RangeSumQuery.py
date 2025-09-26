from typing import List

# Time: O(n) for __init__, O(1) for sumRange
# Space: O(n) for the prefix sum array
class NumArray:
    """
    This class allows for fast calculation of the sum of elements in a given range of an array.
    It pre-computes a prefix sum array, which allows for constant-time range sum queries.
    """

    def __init__(self, nums: List[int]):
        """
        Initializes the data structure by creating a prefix sum array.
        The prefix sum array `self.prefix` stores the cumulative sum of the elements of `nums`.
        `self.prefix[i]` will store the sum of the first `i` elements of `nums`.

        :param nums: A list of integers.
        """
        # Initialize a prefix sum array with zeros. It has one extra element
        # to simplify the sumRange calculation.
        self.prefix = [0] * (len(nums) + 1)
        
        # Iterate through the input numbers to build the prefix sum array.
        for i in range(len(nums)):
            # self.prefix[i+1] stores the sum of elements from nums[0] to nums[i].
            self.prefix[i + 1] = self.prefix[i] + nums[i]


    def sumRange(self, left: int, right: int) -> int:
        """
        Calculates the sum of the elements from index `left` to `right` (inclusive).
        This is done by subtracting the cumulative sum up to `left` from the cumulative sum up to `right + 1`.
        For example, sum(left, right) = prefix[right + 1] - prefix[left].

        :param left: The starting index of the range.
        :param right: The ending index of the range.
        :return: The sum of the elements in the specified range.
        """
        # O(1) operation
        return self.prefix[right + 1] - self.prefix[left]


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# param_1 = obj.sumRange(left,right)