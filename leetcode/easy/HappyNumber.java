/**
 * Solution class for the Happy Number problem.
 */
class Solution {
    /**
     * Determines if a number is a "happy number".
     * A happy number is a number which eventually reaches 1 when replaced by the sum of the square of its digits.
     * If the process enters a cycle that does not include 1, the number is not happy.
     *
     * This solution uses Floyd's Cycle-Finding Algorithm (the "tortoise and the hare" algorithm)
     * to detect cycles in the sequence of numbers generated.
     *
     * @param n The number to check.
     * @return true if n is a happy number, false otherwise.
     */
    public boolean isHappy(int n) {
        // Initialize two pointers, a slow one and a fast one, for cycle detection.
        int slow = n;
        // The fast pointer moves two steps for every one step of the slow pointer.
        int fast = getNext(n);

        // The loop continues as long as the sequence hasn't reached 1 and the pointers haven't met.
        // If fast == 1, the number is happy.
        // If slow == fast, a cycle has been detected.
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);          // Slow pointer moves one step.
            fast = getNext(getNext(fast)); // Fast pointer moves two steps.
        }

        // If the loop ended, we check why. If fast is 1, we reached the happy state.
        // If the loop ended because slow == fast (and fast is not 1), it's an unhappy cycle.
        return fast == 1;
    }

    /**
     * Calculates the sum of the squares of the digits of a given number.
     * This generates the "next" number in the happy number sequence.
     *
     * @param num The input number.
     * @return The sum of the squares of its digits.
     */
    private int getNext(int num) {
        int totalSum = 0;
        while (num > 0) {
            int digit = num % 10;
            num = num / 10;
            totalSum += digit * digit;
        }
        return totalSum;
    }
}