from typing import Optional


class _Node:
    """A node in a singly linked list, used for chaining in the HashMap.
    
    Each node stores a key-value pair and a pointer to the next node in the
    same bucket, handling hash collisions.
    """
    def __init__(self, key: int, value: int, next_node: Optional['_Node'] = None):
        self.key = key
        self.value = value
        self.next = next_node


class MyHashMap:
    """A custom implementation of a HashMap using separate chaining for collision resolution.

    This HashMap supports 'put', 'get', and 'remove' operations. It also dynamically
    resizes itself by doubling or halving its capacity based on a load factor to
    maintain average O(1) time complexity for its operations.
    """

    def __init__(self):
        """Initializes the HashMap with a default capacity and empty buckets."""
        self._INITIAL_CAPACITY = 16
        self._LOAD_FACTOR_THRESHOLD_UP = 0.75
        self._LOAD_FACTOR_THRESHOLD_DOWN = 0.25
        
        self.capacity = self._INITIAL_CAPACITY
        self.size = 0
        # Each bucket will be the head of a linked list of _Node objects.
        self.buckets = [None] * self.capacity

    def _hash(self, key: int) -> int:
        """Computes the bucket index for a given key using a simple modulo operation."""
        return key % self.capacity

    def put(self, key: int, value: int) -> None:
        """Inserts a key-value pair. Updates the value if the key already exists.
        
        It traverses the linked list at the corresponding bucket. If the key is found,
        it updates the value. Otherwise, it adds a new node to the head of the list.
        After insertion, it checks if a resize is needed.
        """
        index = self._hash(key)
        curr = self.buckets[index]

        # Traverse the list to find if the key already exists.
        while curr:
            if curr.key == key:
                curr.value = value  # Key found, update value and return.
                return
            curr = curr.next

        # Key not found, add a new node to the beginning of the bucket's list.
        new_node = _Node(key, value, self.buckets[index])
        self.buckets[index] = new_node
        self.size += 1

        # Check if resizing (expanding) is needed.
        if (self.size / self.capacity) > self._LOAD_FACTOR_THRESHOLD_UP:
            self._resize(self.capacity * 2)

    def get(self, key: int) -> int:
        """Retrieves the value for a key.
        
        Returns:
            The value associated with the key, or -1 if the key is not found.
        """
        index = self._hash(key)
        curr = self.buckets[index]

        # Traverse the list to find the key.
        while curr:
            if curr.key == key:
                return curr.value  # Key found, return its value.
            curr = curr.next
        
        return -1  # Key not found.

    def remove(self, key: int) -> None:
        """Removes the key-value pair for the given key if it exists."""
        index = self._hash(key)
        
        # Use a dummy node to simplify removal, especially for the head of the list.
        dummy = _Node(-1, -1, self.buckets[index])
        prev = dummy
        curr = self.buckets[index]

        # Traverse the list to find the node to remove.
        while curr:
            if curr.key == key:
                # Key found, bypass the current node to remove it.
                prev.next = curr.next
                self.buckets[index] = dummy.next  # Update the bucket head.
                self.size -= 1

                # Check if resizing (shrinking) is needed.
                if self.capacity > self._INITIAL_CAPACITY and (self.size / self.capacity) < self._LOAD_FACTOR_THRESHOLD_DOWN:
                    self._resize(self.capacity // 2)
                return
            
            prev = curr
            curr = curr.next

    def _resize(self, new_capacity: int) -> None:
        """Resizes the hash table and re-hashes all existing entries."""
        old_buckets = self.buckets

        # Reset properties for the new table structure.
        self.capacity = new_capacity
        self.buckets = [None] * self.capacity
        self.size = 0

        # Re-hash all entries from the old buckets into the new buckets.
        for bucket_head in old_buckets:
            curr = bucket_head
            while curr:
                self.put(curr.key, curr.value)
                curr = curr.next