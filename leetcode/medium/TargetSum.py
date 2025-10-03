from typing import List

class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        """
        Finds the number of ways to assign symbols (+ or -) to make the sum of
        the numbers equal to the target.

        This solution converts the problem into a subset sum problem, which can
        be solved efficiently with dynamic programming.

        Mathematical Transformation:
        Let P be the subset of `nums` with a '+' sign, and N be the subset with a '-' sign.
        We are looking for:
            sum(P) - sum(N) = target

        We also know that:
            sum(P) + sum(N) = total_sum (where total_sum is the sum of all numbers in `nums`)

        Adding the two equations gives:
            2 * sum(P) = target + total_sum
            sum(P) = (target + total_sum) / 2

        So, the problem is transformed into: "Find the number of subsets of `nums`
        that sum up to a new target `(target + total_sum) / 2`".

        DP Approach (Subset Sum):
        - Let `dp[j]` be the number of ways to form the sum `j`.
        - We initialize `dp[0] = 1` (there is one way to make a sum of 0: by choosing an empty subset).
        - We iterate through each number `num` in `nums`. For each `num`, we update
          the `dp` array. For a sum `j`, the number of ways to form it is increased
          by the number of ways we could form `j - num` before considering the current `num`.
        - The final answer is `dp[new_target]`.

        Time Complexity: O(n * s), where n is the number of items and s is the `new_target` sum.
        Space Complexity: O(s) for the dp array.
        """
        total_sum = sum(nums)
        
        # If `target + total_sum` is odd, it's impossible to find an integer `sum(P)`.
        # Also, if the required sum `sum(P)` is negative (since `abs(target) > total_sum`),
        # it's impossible as we are working with non-negative numbers.
        if (target + total_sum) % 2 != 0 or abs(target) > total_sum:
            return 0
            
        new_target = (target + total_sum) // 2
        
        # dp[j] will store the number of ways to get sum j.
        dp = [0] * (new_target + 1)
        # Base case: There is one way to get a sum of 0 (by choosing no numbers).
        dp[0] = 1
        
        # For each number, update the dp array.
        for num in nums:
            # Iterate backwards to prevent using the same number multiple times in one subset.
            for j in range(new_target, num - 1, -1):
                dp[j] += dp[j - num]
                
        return dp[new_target]


# --- Original Recursive (Backtracking) Solution ---
#
# class Solution:
#     def findTargetSumWays(self, nums: List[int], target: int) -> int:
#         """
#         This is a brute-force backtracking solution. It explores every possible
#         combination of `+` and `-` for each number by modifying the target.
#
#         For each number, it makes two recursive calls, exploring what the
#         remaining target needs to be.
#         1. One where we subtract the number from the target (equivalent to using a `+` sign).
#         2. One where we add the number to the target (equivalent to using a `-` sign).
#
#         The base case is when all numbers have been processed (index == len(nums)).
#         If the remaining target is 0 at that point, we have found one valid way.
#
#         Time Complexity: O(2^n), where n is the number of elements. This is
#                          very slow and will likely result in a "Time Limit Exceeded"
#                          error for larger inputs.
#         Space Complexity: O(n) for the recursion stack depth.
#         """
#         def backtrack(index, target):
#             # Base case: if we've processed all numbers
#             if index == len(nums):
#                 # If the remaining target is 0, we found a valid combination.
#                 return 1 if target == 0 else 0
#
#             # Recursive step:
#             # Path 1: Corresponds to using a `+` sign for nums[index]
#             path1 = backtrack(index + 1, target - nums[index])
#             # Path 2: Corresponds to using a `-` sign for nums[index]
#             path2 = backtrack(index + 1, target + nums[index])
#
#             return path1 + path2
#
#         return backtrack(0, target)
