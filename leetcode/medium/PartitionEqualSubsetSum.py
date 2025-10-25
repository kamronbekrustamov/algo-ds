from typing import List

class Solution:
    """
    Determines if a given array of positive integers can be partitioned into two 
    subsets such that the sum of elements in both subsets is equal.
    
    This is equivalent to the Subset Sum Problem: finding a subset of 'nums' 
    that sums up to 'total_sum / 2'.
    """
    def canPartition(self, nums: List[int]) -> bool:
        # Edge case: An empty list can be considered trivially partitionable (two empty sets).
        # However, the problem typically assumes positive integers and focuses on 
        # non-trivial partitioning. For practical purposes, a list with less than 
        # two elements usually defaults to the logic below.
        # The original code's check is preserved but simplified.
        if not nums:
            return True
        
        total_sum = sum(nums)
        
        # Condition 1: If the total sum is odd, it's impossible to partition into 
        # two subsets with equal sums.
        if total_sum % 2 != 0:
            return False
        
        target = total_sum // 2
        
        # 1. Initialization for Dynamic Programming (0/1 Knapsack style)
        # dp[i] will be True if a subset of the numbers considered so far sums up to i.
        dp = [False] * (target + 1)
        dp[0] = True  # A sum of 0 is always achievable (by choosing no numbers).

        # Sorting the array is not strictly necessary for correctness or asymptotic 
        # complexity, but the original logic used it to potentially break early. 
        # We can keep it for that minor optimization.
        nums.sort()

        # 2. Iterate through each number
        for num in nums:
            # Optimization: If the current number is already greater than the target, 
            # it cannot contribute to forming the target sum, so we can stop.
            if num > target:
                break
            
            # 3. Update the DP array
            # We iterate backward from 'target' down to 'num'. This is crucial 
            # for the 0/1 knapsack implementation: it ensures each number is 
            # considered *at most once* for forming a sum.
            for current_sum in range(target, num - 1, -1):
                # dp[current_sum] is True if either:
                # a) It was already possible to achieve 'current_sum' without the current 'num' (dp[current_sum])
                # OR
                # b) It was possible to achieve 'current_sum - num' and we include the current 'num' (dp[current_sum - num])
                dp[current_sum] = dp[current_sum] or dp[current_sum - num]
                
        # 4. Result
        # The problem is solvable if we can achieve the 'target' sum.
        return dp[target]