from typing import List


class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        """
        Finds the minimum integer eating speed `k` such that Koko can eat all
        bananas in `piles` within `h` hours.

        This problem exhibits a monotonic property: if Koko can eat all bananas
        at a speed `k`, she can also eat them at any speed `k' > k`. This allows
        us to use binary search on the possible range of eating speeds.

        Algorithm Breakdown:
        1. Define the search space for `k`:
           - The minimum possible speed is 1 (Koko must eat at least 1 banana per hour).
           - The maximum possible speed is `max(piles)` (Koko never needs to eat
             faster than the largest pile, as she can only eat one pile at a time).
        2. Perform binary search within this range (`low` to `high`).
        3. In each iteration, calculate `mid = (low + high) // 2`.
        4. Use a helper function `_can_finish(speed)` to determine if Koko can
           eat all bananas within `h` hours at the current `mid` speed.
           - This helper sums up `ceil(pile / speed)` for each pile.
        5. Adjust the search space based on the result of `_can_finish(mid)`:
           - If `_can_finish(mid)` is true, it means `mid` is a possible speed,
             and we might be able to find an even slower (optimal) speed. So,
             we try `high = mid`.
           - If `_can_finish(mid)` is false, it means `mid` is too slow, and
             we need a faster speed. So, we try `low = mid + 1`.
        6. The loop terminates when `low == high`, which will be the minimum
           eating speed `k`.

        Time Complexity: O(N log M), where N is the number of piles and M is the
                         maximum pile size. The `log M` comes from the binary
                         search, and `N` comes from iterating through piles in
                         the `_can_finish` helper.
        Space Complexity: O(1).

        Args:
            piles: A list of integers representing the number of bananas in each pile.
            h: The total number of hours Koko has to eat all bananas.

        Returns:
            The minimum integer eating speed `k`.
        """

        # Helper function to check if Koko can finish all bananas within `h` hours
        # at a given `speed`.
        def _can_finish(speed: int) -> bool:
            hours_needed = 0
            for pile in piles:
                # Calculate hours needed for this pile: ceil(pile / speed)
                # Using (pile + speed - 1) // speed for integer ceiling division.
                hours_needed += (pile + speed - 1) // speed
            return hours_needed <= h

        # 1. Define the search space for `k`.
        # Minimum possible speed is 1.
        low = 1
        # Maximum possible speed is the largest pile size.
        high = max(piles)

        # 2. Perform binary search.
        # The loop continues as long as there's a valid search space.
        while low < high:
            # 3. Calculate the middle speed.
            mid = low + (high - low) // 2  # Avoids potential overflow for very large low/high

            # 4. Check if Koko can finish at `mid` speed.
            if _can_finish(mid):
                # If she can finish, `mid` is a possible answer.
                # Try to find an even smaller speed in the left half (including mid).
                high = mid
            else:
                # If she cannot finish, `mid` is too slow.
                # We need a faster speed, so search in the right half (excluding mid).
                low = mid + 1
        
        # 6. When the loop terminates, `low` (or `high`) will be the minimum `k`.
        return low