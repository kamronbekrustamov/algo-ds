class Solution {
    /**
     * Determines the starting gas station index from which a car can complete a
     * full circular trip.
     *
     * The car has to travel from station 'i' to 'i+1'. The gas needed is cost[i].
     * If the total gas available is less than the total cost, no solution exists.
     * If a solution exists, it is guaranteed to be unique.
     *
     * @param gas An array where gas[i] is the amount of gas available at station i.
     * @param cost An array where cost[i] is the amount of gas needed to travel
     * from station i to station i+1 (or station 0 from the last station).
     * @return The starting index if a full circuit can be completed, or -1 otherwise.
     *
     * Time Complexity: O(n) - Single pass through the arrays.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Essential check: An empty or null array means no circuit is possible.
        if (gas == null || gas.length == 0) {
            return -1;
        }

        // 1. Calculate the net change in fuel at each station: gas[i] - cost[i].
        //    Also, track the total gas and total cost to check for feasibility.
        int totalGas = 0;
        int totalCost = 0;
        int currentTank = 0; // Tracks the current net fuel balance in the tank for the *potential* circuit
        int startStation = 0; // The candidate starting station

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];

            // The net change in fuel after stopping at station 'i' and traveling to the next
            int netChange = gas[i] - cost[i];
            currentTank += netChange;

            // If the current tank balance drops below zero, it means the car cannot
            // reach station (i+1) starting from 'startStation'.
            // The new candidate must be the *next* station, (i+1), because any station
            // before or at 'i' cannot be the start of a valid circuit (as we've just proved).
            if (currentTank < 0) {
                // Reset the tank balance to 0, as we are effectively starting a new trip
                // from the next station.
                currentTank = 0;
                // The new potential starting point is the station immediately after the failure point
                startStation = i + 1;
            }
        }

        // 2. Feasibility check: If totalGas < totalCost, the trip is impossible.
        //    If totalGas >= totalCost, a solution is guaranteed to exist (unique).
        //    The unique solution will be the 'startStation' found in the single pass.
        return (totalGas < totalCost) ? -1 : startStation;
    }
}