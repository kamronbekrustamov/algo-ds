from typing import List


class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        """
        Counts the total number of continuous subarrays whose sum equals `k`.

        This solution uses the prefix sum technique combined with a hash map
        (dictionary in Python) to achieve optimal time complexity.

        The core idea is based on the property:
        `sum(nums[i...j]) = prefix_sum[j] - prefix_sum[i-1]`

        If `sum(nums[i...j]) == k`, then `prefix_sum[j] - prefix_sum[i-1] == k`.
        This can be rearranged to: `prefix_sum[i-1] = prefix_sum[j] - k`.

        Algorithm Breakdown:
        1. Initialize `total_subarrays` to 0 (to store the number of subarrays that sum to `k`).
        2. Initialize `current_prefix_sum` to 0 (to keep track of the prefix sum up to the current element).
        3. Initialize a hash map `prefix_frequencies` to store the frequency of each
           prefix sum encountered so far.
        4. Add an entry `prefix_frequencies[0] = 1`. This handles the case where
           the subarray itself starts from index 0 and sums up to `k`.
           (e.g., if `current_prefix_sum == k`, then `current_prefix_sum - k == 0`, and we need
           to count the one way to get a prefix sum of 0, which is an empty prefix).
        5. Iterate through each `num` in `nums`:
           a. Update `current_prefix_sum` by adding the current `num`.
           b. Calculate `needed_prefix_sum = current_prefix_sum - k`. This is the value
              of `prefix_sum[i-1]` that we are looking for.
           c. Add the frequency of `needed_prefix_sum` (if it exists in the map) to `total_subarrays`.
           d. Increment the frequency of the `current_prefix_sum` in the `prefix_frequencies` map.
        6. Return `total_subarrays`.

        Time Complexity: O(n), as we iterate through the array once. Dictionary
                         operations (get and put) take O(1) time on average.
        Space Complexity: O(n) in the worst case, where all prefix sums are unique
                          and stored in the hash map.

        Args:
            nums: A list of integers.
            k: The target sum.

        Returns:
            The total number of continuous subarrays whose sum equals `k`.
        """
        # 1. Initialize count for subarrays that sum to k.
        total_subarrays = 0
        
        # 2. Initialize current_prefix_sum to track prefix sum.
        current_prefix_sum = 0
        
        # 3. Hash map to store frequencies of prefix sums.
        # Key: prefix sum, Value: frequency of that prefix sum.
        prefix_frequencies = {}
        
        # 4. Base case: A prefix sum of 0 exists once (before any elements are processed).
        # This handles cases where a subarray starting from index 0 sums to k.
        prefix_frequencies[0] = 1
        
        # 5. Iterate through each number in the input array.
        for num in nums:
            # 5a. Update the current prefix sum.
            current_prefix_sum += num
            
            # 5b. Calculate the prefix sum we need to find in the map.
            # If `current_prefix_sum - needed_prefix_sum == k`, then `needed_prefix_sum = current_prefix_sum - k`.
            needed_prefix_sum = current_prefix_sum - k
            
            # 5c. If `needed_prefix_sum` exists in the map, it means we found
            # subarrays ending at the current index that sum to `k`.
            # Add its frequency to our total count.
            total_subarrays += prefix_frequencies.get(needed_prefix_sum, 0)
            
            # 5d. Increment the frequency of the `current_prefix_sum` in the map.
            prefix_frequencies[current_prefix_sum] = prefix_frequencies.get(current_prefix_sum, 0) + 1
            
        # 6. Return the total count of subarrays.
        return total_subarrays