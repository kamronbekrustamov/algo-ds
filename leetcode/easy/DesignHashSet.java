class MyHashSet {

    // --- Inner Node Class for Linked List ---
    // Represents a key in the hash set.
    // Made private static to encapsulate and avoid implicit reference to MyHashSet instance.
    private static class Node {
        final int key;
        Node next; // Pointer to the next node in case of collision

        public Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    // --- MyHashSet Fields ---
    private Node[] buckets; // Array of Node heads (each Node is the head of a linked list)
    private int capacity;          // Current capacity of the hash table (number of buckets)
    private int size;              // Current number of elements in the hash set
    private final double LOAD_FACTOR_THRESHOLD_UP = 0.75; // Threshold for resizing up
    private final double LOAD_FACTOR_THRESHOLD_DOWN = 0.25; // Threshold for resizing down (for shrink)
    private final int INITIAL_CAPACITY = 16; // Initial capacity, often a power of 2

    /**
     * Initializes a new instance of MyHashSet.
     */
    public MyHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.buckets = new Node[this.capacity]; // Initialize array of Node heads
        // Note: Each element in `buckets` is initially null. Nodes are added on `add`.
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
     * Adds the specified element to this set if it is not already present.
     *
     * @param key The element to be added to this set.
     */
    public void add(int key) {
        int index = hash(key);
        Node current = this.buckets[index]; // Head of the linked list at this bucket

        // Traverse the linked list to check if the key already exists.
        while (current != null) {
            if (current.key == key) {
                return; // Key already exists, do nothing.
            }
            current = current.next;
        }

        // Key does not exist, add a new node at the beginning of the bucket's linked list.
        Node newNode = new Node(key, this.buckets[index]);
        this.buckets[index] = newNode;
        this.size++;

        // Check if resizing (expanding) is needed after adding a new entry.
        if ((double) this.size / this.capacity > LOAD_FACTOR_THRESHOLD_UP) {
            resize(this.capacity * 2);
        }
    }
    
    /**
     * Removes the specified element from this set if it is present.
     *
     * @param key The element to be removed from this set.
     */
    public void remove(int key) {
        int index = hash(key);
        Node head = this.buckets[index]; // Head of the linked list at this bucket

        // Use a dummy node to simplify removal, especially if the head of the list needs to be removed.
        Node dummy = new Node(-1, head); // Dummy node, key doesn't matter
        
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
     * Returns true if this set contains the specified element.
     *
     * @param key The element whose presence in this set is to be tested.
     * @return true if this set contains the specified element, false otherwise.
     */
    public boolean contains(int key) {
        int index = hash(key);
        Node current = this.buckets[index]; // Head of the linked list at this bucket

        // Traverse the linked list to find the key.
        while (current != null) {
            if (current.key == key) {
                return true; // Key found.
            }
            current = current.next;
        }
        return false; // Key not found.
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
        
        this.size = 0; // Reset size, as add() will increment it again during rehash.

        // Rehash all entries from old buckets into new buckets.
        for (int i = 0; i < oldCapacity; i++) {
            Node node = oldBuckets[i];
            while (node != null) {
                this.add(node.key); // Use add method to rehash each entry
                node = node.next;
            }
        }
    }
}