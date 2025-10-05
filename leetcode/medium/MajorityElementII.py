from typing import List

class Solution:
    def majorityElement(self, nums: List[int]) -> List[List[int]]:
        """
        Finds all elements that appear more than ⌊n/3⌋ times in the array.

        This solution uses a modified version of the Boyer-Moore Majority Vote Algorithm.
        The key insight for elements appearing more than ⌊n/3⌋ times is that there
        can be at most two such elements.

        The algorithm proceeds in two passes:
        1. Candidate Selection Pass: Iterate through the array to find up to two
           potential majority elements (`candidate1`, `candidate2`) and their
           respective counts (`count1`, `count2`).
           - If the current number matches a candidate, increment its count.
           - If a count is 0, assign the current number as a new candidate and set its count to 1.
           - If neither of the above, decrement both counts. This step effectively
             "cancels out" non-majority elements.
        2. Verification Pass: After the first pass, `candidate1` and `candidate2`
           are the only possible elements that could appear more than ⌊n/3⌋ times.
           - Iterate through the array again to count the actual occurrences of
             `candidate1` and `candidate2`.
           - Add them to the result list if their actual count is greater than ⌊n/3⌋.
           - Ensure only unique candidates are added to the result.

        Time Complexity: O(n), as we iterate through the array twice.
        Space Complexity: O(1), as we only use a few variables to store candidates and counts.

        Args:
            nums: A list of integers.

        Returns:
            A list of integers that appear more than ⌊n/3⌋ times.
        """
        if not nums:
            return []

        # Initialize two candidates and their counts.
        # Using None as initial candidates is safer than float("inf").
        candidate1, candidate2 = None, None
        count1, count2 = 0, 0

        # --- First Pass: Candidate Selection ---
        for num in nums:
            if num == candidate1:
                count1 += 1
            elif num == candidate2:
                count2 += 1
            elif count1 == 0:
                candidate1 = num
                count1 = 1
            elif count2 == 0:
                candidate2 = num
                count2 = 1
            else:
                # If the current number is not one of the candidates and
                # both counts are non-zero, decrement both counts.
                # This effectively "cancels out" three distinct elements.
                count1 -= 1
                count2 -= 1

        # --- Second Pass: Verification ---
        # Reset counts for actual verification.
        count1, count2 = 0, 0
        for num in nums:
            if num == candidate1:
                count1 += 1
            # Only count for candidate2 if it's different from candidate1
            elif num == candidate2:
                count2 += 1
        
        result = []
        n_third = len(nums) // 3

        # Add candidate1 to result if it's a true majority element.
        if count1 > n_third:
            result.append(candidate1)
        
        # Add candidate2 to result if it's a true majority element AND it's different from candidate1.
        if candidate2 is not None and candidate2 != candidate1 and count2 > n_third:
            result.append(candidate2)
            
        return result