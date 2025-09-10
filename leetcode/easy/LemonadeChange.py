from typing import List


class Solution:
    def lemonadeChange(self, bills: List[int]) -> bool:
        fives = 0
        tens = 0

        for bill in bills:
            if bill == 5:
                # Customer pays with $5, we just take it.
                fives += 1
            elif bill == 10:
                # Customer pays with $10, needs $5 change.
                if not fives:  # Check if we have any $5 bills.
                    return False
                fives -= 1
                tens += 1
            elif bill == 20:
                # Customer pays with $20, needs $15 change.
                # Greedy approach: Prioritize using a $10 and a $5,
                # as $5 bills are more versatile for future transactions.
                if tens > 0 and fives > 0:
                    tens -= 1
                    fives -= 1
                # If we can't use a $10, try using three $5 bills.
                elif fives >= 3:
                    fives -= 3
                # If neither option is possible, we can't make change.
                else:
                    return False

        # If we successfully process all customers, return True.
        return True