/**
 * A class containing a method to find the minimum eating speed (Koko's Bananas problem).
 */
class Solution {
    
    /**
     * Finds the minimum integer eating speed 'K' such that Koko can finish
     * all the bananas within 'h' hours.
     *
     * The method uses **Binary Search** over the possible range of speeds:
     * [1, max(piles)], where max(piles) is the largest pile size.
     *
     * @param piles An array of integers where piles[i] is the number of bananas in the i-th pile.
     * @param h The maximum number of hours Koko has to eat all the bananas.
     * @return The minimum integer eating speed 'K'. Returns -1 if the constraints imply
     * no solution (though problem constraints usually ensure a solution exists).
     */
    public int minEatingSpeed(int[] piles, int h) {
        // Initial validation: While not strictly necessary if problem constraints guarantee a solution,
        // it's good practice. If the number of piles (N) is greater than the available hours (h),
        // it's impossible to finish, as Koko takes at least 1 hour per pile.
        // However, the binary search logic naturally handles the case where no speed works 
        // within the search space [1, max_pile] by returning the max_pile speed.
        if (piles == null || piles.length == 0) {
            return 0; // Or handle as an error case depending on expected input
        }

        // 1. Determine the search space boundaries
        
        // The minimum possible speed is 1.
        long low = 1; 
        
        // The maximum possible speed is the size of the largest pile. Koko never needs to eat 
        // faster than the largest pile size, as that would finish the largest pile in 1 hour, 
        // which is the fastest possible time for that pile.
        // Using 'long' for 'high' and related calculations prevents potential integer overflow, 
        // though typically 'int' is sufficient for speed unless pile sizes are very large.
        long high = 0; 
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        // 2. Binary Search
        
        // 'low' will store the minimum valid speed.
        int minSpeed = (int)high; 

        // The binary search continues until 'low' equals 'high'.
        while (low <= high) {
            // Calculate the midpoint speed 'k'
            long k = low + (high - low) / 2;
            
            // Check if this speed 'k' is feasible (i.e., time <= h)
            if (isFeasible(piles, h, k)) {
                // 'k' is a possible speed. We store it and try a *smaller* speed 
                // to see if an even better minimum exists.
                minSpeed = (int)k;
                high = k - 1; // Search the lower half
            } else {
                // 'k' is too slow (time > h). We need a *faster* speed.
                low = k + 1; // Search the upper half
            }
        }

        return minSpeed;
    }
    
    /**
     * Helper function to check if Koko can finish all piles within 'h' hours 
     * with a given eating speed 'k'.
     *
     * @param piles The array of banana piles.
     * @param h The maximum available hours.
     * @param k The current eating speed to test.
     * @return True if the total time required is less than or equal to 'h', false otherwise.
     */
    private boolean isFeasible(int[] piles, int h, long k) {
        // Check against problem constraint: A speed of 0 is not valid.
        if (k == 0) return false; 
        
        long hours = 0;
        for (int pile : piles) {
            // Calculate hours for the current pile: 
            // hours = ceil(pile / k).
            // Using integer division: (numerator + denominator - 1) / denominator
            hours += (pile + k - 1) / k; 

            // Optimization: Early exit if the time exceeds 'h'.
            if (hours > h) {
                return false;
            }
        }
        
        return hours <= h;
    }
}