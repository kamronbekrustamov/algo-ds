from typing import List

class Solution:
    """
    Determines if the target triplet can be formed by merging a subset of the
    given triplets.

    A merge operation takes two triplets (a, b, c) and (d, e, f) and produces
    (max(a, d), max(b, e), max(c, f)).

    The target can only be achieved if:
    1. Every triplet used in the merge has components less than or equal to the
       corresponding target components.
    2. Among the valid triplets, at least one achieves the target's first component,
       at least one achieves the second, and at least one achieves the third.
    """
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        """
        Checks if the target triplet can be formed by merging valid triplets.

        Args:
            triplets: A list of triplets, where each triplet is a list/tuple of three integers.
            target: The target triplet [T1, T2, T3] to achieve.

        Returns:
            True if the target can be formed, False otherwise.
        """
        # Unpack the target for easy comparison
        T1, T2, T3 = target
        
        # Boolean flags to track if each component of the target has been matched
        # by at least one valid triplet.
        T1_achieved, T2_achieved, T3_achieved = False, False, False

        for f, s, t in triplets:
            # First filter condition: A triplet is 'valid' for merging only if
            # all its components are less than or equal to the target's components.
            # If any component exceeds the target, this triplet can *never* be
            # used, as the merge operation only increases values.
            if f <= T1 and s <= T2 and t <= T3:
                # This triplet is valid. Now check if it helps us achieve the target.
                
                # Check if this triplet *matches* the target's first component.
                if f == T1:
                    T1_achieved = True
                
                # Check if this triplet *matches* the target's second component.
                if s == T2:
                    T2_achieved = True
                
                # Check if this triplet *matches* the target's third component.
                if t == T3:
                    T3_achieved = True
            
            # Optimization: If all three target components have been matched,
            # we can stop early and return True immediately.
            if T1_achieved and T2_achieved and T3_achieved:
                return True

        # After checking all triplets, return True only if all three target components
        # were successfully matched by at least one valid triplet.
        return T1_achieved and T2_achieved and T3_achieved