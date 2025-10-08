import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    /**
     * Finds the maximized capital after finishing at most k distinct projects.
     *
     * This problem is solved using a greedy approach. At each step, we want to invest
     * in the most profitable project that we can currently afford. This suggests a max-heap.
     *
     * The overall strategy is:
     * 1. Sort all projects by their capital requirement. This allows us to efficiently
     *    find all affordable projects at any given time.
     * 2. Iterate up to k times (for each investment opportunity).
     * 3. In each iteration, add all newly affordable projects (based on current capital `w`)
     *    to a max-heap of profits.
     * 4. Greedily select the most profitable project from the heap, add its profit to our
     *    capital, and repeat.
     *
     * @param k       The maximum number of projects to invest in.
     * @param w       The initial capital.
     * @param profits An array of profits for each project.
     * @param capital An array of capital requirements for each project.
     * @return The final maximized capital.
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        
        // Step 1: Combine capital and profits into a single structure.
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        // Step 2: Sort the projects based on their capital requirement.
        // This allows us to iterate through them once in an efficient manner.
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));

        // Step 3: Use a max-heap to store the profits of all currently affordable projects.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        int projectIndex = 0; // Pointer for the sorted projects array.

        // Step 4: Loop up to k times to make investments.
        for (int i = 0; i < k; i++) {
            // Add all projects that we can now afford (capital <= w) to the max-heap.
            while (projectIndex < n && projects[projectIndex][0] <= w) {
                maxHeap.offer(projects[projectIndex][1]);
                projectIndex++;
            }

            // If the heap is empty, it means there are no affordable projects left to invest in.
            if (maxHeap.isEmpty()) {
                break;
            }

            // Greedily invest in the most profitable available project.
            w += maxHeap.poll();
        }

        return w;
    }
}