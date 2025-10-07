from typing import Optional


class Node:
    """
    A node in a singly linked list. Used for chaining in the hash set.
    """
    def __init__(self, key: int, next_node: Optional['Node'] = None) -> None:
        self.key = key
        self.next = next_node


class MyHashSet:
    """
    A custom implementation of a HashSet using a hash table with separate chaining for collision resolution.
    The hash table dynamically resizes itself based on a load factor to maintain performance.
    """

    def __init__(self):
        """
        Initializes the HashSet.
        """
        # The ratio of size to capacity above which the hash table should grow.
        self.UP_LOAD_FACTOR = 0.75
        # The ratio of size to capacity below which the hash table should shrink.
        self.DOWN_LOAD_FACTOR = 0.25
        # The initial number of buckets in the hash table.
        self.capacity = 32
        # The number of unique keys currently stored in the hash set.
        self.size = 0
        # The list of buckets, where each bucket is the head of a linked list.
        self.buckets = [None] * self.capacity
        

    def add(self, key: int) -> None:
        """
        Adds a key to the hash set. If the key is already present, the set remains unchanged.
        This operation avoids a separate 'contains' check for better performance.
        """
        index = self.hash(key)
        curr = self.buckets[index]
        
        # Traverse the linked list in the bucket to check if the key already exists.
        while curr:
            if curr.key == key:
                return  # Key is already in the set, so do nothing.
            curr = curr.next
        
        # If the key is not found, add a new node to the head of the linked list.
        new_node = Node(key, self.buckets[index])
        self.buckets[index] = new_node
        self.size += 1
        
        # If adding the new key exceeds the upper load factor, resize to double the capacity.
        if (self.size / self.capacity) > self.UP_LOAD_FACTOR:
            self.resize(self.capacity * 2)

    def remove(self, key: int) -> None:
        """
        Removes a key from the hash set if it is present.
        This operation finds and removes the key in a single pass.
        """
        index = self.hash(key)
        
        # Use a dummy head to simplify the removal of the first node in the linked list.
        dummy = Node(0, self.buckets[index])
        curr = dummy
        key_found = False
        
        # Traverse the list to find the node to remove.
        while curr.next:
            if curr.next.key == key:
                # Remove the node by updating the 'next' pointer of the previous node.
                curr.next = curr.next.next
                key_found = True
                break
            curr = curr.next
            
        if key_found:
            # If a key was removed, update the bucket head and decrement the size.
            self.buckets[index] = dummy.next
            self.size -= 1
            # If the size drops below the lower load factor, resize to half the capacity.
            # We avoid shrinking below the initial capacity.
            if self.capacity > 32 and (self.size / self.capacity) < self.DOWN_LOAD_FACTOR:
                self.resize(self.capacity // 2)

    def contains(self, key: int) -> bool:
        """
        Returns True if the key is in the hash set, and False otherwise.
        """
        index = self.hash(key)
        curr = self.buckets[index]
        
        # Traverse the linked list at the calculated bucket index.
        while curr:
            if curr.key == key:
                return True
            curr = curr.next
        return False
    
    def resize(self, new_capacity: int):
        """
        Resizes the hash table to a new capacity and re-hashes all existing keys.
        """
        old_buckets = self.buckets
        
        # Reset the properties for the new table structure.
        self.size = 0
        self.capacity = new_capacity
        self.buckets = [None] * self.capacity

        # Re-add all keys from the old buckets to the new, resized buckets.
        for bucket_head in old_buckets:
            curr = bucket_head
            while curr:
                self.add(curr.key)
                curr = curr.next

    def hash(self, key: int) -> int:
        """
        Calculates the bucket index for a given key.
        """
        return key % self.capacity
        


# Your MyHashSet object will be instantiated and called as such:
# obj = MyHashSet()
# obj.add(key)
# obj.remove(key)
# param_3 = obj.contains(key)
