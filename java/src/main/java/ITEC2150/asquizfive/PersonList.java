package ITEC2150.asquizfive;

public class PersonList {
    private Person value;
    private PersonList nextNode;

    // Constructor
    public PersonList(Person person) {
        this.value = person;
        this.nextNode = null;
    }

    // Get the Person value stored in this node
    public Person getValue() {
        return value;
    }

    // Set the Person value for this node
    public void setValue(Person person) {
        this.value = person;
    }

    // Check if there is a next node
    public boolean hasNext() {
        return nextNode != null;
    }

    // Add a node as the next node
    public void addNode(PersonList node) {
        this.nextNode = node;
    }

    // Get the next node in the list
    public PersonList getNextNode() {
        return nextNode;
    }
}