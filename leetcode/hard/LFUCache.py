class Node:
    """Represents a key-value pair with frequency and linked-list pointers."""
    def __init__(self, key: int, value: int, freq: int = 1):
        self.key = key
        self.value = value
        self.freq = freq
        self.prev = None
        self.next = None

class DoublyLinkedList:
    """A list for nodes with the same frequency, ordered by recency."""
    def __init__(self):
        # Use dummy head and tail nodes to simplify insertions/deletions
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.size = 0

    def add_to_head(self, node: Node):
        """Adds a node right after the head (Most Recently Used). O(1)."""
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node
        self.size += 1

    def remove_node(self, node: Node):
        """Removes a given node from the list. O(1)."""
        node.prev.next = node.next
        node.next.prev = node.prev
        self.size -= 1

    def remove_from_tail(self) -> Node:
        """Removes the node just before the tail (Least Recently Used). O(1)."""
        if self.size == 0:
            return None
        
        node_to_remove = self.tail.prev
        self.remove_node(node_to_remove)
        return node_to_remove

    def is_empty(self) -> bool:
        """Checks if the list contains any real nodes."""
        return self.size == 0

class LFUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.min_freq = 0
        
        # Maps key -> Node object (stores value, freq, and DLL pointers)
        self.key_to_node = {}
        
        # Maps frequency -> DoublyLinkedList object
        self.freq_to_list = {}


    def _update_node_freq(self, node: Node):
        """Moves a node from its current frequency list to the next one. O(1)."""
        old_freq = node.freq
        new_freq = old_freq + 1
        
        # 1. Remove the node from the old list
        old_list = self.freq_to_list[old_freq]
        old_list.remove_node(node)
        
        # 2. Check if min_freq needs to be incremented
        if old_list.is_empty() and old_freq == self.min_freq:
            self.min_freq = new_freq
            
        # 3. Increment the node's frequency
        node.freq = new_freq
        
        # 4. Ensure the new list exists and add the node to its head (MRU position)
        if new_freq not in self.freq_to_list:
            self.freq_to_list[new_freq] = DoublyLinkedList()
            
        self.freq_to_list[new_freq].add_to_head(node)


    def get(self, key: int) -> int:
        """Retrieves a value and updates the node's frequency. O(1)."""
        if key not in self.key_to_node:
            return -1
        
        node = self.key_to_node[key]
        self._update_node_freq(node)
        return node.value


    def put(self, key: int, value: int) -> None:
        """Inserts or updates a value. Evicts LFU/LRU item if full. O(1)."""
        if self.capacity == 0:
            return

        # Case 1: Key already exists (Update)
        if key in self.key_to_node:
            node = self.key_to_node[key]
            node.value = value  # Update value
            self._update_node_freq(node)
            return

        # Case 2: New key, cache is full (Evict then Insert)
        if len(self.key_to_node) == self.capacity:
            # a. Get the list for the current min_freq
            list_to_evict_from = self.freq_to_list[self.min_freq]
            
            # b. Remove the LRU node (tail) from that list
            node_to_evict = list_to_evict_from.remove_from_tail()
            
            # c. Remove the key from the main cache map
            self.key_to_node.pop(node_to_evict.key)
            
            # Check if the min_freq list is now empty and clear it if so
            if list_to_evict_from.is_empty():
                 # min_freq will be reset to 1 below, so no need to update it here
                 # If it were to be updated, it would normally be done in _update_node_freq
                 # but for a new insertion, it will be 1. We rely on the insert below.
                 pass
        
        # Case 3: Insert new key
        
        # a. Create new node with freq 1
        new_node = Node(key, value, freq=1)
        self.key_to_node[key] = new_node
        
        # b. Ensure the list for freq 1 exists
        if 1 not in self.freq_to_list:
            self.freq_to_list[1] = DoublyLinkedList()
            
        # c. Add the new node to the list for freq 1
        self.freq_to_list[1].add_to_head(new_node)
        
        # d. New insertion always sets min_freq to 1
        self.min_freq = 1
        


# Your LFUCache object will be instantiated and called as such:
# obj = LFUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)