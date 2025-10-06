from collections import defaultdict

class TimeMap:
    """
    A time-based key-value store that allows storing multiple values for the same key
    at different timestamps and retrieving the value for a key at a specific timestamp.
    """

    def __init__(self):
        """
        Initializes the TimeMap.
        A dictionary is used to store the key-value pairs, where each key maps to a
        list of (timestamp, value) tuples. The list is kept sorted by timestamp.
        """
        self.store = defaultdict(list)

    def set(self, key: str, value: str, timestamp: int) -> None:
        """
        Stores a value for a given key at a specific timestamp.

        Since the problem guarantees that timestamps are strictly increasing for each key,
        we can simply append the new (timestamp, value) tuple to the list for that key.

        Args:
            key: The key to associate the value with.
            value: The value to store.
            timestamp: The timestamp at which to store the value.

        Time complexity: O(1) on average.
        """
        self.store[key].append((timestamp, value))

    def get(self, key: str, timestamp: int) -> str:
        """
        Retrieves a value for a given key at a specific timestamp.

        It returns the value associated with the largest timestamp that is less than or
        equal to the given timestamp. If no such value exists, it returns an empty string.
        Binary search is used to find the correct value efficiently.

        Args:
            key: The key to retrieve the value for.
            timestamp: The timestamp to retrieve the value at.

        Returns:
            The value for the key at the given timestamp, or an empty string if not found.

        Time complexity: O(log n), where n is the number of values for the key.
        """
        if key not in self.store:
            return ""

        values = self.store[key]
        
        # Binary search to find the value with the largest timestamp <= given timestamp.
        low, high = 0, len(values) - 1
        result = ""

        while low <= high:
            mid = (low + high) // 2
            if values[mid][0] <= timestamp:
                # This is a potential candidate. Store it and look for a better one
                # (i.e., a larger timestamp) in the right half.
                result = values[mid][1]
                low = mid + 1
            else:
                # The timestamp at mid is too high. Look in the left half.
                high = mid - 1
                
        return result

# Your TimeMap object will be instantiated and called as such:
# obj = TimeMap()
# obj.set(key,value,timestamp)
# param_2 = obj.get(key,timestamp)