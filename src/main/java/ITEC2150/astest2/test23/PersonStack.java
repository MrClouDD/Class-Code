package ITEC2150.astest2.test23;
// you can add your package information here

import java.util.ArrayList;

/**
 * Stack implementation for Person objects
 * @author Ajitesh Sandhu
 */
// complete the following PersonStack class
// the PersonStack class should have the class constructor, isEmpty(), push(), and pop() methods.
public class PersonStack {
    private ArrayList<Person> stack; // Internal storage for the stack
    
    /**
     * Constructor initializes an empty stack
     */
    PersonStack() {
        stack = new ArrayList<>();
    }
    
    /**
     * Checks if the stack is empty
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    /**
     * Pushes a Person object onto the top of the stack
     * @param person The Person object to push onto the stack
     */
    public void push(Person person) {
        stack.add(person);
    }
    
    /**
     * Pops and returns the Person object from the top of the stack
     * @return The Person object from the top of the stack
     */
    public Person pop() {
        if (!isEmpty()) {
            return stack.remove(stack.size() - 1); // Remove and return last element (top of stack)
        }
        return null; // Return null if stack is empty
    }
}
