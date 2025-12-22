class Solution {
    /**
     * Determines if a subset of 'triplets' can be merged (component-wise maximum) 
     * to form the 'target' triplet.
     * * The core logic is:
     * 1. A triplet A=(a1, a2, a3) can only be used if all its components are 
     * less than or equal to the corresponding target components (a1 <= t1, a2 <= t2, a3 <= t3).
     * If any component of A is greater than the target (e.g., a1 > t1), using A 
     * will make the merged result's first component > t1, making the target unachievable.
     * 2. To achieve the target T=(t1, t2, t3), we must find at least one **valid** * triplet that achieves t1 (i.e., [t1, a2, a3] where a2<=t2, a3<=t3), 
     * at least one valid triplet that achieves t2, and at least one valid 
     * triplet that achieves t3.
     * * @param triplets An array of [a, b, c] triplets.
     * @param target The target [t1, t2, t3] triplet.
     * @return true if a subset of triplets can be merged to form the target, false otherwise.
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // Flags to track whether we have found a valid triplet that achieves each component
        boolean target1Achieved = false;
        boolean target2Achieved = false;
        boolean target3Achieved = false;

        final int t1 = target[0];
        final int t2 = target[1];
        final int t3 = target[2];

        for (int[] currentTriplet : triplets) {
            final int c1 = currentTriplet[0];
            final int c2 = currentTriplet[1];
            final int c3 = currentTriplet[2];

            // 1. Check for the Veto Condition: 
            // The triplet is only useful if all its components are <= the target's.
            if (c1 <= t1 && c2 <= t2 && c3 <= t3) {
                
                // 2. Track Achievement:
                // If a valid triplet matches a target component, set the corresponding flag.
                if (c1 == t1) {
                    target1Achieved = true;
                }

                if (c2 == t2) {
                    target2Achieved = true;
                }

                if (c3 == t3) {
                    target3Achieved = true;
                }

                // Optimization: If all three components are achieved, we can stop early.
                if (target1Achieved && target2Achieved && target3Achieved) {
                    return true;
                }
            }
        }
        
        // After checking all triplets, return true only if all components were achieved
        return target1Achieved && target2Achieved && target3Achieved;
    }
}