class LRUCache:
    """
    An implementation of a Least Recently Used (LRU) Cache.

    This cache stores a limited number of items and evicts the least recently
    used item when its capacity is reached. It provides O(1) time complexity
    for both `get` and `put` operations.

    This is achieved by using two data structures:
    1. A hash map (Python dictionary) for O(1) lookups of items by key.
       The dictionary stores keys and references to nodes in a doubly linked list.
    2. A doubly linked list to maintain the order of item usage. The most
       recently used items are near the head, and the least recently used
       items are near the tail.
    """

    class DLinkedNode:
        """A node in a doubly linked list."""
        def __init__(self, key=0, value=0):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None

    def __init__(self, capacity: int):
        """
        Initializes the LRU Cache with a given capacity.
        """
        self.capacity = capacity
        self.cache = {}  # Maps key to DLinkedNode

        # Sentinel nodes for head and tail to simplify list operations.
        self.head = self.DLinkedNode()
        self.tail = self.DLinkedNode()
        self.head.next = self.tail
        self.tail.prev = self.head

    def _add_node(self, node: DLinkedNode):
        """Adds a node right after the head (most recent)."""
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

    def _remove_node(self, node: DLinkedNode):
        """Removes a node from the linked list."""
        prev_node = node.prev
        next_node = node.next
        prev_node.next = next_node
        next_node.prev = prev_node

    def get(self, key: int) -> int:
        """
        Retrieves the value for a key if it exists in the cache.
        Moves the accessed item to the most recently used position.
        Returns -1 if the key is not found.
        """
        if key in self.cache:
            node = self.cache[key]
            # Move the accessed node to the front.
            self._remove_node(node)
            self._add_node(node)
            return node.value
        return -1

    def put(self, key: int, value: int) -> None:
        """
        Adds or updates a key-value pair in the cache.
        If the key exists, its value is updated and it's marked as most recently used.
        If the key is new and the cache is at capacity, the least recently used
        item is evicted.
        """
        if key in self.cache:
            # If key exists, update value and move to front.
            node = self.cache[key]
            node.value = value
            self._remove_node(node)
            self._add_node(node)
        else:
            # If key is new, create a new node.
            new_node = self.DLinkedNode(key, value)
            self.cache[key] = new_node
            self._add_node(new_node)

            # If capacity is exceeded, remove the least recently used item.
            if len(self.cache) > self.capacity:
                # The LRU item is the one just before the tail sentinel.
                lru_node = self.tail.prev
                self._remove_node(lru_node)
                # Remove from the cache dictionary as well.
                del self.cache[lru_node.key]