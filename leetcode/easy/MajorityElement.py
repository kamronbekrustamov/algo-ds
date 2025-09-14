from typing import List


class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        """
        Finds the majority element in an array.
        The majority element is the element that appears more than ⌊n / 2⌋ times.
        This problem assumes that the majority element always exists in the array.

        This solution uses the Boyer-Moore Voting Algorithm, which is a highly
        efficient O(n) time and O(1) space algorithm for this problem.

        Algorithm Intuition:
        The algorithm works by maintaining a `candidate` for the majority element and a `count`.
        As we iterate through the array:
        - If the count is zero, we pick the current element as the new candidate.
        - If the current element is the same as the candidate, we increment the count.
        - If it's different, we decrement the count.

        The key idea is that if an element truly is the majority, its "votes" (increments)
        will outnumber the "votes" against it (decrements from other elements), ensuring
        it remains the candidate at the end.
        """
        candidate = None
        count = 0

        for num in nums:
            # If count is 0, we choose the current element as the new candidate.
            if count == 0:
                candidate = num

            # Increment count if the element matches the candidate, otherwise decrement.
            count += 1 if num == candidate else -1

        # Since the problem guarantees a majority element exists, the final
        # candidate will be the majority element.
        return candidate