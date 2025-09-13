class Solution:
    def _sum_squares(self, num: int) -> int:
        """Helper function to calculate the sum of the squares of the digits."""
        total_sum = 0
        while num > 0:
            # divmod is a clean way to get both quotient and remainder
            num, digit = divmod(num, 10)
            total_sum += digit ** 2
        return total_sum

    def isHappy(self, n: int) -> bool:
        """
        Determines if a number is a "happy number".

        A happy number is a number which eventually reaches 1 when replaced by the
        sum of the square of its digits. If the process results in an endless
        cycle of numbers that does not include 1, the number is not happy.

        This solution uses Floyd's Cycle-Finding Algorithm (also known as the
        "Tortoise and Hare" algorithm) to detect cycles with O(1) space complexity,
        which is an optimization over using a hash set.

        Algorithm:
        1. Use two pointers, `slow` and `fast`.
        2. `slow` moves one step at a time through the sequence (n -> next_n).
        3. `fast` moves two steps at a time (n -> next_n -> next_next_n).
        4. If `n` is a happy number, the `fast` pointer will reach 1.
        5. If `n` is not a happy number, it will enter a cycle. In this case,
           the `fast` pointer will eventually "lap" the `slow` pointer, and
           they will become equal.

        Time Complexity: O(log n) - The number of steps is related to the number of digits.
        Space Complexity: O(1) - No extra storage is needed besides the pointers.
        """
        slow = n
        fast = self._sum_squares(n)

        # The loop terminates if a cycle is found (slow == fast)
        # or if the sequence reaches 1.
        while fast != 1 and slow != fast:
            slow = self._sum_squares(slow)
            fast = self._sum_squares(self._sum_squares(fast))

        # If the loop ended because fast reached 1, it's a happy number.
        return fast == 1