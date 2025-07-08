package asquizsix;

/**
 * Simple stack implementation for Coin objects.
 * 
 * @author Ajitesh Sandhu
 */
public class CoinStack {
    private final Coin[] stack;
    private int head = -1;              // index of the top element (empty when -1)
    private int curr = 0;               // next free index, also size of stack

    /** Creates a new CoinStack with default capacity of 10. */
    public CoinStack() {
        this.stack = new Coin[10];
    }

    /** @return true if the stack is empty. */
    public boolean isEmpty() {
        return head == 0;
    }

    /** @return true if the stack reached its capacity. */
    public boolean isFull() {
        return head == stack.length;
    }

    /**
     * Pushes a coin onto the stack.
     * @throws IllegalArgumentException if coin is null
     * @throws IllegalStateException    if stack is full
     */
    public void push(Coin coin) {
        if (coin == null) 
            throw new IllegalArgumentException("Cannot push null coin");
        if (isFull()) 
            throw new IllegalStateException("Stack is full");
        stack[curr] = coin;
        head = curr;
        curr++;
    }

    /**
     * Pops and returns the top coin.
     * @throws IllegalStateException if stack is empty
     */
    public Coin pop() {
        if (isEmpty()) 
            throw new IllegalStateException("Stack is empty");
        curr--;
        Coin c = stack[curr];
        stack[curr] = null; // help GC
        head = curr - 1;
        return c;
    }

    /**
     * Peeks at the top coin without removing.
     * @throws IllegalStateException if stack is empty
     */
    public Coin peek() {
        if (isEmpty()) 
            throw new IllegalStateException("Stack is empty");
        return stack[curr - 1];
    }

    /** @return the number of coins currently in the stack. */
    public int size() {
        return curr;
    }
}
