class Solution {
    /**
     * Calculates the Nth Tribonacci number.
     *
     * The Tribonacci sequence Tn is defined as follows:
     * T0 = 0, T1 = 1, T2 = 1,
     * and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
     *
     * This solution uses an iterative approach with constant space complexity.
     * It keeps track of the last three Tribonacci numbers to calculate the next one.
     *
     * @param n The index of the Tribonacci number to calculate.
     * @return The Nth Tribonacci number.
     *
     * Time Complexity: O(n), as we iterate from 3 up to n.
     * Space Complexity: O(1), as we only use a few variables to store previous values.
     */
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int t0 = 0;
        int t1 = 1;
        int t2 = 1;

        for (int i = 3; i <= n; i++) {
            int nextT = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = nextT;
        }

        return t2;
    }
}
