/**
 * Solution class for the Lemonade Change problem.
 */
class Solution {
    /**
     * Determines if correct change can be provided to every customer.
     * Customers line up to buy $5 lemonade and pay with $5, $10, or $20 bills.
     *
     * @param bills An array of integers representing the bills customers pay with.
     * @return true if it's possible to provide change for every customer, false otherwise.
     */
    public boolean lemonadeChange(int[] bills) {
        int fives = 0; // Counter for available $5 bills
        int tens = 0;  // Counter for available $10 bills

        for (int bill : bills) {
            if (bill == 5) {
                // Customer pays with $5, we just take it.
                fives++;
            } else if (bill == 10) {
                // Customer pays with $10, needs $5 change.
                if (fives == 0) { // Check if we have any $5 bills.
                    return false;
                }
                fives--;
                tens++;
            } else { // bill == 20
                // Customer pays with $20, needs $15 change.
                // We apply a greedy approach:
                // It's always better to use a $10 bill for change if possible,
                // because $5 bills are more versatile for future customers.

                // Option 1: Use one $10 bill and one $5 bill.
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } 
                // Option 2: If Option 1 is not possible, use three $5 bills.
                else if (fives >= 3) {
                    fives -= 3;
                } 
                // If neither option is possible, we can't make change.
                else {
                    return false;
                }
            }
        }

        // If we successfully process all customers, return true.
        return true;
    }
}
