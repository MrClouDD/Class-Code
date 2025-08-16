package ITEC2150.astest2.test22;
// you can add your package information here

/**
 * complete the following List class with Generic type T
 * @author Ajitesh Sandhu
 * the class should have the class constructor, getValue(), hasNext(), addNode(), getNextNode() methods.
*/
public class List<T> {
    private T value; // The data stored in this node
    private List<T> nextNode; // Reference to the next node in the list
    
    /**
     * Constructor that initializes the node with a value
     * @param value The value to store in this node
     */
    List(T value) {
        this.value = value;
        this.nextNode = null;
    }
    
    /**
     * Gets the value stored in this node
     * @return The value of type T
     */
    public T getValue() {
        return this.value;
    }
    
    /**
     * Checks if this node has a next node
     * @return true if there is a next node, false otherwise
     */
    public boolean hasNext() {
        return this.nextNode != null;
    }
    
    /**
     * Adds a node as the next node of this current node
     * @param node The node to add as the next node
     */
    public void addNode(List<T> node) {
        this.nextNode = node;
    }
    
    /**
     * Gets the next node in the list
     * @return The next node
     */
    public List<T> getNextNode() {
        return this.nextNode;
    }
}
