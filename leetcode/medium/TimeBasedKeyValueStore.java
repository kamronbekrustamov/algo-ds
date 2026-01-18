import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A time-based key-value store that supports storing multiple values for the same key
 * at different timestamps and retrieving the key's value at a certain timestamp.
 */
class TimeMap {

    // Using a static inner class or record reduces the memory overhead of the outer class reference
    private static record Entry(int timestamp, String value) {}

    private final Map<String, List<Entry>> store;

    public TimeMap() {
        this.store = new HashMap<>();
    }

    /**
     * Stores the key-value pair with the given timestamp.
     * Time Complexity: O(1) average
     */
    public void set(String key, String value, int timestamp) {
        // computeIfAbsent is more concise and slightly more performant than containsKey + get
        this.store.computeIfAbsent(key, k -> new ArrayList<>())
                  .add(new Entry(timestamp, value));
    }

    /**
     * Returns a value such that set was called previously, with timestamp_prev <= timestamp.
     * If there are multiple such values, it returns the one associated with the largest timestamp_prev.
     * If there are no values, it returns "".
     * * Time Complexity: O(log N) where N is the number of values for the specific key.
     */
    public String get(String key, int timestamp) {
        List<Entry> history = store.get(key);
        
        if (history == null || history.isEmpty()) {
            return "";
        }

        // Binary Search for the greatest timestamp <= target timestamp
        int left = 0;
        int right = history.size() - 1;
        String result = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Entry midEntry = history.get(mid);

            if (midEntry.timestamp() <= timestamp) {
                result = midEntry.value();
                left = mid + 1; // Try to find a larger timestamp that still satisfies the condition
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}