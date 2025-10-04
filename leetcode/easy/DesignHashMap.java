class MyHashMap {

    // --- Inner Node Class for Linked List ---
    // Represents a key-value pair in the hash map.
    // Made private static to encapsulate and avoid implicit reference to MyHashMap instance.
    private static class Node {
        final int key;
        int value;
        Node next; // Pointer to the next node in case of collision

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // --- MyHashMap Fields ---
    private Node[] buckets; // Array of Node heads (each Node is the head of a linked list)
    private int capacity;          // Current capacity of the hash table (number of buckets)
    private int size;              // Current number of key-value mappings in the hash map
    private final double LOAD_FACTOR_THRESHOLD_UP = 0.75; // Threshold for resizing up
    private final double LOAD_FACTOR_THRESHOLD_DOWN = 0.25; // Threshold for resizing down
    private final int INITIAL_CAPACITY = 16; // Initial capacity, often a power of 2

    /**
     * Initializes a new instance of MyHashMap.
     */
    public MyHashMap() {
        this.capacity = INITIAL_CAPACITY;
        this.buckets = new Node[this.capacity]; // Initialize array of Node heads
        // Note: Each element in `buckets` is initially null. Nodes are added on `put`.
        this.size = 0;
    }

    /**
     * Computes the hash index for a given key.
     * Uses a simple modulo operation. Assumes non-negative keys for simplicity.
     * For negative keys, a more robust hash would be `(Integer.hashCode(key) & 0x7fffffff) % this.capacity`.
     *
     * @param key The key to hash.
     * @return The index in the buckets array.
     */
    private int hash(int key) {
        return key % this.capacity;
    }

    /**
     * Inserts a key-value pair into the hash map. If the key already exists,
     * its value is updated. Otherwise, a new entry is added.
     *
     * @param key The key to insert or update.
     * @param value The value associated with the key.
     */
    public void put(int key, int value) {
        int index = hash(key);
        Node current = this.buckets[index]; // Head of the linked list at this bucket

        // Traverse the linked list to find if the key already exists.
        while (current != null) {
            if (current.key == key) {
                current.value = value; // Key found, update its value.
                return;
            }
            current = current.next;
        }

        // Key does not exist, add a new node at the beginning of the bucket's linked list.
        Node newNode = new Node(key, value);
        newNode.next = this.buckets[index];
        this.buckets[index] = newNode;
        this.size++;

        // Check if resizing (expanding) is needed after adding a new entry.
        if ((double) this.size / this.capacity > LOAD_FACTOR_THRESHOLD_UP) {
            resize(this.capacity * 2);
        }
    }

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key to search for.
     * @return The value associated with the key, or -1 if the key is not found.
     */
    public int get(int key) {
        int index = hash(key);
        Node current = this.buckets[index]; // Head of the linked list at this bucket

        // Traverse the linked list to find the key.
        while (current != null) {
            if (current.key == key) {
                return current.value; // Key found, return its value.
            }
            current = current.next;
        }
        return -1; // Key not found.
    }

    /**
     * Removes the key-value pair associated with the given key from the hash map.
     *
     * @param key The key to remove.
     */
    public void remove(int key) {
        int index = hash(key);
        Node head = this.buckets[index]; // Head of the linked list at this bucket

        // Use a dummy node to simplify removal, especially if the head of the list needs to be removed.
        Node dummy = new Node(-1, -1); // Dummy node, key/value don't matter
        dummy.next = head;

        Node prev = dummy;
        Node current = head;

        // Traverse the linked list to find the node to remove.
        while (current != null && current.key != key) {
            prev = current;
            current = current.next;
        }

        // If current is not null, it means we found the node to remove.
        if (current != null) {
            prev.next = current.next; // Bypass the node to remove.
            this.buckets[index] = dummy.next; // Update the bucket head (in case head was removed).
            this.size--;

            // Check if resizing (shrinking) is needed after removing an entry.
            // Only shrink if capacity is not already at its minimum (INITIAL_CAPACITY).
            if (this.capacity > INITIAL_CAPACITY && (double) this.size / this.capacity < LOAD_FACTOR_THRESHOLD_DOWN) {
                resize(this.capacity / 2);
            }
        }
    }

    /**
     * Resizes the hash table to a new capacity and rehashes all existing entries.
     *
     * @param newCapacity The new capacity for the hash table.
     */
    private void resize(int newCapacity) {
        // Store old buckets temporarily.
        Node[] oldBuckets = this.buckets;
        int oldCapacity = this.capacity;

        // Update capacity and initialize new buckets array.
        this.capacity = newCapacity;
        this.buckets = new Node[this.capacity];
        // No need to explicitly initialize each bucket to null, as it's the default for object arrays.
        
        this.size = 0; // Reset size, as put() will increment it again during rehash.

        // Rehash all entries from old buckets into new buckets.
        for (int i = 0; i < oldCapacity; i++) {
            Node node = oldBuckets[i];
            while (node != null) {
                this.put(node.key, node.value); // Use put method to rehash each entry
                node = node.next;
            }
        }
    }
}
