import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    /**
     * Determines the order in which a single-threaded CPU will process a given set of tasks.
     *
     * The CPU processes tasks based on the following rules:
     * 1. If the CPU is idle and no tasks are in the queue, it waits.
     * 2. When tasks are available, it chooses the one with the shortest processing time.
     * 3. If there's a tie in processing time, it chooses the one with the smaller original index.
     *
     * This implementation simulates this process efficiently using sorting and a min-heap.
     *
     * @param tasks A 2D array where tasks[i] = [enqueueTime, processingTime].
     * @return An array representing the order of tasks processed by the CPU.
     */
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        // Step 1: Augment tasks with their original indices.
        // We need to preserve the original index of each task before sorting.
        int[][] indexedTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0]; // enqueueTime
            indexedTasks[i][1] = tasks[i][1]; // processingTime
            indexedTasks[i][2] = i;             // originalIndex
        }

        // Step 2: Sort the tasks based on their enqueue time.
        Arrays.sort(indexedTasks, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 3: Use a min-heap to manage the queue of available tasks.
        // The heap prioritizes tasks by the shortest processing time, then by the smallest index.
        // The heap stores pairs of [processingTime, originalIndex].
        PriorityQueue<int[]> availableTasksQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]); // Primary sort: processingTime
            } else {
                return Integer.compare(a[1], b[1]); // Tie-breaker: originalIndex
            }
        });

        int[] resultOrder = new int[n];
        int resultIndex = 0; // Pointer for the result array
        int taskIndex = 0;   // Pointer for the sorted indexedTasks array
        long currentTime = 0;  // Use long to prevent overflow from large times

        // Main simulation loop: continues as long as there are tasks to enqueue or process.
        while (taskIndex < n || !availableTasksQueue.isEmpty()) {
            // If the queue is empty and the CPU is idle, jump time forward.
            // The CPU will be idle until the next task's enqueue time.
            if (availableTasksQueue.isEmpty()) {
                currentTime = Math.max(currentTime, indexedTasks[taskIndex][0]);
            }

            // Add all tasks that have become available by the current time to the min-heap.
            while (taskIndex < n && indexedTasks[taskIndex][0] <= currentTime) {
                availableTasksQueue.offer(new int[]{indexedTasks[taskIndex][1], indexedTasks[taskIndex][2]});
                taskIndex++;
            }

            // If there are tasks in the queue, process the one with the highest priority.
            if (!availableTasksQueue.isEmpty()) {
                int[] nextTask = availableTasksQueue.poll();
                int processingTime = nextTask[0];
                int originalIndex = nextTask[1];

                // Add the task's original index to the result and advance time.
                resultOrder[resultIndex++] = originalIndex;
                currentTime += processingTime;
            }
        }

        return resultOrder;
    }
}