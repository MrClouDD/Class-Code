package ITEC2150.CA.sorting;

/**
 * Custom Queue implementation using an array with FIFO (First In, First Out) behavior.
 * This class demonstrates basic queue operations including enqueue, dequeue, and status checks.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-12
 */
public class MyQueue {
    private int[] data;     // Array to store queue elements
    private int front;      // Index pointing to the front of the queue
    private int back;       // Index pointing to the back of the queue
    private int size;       // Current number of elements in the queue
    private static final int CAPACITY = 10;  // Fixed capacity of the queue
    private static final int EMPTY_VALUE = -99999;  // Return value when queue is empty
    
    /**
     * Constructor initializes the queue with default capacity
     */
    public MyQueue() {
        data = new int[CAPACITY];
        front = 0;
        back = -1;
        size = 0;
    }
    
    /**
     * Checks if the queue is empty
     * @return true if the queue contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Checks if the queue is full
     * @return true if the queue has reached its maximum capacity, false otherwise
     */
    public boolean isFull() {
        return size == CAPACITY;
    }
    
    /**
     * Adds a value to the back of the queue (enqueue operation)
     * @param v the integer value to be added to the queue
     * @return true if the element was successfully added, false if queue is full
     */
    public boolean enqueue(int v) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot enqueue " + v);
            return false;
        }
        
        // Move back pointer to next position (circular array implementation)
        back = (back + 1) % CAPACITY;
        data[back] = v;
        size++;
        
        System.out.println("Enqueued: " + v);
        return true;
    }
    
    /**
     * Removes and returns the value at the front of the queue (dequeue operation)
     * @return the value at the front of the queue, or -99999 if queue is empty
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return EMPTY_VALUE;
        }
        
        int value = data[front];
        // Move front pointer to next position (circular array implementation)
        front = (front + 1) % CAPACITY;
        size--;
        
        System.out.println("Dequeued: " + value);
        return value;
    }
    
    /**
     * Returns the current size of the queue
     * @return the number of elements currently in the queue
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Main method to demonstrate queue functionality
     */
    public static void main(String[] args) {
        // Print name at the start of the program
        System.out.println("Queue Implementation Demo");
        System.out.println("Name: Ajitesh Sandhu");
        System.out.println("================================");
        
        // Create an instance of MyQueue
        MyQueue queue = new MyQueue();
        
        // Demonstrate queue operations
        System.out.println("\n--- Testing Queue Operations ---");
        System.out.println("Initial queue empty status: " + queue.isEmpty());
        System.out.println("Initial queue full status: " + queue.isFull());
        
        // Enqueue the values 2, 1, 5, and 0
        System.out.println("\n--- Enqueuing values: 2, 1, 5, 0 ---");
        queue.enqueue(2);
        queue.enqueue(1);
        queue.enqueue(5);
        queue.enqueue(0);
        
        System.out.println("\nQueue size after enqueuing: " + queue.getSize());
        System.out.println("Queue empty status: " + queue.isEmpty());
        System.out.println("Queue full status: " + queue.isFull());
        
        // Dequeue all elements and print them
        System.out.println("\n--- Dequeuing all elements ---");
        while (!queue.isEmpty()) {
            int value = queue.dequeue();
            System.out.println("Current queue size: " + queue.getSize());
        }
        
        // Final status check
        System.out.println("\n--- Final Status ---");
        System.out.println("Queue empty status: " + queue.isEmpty());
        System.out.println("Queue size: " + queue.getSize());
        
        // Demonstrate dequeue from empty queue
        System.out.println("\n--- Testing dequeue from empty queue ---");
        int emptyResult = queue.dequeue();
        System.out.println("Result from empty queue dequeue: " + emptyResult);
    }
}
