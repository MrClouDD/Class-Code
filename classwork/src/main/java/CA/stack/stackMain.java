package CA.stack;

/**
 * A simple stack implementation using an array
 * @author Ajitesh Sandhu
 * @date July 14, 2025
 */
public class stackMain {
    // Create an int stack array of size 100
    private final int[] stackArray = new int[100];

    // Top index - points to the next available position
    private int top = 0;

    /**
     * Check if stack is empty
     * @return true if stack is empty, false otherwise
     */
    boolean isEmpty(){
        return top == 0;
    }

    /**
     * Check if stack is full
     * @return true if stack is full, false otherwise
     */
    boolean isFull(){
        return top >= stackArray.length;
    }

    /**
     * Return the current number of elements in the stack
     * @return number of elements currently in the stack
     */
    int size(){
        return top;
    }

    /**
     * Return the maximum capacity of the stack
     * @return maximum capacity of the stack array
     */
    int capacity(){
        return stackArray.length;
    }

    /**
     * Push an int onto the stack
     * @param v the value to push onto the stack
     * @throws RuntimeException if stack is full
     */
    void push(int v){
        if (isFull()) {
            throw new RuntimeException("Stack overflow: Cannot push to full stack");
        }
        stackArray[top] = v;
        top++;
    }

    /**
     * Pop the topmost entry from the stack
     * @return the value that was on top of the stack
     * @throws RuntimeException if stack is empty
     */
    int pop(){
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow: Cannot pop from empty stack");
        }
        top--;
        return stackArray[top];
    }

    /**
     * Peek at the top element without removing it
     * @return the value on top of the stack
     * @throws RuntimeException if stack is empty
     */
    int peek(){
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty: Cannot peek");
        }
        return stackArray[top - 1];
    }


    /**
     * Basic main to add 1-9 to the stack before popping and printing values
     */
    public static void main(String[] args) {
        stackMain stack = new stackMain();
        for (int i = 0; i <= 9; i++){
            stack.push(i);
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        System.out.println("Ajitesh Sandhu");
    }
}
