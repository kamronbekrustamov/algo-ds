from typing import List
import collections


class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        """
        Finds the k most frequent elements in an array of integers.

        This solution uses a bucket sort-like approach to achieve O(N) time complexity,
        which is more efficient than a sorting-based O(N log N) solution.

        Algorithm:
        1.  Count the frequency of each number in the input array using a hash map
            (or `collections.Counter`). This takes O(N) time.
        2.  Create a list of "buckets", where the index of each bucket corresponds to a
            frequency. `buckets[i]` will store a list of all numbers that appear `i` times.
            The size of this list will be `len(nums) + 1`.
        3.  Iterate through the frequency map and place each number into its corresponding
            frequency bucket. This takes O(U) time, where U is the number of unique elements.
        4.  Iterate through the buckets in reverse order (from highest frequency to lowest).
            Collect the numbers from the buckets until `k` elements have been gathered.

        Time Complexity: O(N) - The frequency counting is O(N), populating the buckets is O(U),
        and gathering the result is O(U) in the worst case. Since U <= N, the overall
        complexity is dominated by the initial counting step, making it O(N).
        Space Complexity: O(N) - For the frequency map and the buckets array.
        """
        if k == 0:
            return []

        # Step 1: Count the frequency of each number. O(N)
        # collections.Counter is a highly efficient and Pythonic way to do this.
        freq_map = collections.Counter(nums)

        # Step 2: Create buckets to group numbers by frequency.
        # The index is the frequency, and the value is a list of numbers with that frequency.
        buckets = [[] for _ in range(len(nums) + 1)]
        for num, freq in freq_map.items():
            buckets[freq].append(num)

        # Step 3: Gather the top k elements by iterating through buckets from high to low frequency.
        result = []
        for i in range(len(buckets) - 1, 0, -1):
            result.extend(buckets[i])
            if len(result) >= k:
                # We only need the top k, so we can slice and return.
                return result[:k]
        return result # Should only be reached if k > number of unique elements
