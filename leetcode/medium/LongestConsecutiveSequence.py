from typing import List


class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        """
        Finds the length of the longest consecutive elements sequence in an unsorted array.

        This solution achieves O(n) time complexity by using a set for efficient lookups.
        The core idea is to only start counting a sequence from its smallest element.

        Algorithm:
        1. Create a set from the input list for O(1) average time complexity checks.
        2. Initialize `longest_streak` to 0.
        3. Iterate through each number in the set.
        4. For each number, check if it is the start of a sequence by verifying that
           `num - 1` is NOT in the set. This is the key optimization, as it ensures
           we only build a sequence once, from its starting point.
        5. If it is a start of a sequence, use a while loop to find its length by
           incrementing the number and checking for its existence in the set.
        6. Update `longest_streak` with the maximum length found.

        Time Complexity: O(n) - Although there is a nested loop, the inner `while` loop
        only runs for numbers that are the start of a sequence. Each number is visited
        at most twice (once in the outer loop, once in the inner loop), leading to
        linear time complexity.
        Space Complexity: O(n) - To store the numbers in a set.
        """
        if not nums:
            return 0

        num_set = set(nums)
        longest_streak = 0

        for num in num_set:
            # Check if the current number is the start of a sequence.
            # If `num - 1` exists, `num` is not the start, and we can skip it.
            # This check is the key to achieving O(n) time complexity.
            if num - 1 not in num_set:
                current_num = num
                current_streak = 1

                # If it is the start, count the length of the sequence.
                while current_num + 1 in num_set:
                    current_num += 1
                    current_streak += 1

                # Update the longest streak found so far.
                longest_streak = max(longest_streak, current_streak)

        return longest_streak