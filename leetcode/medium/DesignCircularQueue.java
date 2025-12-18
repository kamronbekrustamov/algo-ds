/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure
 * in which the operations are performed based on FIFO (First In First Out) principle and the last position 
 * is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * * The class uses an array to store elements and uses three variables:
 * - front: Index of the element at the front of the queue (if not empty).
 * - rear: Index of the next available slot for insertion.
 * - count: Current number of elements in the queue.
 * * All methods are O(1) time complexity.
 */
class MyCircularQueue {
    // Array to hold the queue elements.
    private final int[] queue;
    // Maximum capacity of the queue. Made final for clarity.
    private final int capacity; 
    // Current number of elements in the queue. Used to distinguish between empty and full.
    private int count;
    // Index of the element at the front of the queue.
    private int front;
    // Index of the next available slot (where the next element will be inserted).
    private int rear;

    /**
     * Initializes the object with the size of the queue to be k.
     * @param k The size of the circular queue.
     */
    public MyCircularQueue(int k) {
        // Use k+1 size for one common strategy, or use count/size for another.
        // This implementation uses 'count' and requires array size 'k'.
        queue = new int[k];
        capacity = k;
        count = 0;
        front = 0;
        rear = 0;
    }
    
    /**
     * Inserts an element into the circular queue. Return true if the operation is successful.
     * Time Complexity: O(1)
     * @param value The element to insert.
     * @return true if successful, false if the queue is full.
     */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        // Insert the new value at the 'rear' index.
        queue[rear] = value;
        
        // Move the 'rear' pointer to the next slot, wrapping around using modulo.
        rear = (rear + 1) % capacity;
        count++;
        return true;
    }
    
    /**
     * Deletes an element from the circular queue. Return true if the operation is successful.
     * Time Complexity: O(1)
     * @return true if successful, false if the queue is empty.
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        // Move the 'front' pointer to the next element, wrapping around using modulo.
        front = (front + 1) % capacity;
        count--;
        return true;
    }
    
    /**
     * Gets the front item from the queue. If the queue is empty, return -1.
     * Time Complexity: O(1)
     * @return The front element, or -1 if the queue is empty.
     */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        // The element at the 'front' index is the current front of the queue.
        return queue[front];
    }
    
    /**
     * Gets the last item from the queue. If the queue is empty, return -1.
     * Time Complexity: O(1)
     * @return The rear element, or -1 if the queue is empty.
     */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        // The 'rear' pointer points to the *next empty slot*. 
        // The last inserted element is at the index before 'rear'.
        // Add 'capacity' before modulo to handle the case where rear is 0 (i.e., (0 - 1) % k).
        int lastElementIndex = (rear - 1 + capacity) % capacity;
        return queue[lastElementIndex];
    }
    
    /**
     * Checks whether the circular queue is empty or not.
     * Time Complexity: O(1)
     * @return true if the queue has no elements, false otherwise.
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
    /**
     * Checks whether the circular queue is full or not.
     * Time Complexity: O(1)
     * @return true if the number of elements equals the capacity, false otherwise.
     */
    public boolean isFull() {
        return count == capacity;
    }
}