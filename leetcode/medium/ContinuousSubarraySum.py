from typing import List

class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        """
        Checks if a continuous subarray of size at least two exists whose
        elements sum up to a multiple of k.

        The solution uses the property of prefix sums and modulo arithmetic:
        If `sum(nums[j...i]) % k == 0`, then `PrefixSum[i] % k == PrefixSum[j-1] % k`.

        We must find two indices, `i` and `p = j-1`, that have the same
        prefix sum remainder, with the added constraint that the subarray
        length `i - p` is at least 2.

        This "size >= 2" constraint is enforced by "lagging" the insertion
        into the `seen_remainders` set by one step.

        Args:
            nums: The input list of integers.
            k: The divisor (guaranteed not to be zero).

        Returns:
            True if such a subarray exists, False otherwise.
        """
        
        # A subarray of size >= 2 requires at least 2 elements.
        if len(nums) < 2:
            return False

        # Stores remainders from prefix sums P[p] where p <= i-2.
        seen_remainders = set()
        
        # R[i-1]: Remainder of the prefix sum ending at the *previous* index.
        # We initialize it to 0, representing the "empty" prefix sum P[-1].
        prev_remainder = 0
        
        for num in nums:
            # R[i]: Remainder of the prefix sum ending at the *current* index.
            # R[i] = (P[i-1] + num) % k = (R[i-1] + num) % k
            curr_remainder = (prev_remainder + num) % k
            
            # Check if R[i] matches any R[p] where p <= i-2.
            # `seen_remainders` only contains remainders up to R[i-2].
            if curr_remainder in seen_remainders:
                return True
            
            # Add the *previous* remainder R[i-1] to the set.
            # This makes it available for checking starting from the *next*
            # iteration (at index i+1), maintaining the size >= 2 gap.
            seen_remainders.add(prev_remainder)
            
            # Update prev_remainder for the next loop.
            prev_remainder = curr_remainder
        
        # No matching remainders with a >= 2 gap were found.
        return False