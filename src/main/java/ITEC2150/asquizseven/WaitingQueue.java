package ITEC2150.asquizseven;

/**
 * WaitingQueue implementation based on custom MyQueue class created before
 * @author Ajitesh Sandhu
 * @date 2025-07-14
 */
public class WaitingQueue {
    private final Person[] data;     // Array to store queue elements
    private int front;         // Index pointing to the front of the queue
    private int back;          // Index pointing to the back of the queue
    private int size;          // Current number of elements in the queue
    private static final int CAPACITY = 10;  // Fixed capacity of the queue
    
    /**
     * Constructor initializes the queue with default capacity
     */
    public WaitingQueue() {
        data = new Person[CAPACITY];
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
     * Adds a Person to the back of the queue (enqueue operation)
     * @param person the Person object to be added to the queue
     * @return true if the element was successfully added, false if queue is full
     */
    public boolean enqueue(Person person) {
        if (isFull()) {
            System.out.println("Queue is full! Cannot enqueue " + person.getName());
            return false;
        }
        
        // Move back pointer to next position (circular array implementation)
        back = (back + 1) % CAPACITY;
        data[back] = person;
        size++;
        
        return true;
    }
    
    /**
     * Removes and returns the Person at the front of the queue (dequeue operation)
     * @return the Person at the front of the queue, or null if queue is empty
     */
    public Person dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty! Cannot dequeue.");
            return null;
        }
        
        Person person = data[front];
        // Move front pointer to next position (circular array implementation)
        front = (front + 1) % CAPACITY;
        size--;
        
        return person;
    }
    
    /**
     * Returns the current size of the queue
     * @return the number of elements currently in the queue
     */
    public int getSize() {
        return size;
    }
}
