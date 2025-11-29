import java.util.Arrays;

/**
 * Finds the minimum number of time intervals required to complete all tasks, 
 * given a cooldown period 'n' between two occurrences of the same task.
 * * The strategy is based on the task with the maximum frequency.
 * The total time is determined by either the length of the tasks array 
 * (if there is enough variety to fill all idle slots) or the time 
 * required by the pattern dictated by the most frequent task.
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // 1. Calculate the frequency of each task. Tasks are uppercase letters 'A'-'Z'.
        int[] taskFrequencies = new int[26];
        for (char task : tasks) {
            taskFrequencies[task - 'A']++;
        }

        // 2. Sort the frequencies to easily identify the max frequency.
        Arrays.sort(taskFrequencies);

        // The task with the highest frequency is at the end of the sorted array.
        int maxFrequency = taskFrequencies[25];

        // 3. Calculate the potential "idle" time based on the max frequency task.
        // Consider the pattern: (T_max, idle, idle, ..., T_max, idle, idle, ...)
        // There are (maxFrequency - 1) groups of tasks/idle slots.
        // Each group must be of size (n + 1) to satisfy the cooldown 'n'.
        // The space *between* the maxFrequency tasks is (maxFrequency - 1) * n.
        int potentialIdleSlots = (maxFrequency - 1) * n;

        // 4. Fill the idle slots with other tasks.
        // We iterate through the rest of the frequencies (indices 0 to 24)
        for (int i = 0; i < 25; i++) {
            // A non-max-frequency task 'T' can fill at most (maxFrequency - 1) slots.
            // (One less than the maxFrequency, because the last group doesn't need idle slots after it).
            // Subtract the number of slots filled by task T from the potential idle time.
            int slotsFilled = Math.min(taskFrequencies[i], maxFrequency - 1);
            potentialIdleSlots -= slotsFilled;
        }

        // 5. The total minimum time is the sum of tasks and the remaining idle slots.
        // The remaining idle slots must be at least 0.
        // If potentialIdleSlots is negative, it means all required idle slots were filled 
        // by other tasks (and possibly more), so the total time is just tasks.length.
        int requiredIdleTime = Math.max(0, potentialIdleSlots);

        // Total time = Total number of tasks + Total required idle time.
        return tasks.length + requiredIdleTime;
    }
}