from typing import List

class Solution:
    def containsNearbyDuplicate(self, nums: List[int], k: int) -> bool:
        """
        Checks if there are two distinct indices i and j in the array such that
        nums[i] == nums[j] and abs(i - j) <= k.

        This is solved efficiently using a hash map in a single pass.
        """
        # The hash map will store the most recent index seen for each number.
        # Key: number from `nums`, Value: its most recent index.
        index_map = {}
        
        # Iterate through the array with both index and value.
        for i, num in enumerate(nums):
            # Check if we have seen this number before.
            if num in index_map:
                # If we have, check if the distance between the current index
                # and the last seen index is within the allowed range `k`.
                if i - index_map[num] <= k:
                    # If it is, we've found a valid pair.
                    return True
            
            # Whether we found a pair or not, update the map with the current
            # index for this number. This ensures we always check against the
            # most recent (and thus closest) occurrence.
            index_map[num] = i
            
        # If the loop completes without finding any such pair, return False.
        return False