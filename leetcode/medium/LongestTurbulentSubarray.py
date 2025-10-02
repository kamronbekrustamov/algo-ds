from typing import List

class Solution:
    def maxTurbulenceSize(self, arr: List[int]) -> int:
        """
        Finds the length of the longest turbulent subarray.

        A turbulent subarray is one where the comparison signs between adjacent
        elements alternate (e.g., <, >, <, > or >, <, >, <).

        This solution uses a single-pass sliding window approach. We iterate
        through the array while keeping track of the length of the current
        turbulent subarray.

        Algorithm Breakdown:
        1. Initialize `max_len` to 1 (for a single element) and `current_len` to 1.
        2. We also track the sign of the comparison of the previous pair of
           elements (`prev_sign`).
        3. Iterate from the second element of the array (`i = 1`).
        4. For each element `arr[i]`, compare it with the previous one `arr[i-1]`
           to determine the `current_sign` (e.g., -1 for '< ', 1 for '>', 0 for '==').
        5. Check for turbulence:
           - If `current_sign` is the opposite of `prev_sign`, the turbulence
             continues. We increment `current_len`.
           - If the elements are equal (`current_sign == 0`), the turbulence
             is broken. Reset `current_len` to 1.
           - If the `current_sign` is the same as `prev_sign`, the turbulence
             is broken, but a new turbulent subarray of length 2 starts.
             Reset `current_len` to 2.
        6. Update `max_len` with the `current_len` at each step.
        7. Update `prev_sign` for the next iteration.

        Time Complexity: O(n), as we iterate through the array once.
        Space Complexity: O(1).

        Args:
            arr: A list of integers.

        Returns:
            The length of the longest turbulent subarray.
        """
        if not arr:
            return 0
        
        n = len(arr)
        if n < 2:
            return n

        # `max_len` tracks the longest turbulent subarray found so far.
        # `current_len` tracks the length of the current turbulent subarray.
        # A single element is a turbulent subarray of length 1.
        max_len = 1
        current_len = 1
        
        # `prev_sign` stores the sign of the comparison between arr[i-1] and arr[i-2].
        # Initialized to 0, as there is no previous sign before the loop starts.
        prev_sign = 0

        for i in range(1, n):
            # Determine the sign of the comparison between the current and previous elements.
            current_sign = 0
            if arr[i - 1] < arr[i]:
                current_sign = 1  # Represents '>'
            elif arr[i - 1] > arr[i]:
                current_sign = -1 # Represents '<'

            # Case 1: The signs alternate, turbulence continues.
            if current_sign != 0 and current_sign == -prev_sign:
                current_len += 1
            # Case 2: The elements are equal, breaking the turbulence.
            # The new subarray starts from the next element, so current length is 1.
            elif current_sign == 0:
                current_len = 1
            # Case 3: The signs are the same, or we are starting a new pattern.
            # A new turbulent subarray of length 2 is formed by the current pair.
            else:
                current_len = 2
            
            # Update the maximum length found so far.
            max_len = max(max_len, current_len)
            
            # Update the previous sign for the next iteration.
            prev_sign = current_sign
            
        return max_len