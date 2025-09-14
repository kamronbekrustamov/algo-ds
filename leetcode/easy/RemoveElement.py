from typing import List


class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        """
        Removes all instances of a given value in-place and returns the new length.

        This solution uses a two-pointer approach (a slow and a fast pointer) to
        achieve this in a single pass with O(1) extra space.

        Algorithm:
        1. Initialize a `slow` pointer (`k`) to 0. This pointer will track the position
           where the next element that is NOT equal to `val` should be placed.
        2. Iterate through the array with a `fast` pointer (the `num` in the loop).
        3. If the current element (`num`) is NOT equal to `val`, it means this
           element should be kept.
        4. We copy the `num` to the `slow` pointer's position (`nums[k]`) and then
           increment the `slow` pointer.
        5. The final value of `k` is the new length of the modified array.

        Time Complexity: O(n), as we iterate through the array once.
        Space Complexity: O(1), as we modify the array in-place.
        """
        # `k` is the slow pointer. It tracks the index for the next valid element.
        k = 0
        for num in nums:
            if num != val:
                nums[k] = num
                k += 1
        return k
