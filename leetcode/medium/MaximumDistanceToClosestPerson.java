/**
 * Solution class for maximizing distance to the closest person.
 */
class Solution {

    /**
     * Calculates the maximum distance to the closest person in a row of seats.
     * * The problem handles three specific scenarios regarding empty seats:
     * 1. Left Edge: Empty seats from the start (index 0) to the first person.
     * 2. Middle: Empty seats between two people.
     * 3. Right Edge: Empty seats from the last person to the end of the array.
     *
     * @param seats An array representing seat occupancy (1 is occupied, 0 is empty).
     * @return The maximum closest distance to a person.
     */
    public int maxDistToClosest(int[] seats) {
        int lastPersonIndex = -1; // Tracks the index of the previously found person
        int maxDistance = 0;      // Tracks the global maximum distance found so far

        for (int i = 0; i < seats.length; i++) {
            // Check if the current seat is occupied
            if (seats[i] == 1) {
                if (lastPersonIndex == -1) {
                    // CASE 1: Leading Empty Seats
                    // If this is the first person we've seen, the distance is simply 
                    // the number of seats from the start (index 0) to here.
                    maxDistance = i;
                } else {
                    // CASE 2: Middle Empty Seats
                    // If we have seen a person before, the empty seats are between two people.
                    // The safest seat is exactly in the middle.
                    // Distance = (current_index - last_person_index) / 2
                    maxDistance = Math.max(maxDistance, (i - lastPersonIndex) / 2);
                }
                // Update the last seen person to the current index
                lastPersonIndex = i;
            }
        }

        // CASE 3: Trailing Empty Seats
        // After the loop, check the distance from the very last person found
        // to the physical end of the row.
        int trailingDistance = seats.length - 1 - lastPersonIndex;
        maxDistance = Math.max(maxDistance, trailingDistance);

        return maxDistance;
    }
}